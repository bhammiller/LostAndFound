package com.example.demo.Controllers;

import com.example.demo.Models.*;
import com.example.demo.Repositories.*;
import com.example.demo.Config.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    // Autowired Repositories
    @Autowired
    AppRoleRepository appRoleRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    LostItemsRepository lostItemsRepository;


    /////////////// METHODS //////////////////

    @RequestMapping("/")
    public String index() {
        return "homepage";
    }

    @RequestMapping("/login")
    public String login() {
        return "loginpage";
    }

    // Security and User Methods

    @GetMapping("/register")
    public String registerUser(Model model) {
        model.addAttribute("newUser", new AppUser());
        return "registrationpage";
    }

    @PostMapping("/register")
    public String addNewUser(@Valid @ModelAttribute("newUser") AppUser newUser, BindingResult result, Model model) {

        if (result.hasErrors()) {
            System.out.println(result.toString());
            return "registrationpage";
        } else {

            model.addAttribute(newUser.getAppUsername() + " created");
            appUserRepository.save(newUser);
            AppRole r = appRoleRepository.findAppRoleByRoleName("USER");
            newUser.addRole(r);
            appUserRepository.save(newUser);
            return "redirect:/";
        }
    }

    // Lost Item methods ///////////

    // Listing Methods
    @RequestMapping("/lostlist")
    public String showLostList(Model model){
        model.addAttribute("listed",lostItemsRepository.findAllByFoundStatusIs(false) );
        return "itemlistpage";
    }

    @RequestMapping("/foundlist")
    public String showFoundList(Model model){
        model.addAttribute("listed", lostItemsRepository.findAllByFoundStatusIs(true));
        return "itemlistpage";
    }

    @RequestMapping("/clotheslist")
    public String showLostClothes(Model model){
        model.addAttribute("listed", lostItemsRepository.findAllByItemTypeIs("clothes"));
        return "itemlistpage";
    }

    @RequestMapping("/petlist")
    public String showLostPets(Model model){
        model.addAttribute("listed", lostItemsRepository.findAllByItemTypeIs("pet"));
        return "itemlistpage";
    }

    @RequestMapping("/otherlist")
    public String showLostOther(Model model){
        model.addAttribute("listed", lostItemsRepository.findAllByItemTypeIs("other"));
        return "itemlistpage";
    }

    @RequestMapping("/usersfound")
    public String showUsersFoundItems(Model model, Authentication authentication){
        AppUser appUser = appUserRepository.findAppUserByAppUsername(authentication.getName());
        List<LostItems> lostItems1 = appUser.getLostItemsList();
        List<LostItems> lostItems3 = new ArrayList<>();
        for(LostItems lostItems :lostItems1){
            if(lostItems.getFoundStatus()==true){
                lostItems3.add(lostItems);
            }
        }
        model.addAttribute("listed",lostItems3);
        return "itemlistpage";
    }

    // Searching Methods
    @PostMapping("/searchitem")
    public String showSearchItemResults(HttpServletRequest request, Model model)
    {
        //Get the search string from the result form
        String searchString = request.getParameter("search");
        model.addAttribute("search",searchString);
        model.addAttribute("listed",lostItemsRepository.findAllByItemDescriptionContainingIgnoreCase(searchString));
        return "itemlistpage";
    }

    // Basic User Methods
    @GetMapping("/additem")
    public String addLostItem(Model model){
        model.addAttribute("additem",new LostItems());
        return "reportmissing";
    }

    @PostMapping("/processitem")
    public String processLostItem(@Valid @ModelAttribute("additem") LostItems lostItems, BindingResult result, Authentication authentication){
        if (result.hasErrors()){
            return "reportmissing";
        }
        AppUser appUser = appUserRepository.findAppUserByAppUsername(authentication.getName());
        lostItems.setAppUser(appUser);
        lostItems.setFoundStatus(false);
        lostItemsRepository.save(lostItems);
        appUser.addLostItems(lostItems);
        appUserRepository.save(appUser);
        return "redirect:/lostlist";
    }


    // ADMIN Methods
    @GetMapping("/userslist")
    public String showUsersList(Model model){
        model.addAttribute("userlist", appUserRepository.findAll());
        return "userslist";
    }

    @GetMapping("/addeditem/{id}")
    public String itemAddedByAdmin(@PathVariable("id") long id, Model model){
        model.addAttribute("additem",new LostItems());
        model.addAttribute("user", appUserRepository.findOne(id));
        return "reportmissing2";
    }

    @PostMapping("/processaddedtem/{id}")
    public String processAddedItem(@Valid @ModelAttribute("additem") @PathVariable("id") long id,
                                   LostItems lostItems, BindingResult result){
        if (result.hasErrors()){
            return "reportmissing2";
        }
        AppUser appUser = appUserRepository.findOne(id);
        lostItems.setAppUser(appUser);
        lostItems.setFoundStatus(false);
        lostItemsRepository.save(lostItems);
        appUser.addLostItems(lostItems);
        appUserRepository.save(appUser);
        return "redirect:/lostlist";
    }

    @RequestMapping("/tofound/{id}")
    public String changeToFound(@PathVariable("id") long id){
        LostItems lostItems =lostItemsRepository.findOne(id);
        lostItems.setFoundStatus(true);
        return "redirect:/foundlist";
    }

    @RequestMapping("/tolost/{id}")
    public String changeToLost(@PathVariable("id") long id){
        LostItems lostItems =lostItemsRepository.findOne(id);
        lostItems.setFoundStatus(false);
        return "redirect:/lostlist";
    }
    @GetMapping("/addeditem2")
    public String itemLonelyAddedByAdmin( Model model){
        model.addAttribute("additem",new LostItems());
        return "reportmissing3";
    }

    @PostMapping("/processaddedtem2")
    public String processLonelyItem(@Valid @ModelAttribute("additem")
                                   LostItems lostItems, BindingResult result){
        if (result.hasErrors()){
            return "reportmissing3";
        }
        lostItems.setFoundStatus(false);
        lostItemsRepository.save(lostItems);
        return "redirect:/lostlist";
    }
}
