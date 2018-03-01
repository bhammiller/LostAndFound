package com.example.demo.Config;

import com.example.demo.Models.AppRole;
import com.example.demo.Models.AppUser;
import com.example.demo.Models.LostItems;
import com.example.demo.Repositories.AppRoleRepository;
import com.example.demo.Repositories.AppUserRepository;
import com.example.demo.Repositories.LostItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    AppRoleRepository appRoleRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    LostItemsRepository lostItemsRepository;


    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Loading data ...");
        AppRole role = new AppRole();
        role.setRoleName("USER");
        appRoleRepository.save(role);

        role = new AppRole();
        role.setRoleName("ADMIN");
        appRoleRepository.save(role);

        // Users
        // User 1
        AppUser appUser = new AppUser();
        appUser.setFirstName("John");
        appUser.setLastName("Doe");
        appUser.setUserEmail("g1@gmail.com");
        appUser.setAppUsername("john");
        appUser.setAppPassword("password1");
        appUserRepository.save(appUser);
        appUser.addRole(appRoleRepository.findAppRoleByRoleName("USER"));
        appUserRepository.save(appUser);
        // User 2
        appUser = new AppUser();
        appUser.setFirstName("Jacob");
        appUser.setLastName("Smith");
        appUser.setUserEmail("g2@gmail.com");
        appUser.setAppUsername("jacob");
        appUser.setAppPassword("password2");
        appUserRepository.save(appUser);
        appUser.addRole(appRoleRepository.findAppRoleByRoleName("USER"));
        appUserRepository.save(appUser);
        // User 3
        appUser = new AppUser();
        appUser.setFirstName("Jane");
        appUser.setLastName("Pane");
        appUser.setUserEmail("g3@gmail.com");
        appUser.setAppUsername("jane");
        appUser.setAppPassword("password3");
        appUserRepository.save(appUser);
        appUser.addRole(appRoleRepository.findAppRoleByRoleName("USER"));
        appUserRepository.save(appUser);

        // adding items
        /*foodItems.setItemName("Fried Chicken");
        foodItems.setServingSize(5);
        foodItems.setItemCategory("Food");
        appUser=appUserRepository.findById(new Long(1));
        foodItems.setAppUser(appUser);
        foodItemsRepository.save(foodItems);
        appUser.addFoodItems(foodItems);
        appUserRepository.save(appUser);*/
        LostItems lostItems=new LostItems();
        // Item 1
        lostItems.setItemType("other");
        lostItems.setItemTitle("Necronomicon");
        lostItems.setItemImage("/img/other/necronomicon1-2.jpg");
        lostItems.setItemDescription("Book bound in human flesh and written in blood. Front similar in appearance to a face. Lost from Miskatonic University " +
                "Library. DO NOT READ!");
        lostItems.setFoundStatus(false);
        appUser=appUserRepository.findOne(new Long(2));
        lostItems.setAppUser(appUser);
        lostItemsRepository.save(lostItems);
        appUser.addLostItems(lostItems);
        appUserRepository.save(appUser);
        // Item 2
        lostItems=new LostItems();
        lostItems.setItemType("clothes");
        lostItems.setItemTitle("Red Sweater");
        lostItems.setItemImage("/img/clothes/redsweater.jpg");
        lostItems.setItemDescription("Red wool sweater. Mens XL. Polo brand. Found at Shady Grove Metro Station.");
        lostItems.setFoundStatus(false);
        appUser=appUserRepository.findOne(new Long(1));
        lostItems.setAppUser(appUser);
        lostItemsRepository.save(lostItems);
        appUser.addLostItems(lostItems);
        appUserRepository.save(appUser);
        // Item 3
        lostItems=new LostItems();
        lostItems.setItemType("pet");
        lostItems.setItemTitle("Steve the Chatty Parrot");
        lostItems.setItemImage("/img/pets/Parrot.jpg");
        lostItems.setItemDescription("Parrot with red and blue plumiage. Scratch on left side of beak. Lost from Kentlands Whole Foods." +
                " Very talkative. Answers to the name Steve");
        lostItems.setFoundStatus(false);
        appUser=appUserRepository.findOne(new Long(2));
        lostItems.setAppUser(appUser);
        lostItemsRepository.save(lostItems);
        appUser.addLostItems(lostItems);
        appUserRepository.save(appUser);
        // Item 4
        lostItems=new LostItems();
        lostItems.setItemType("other");
        lostItems.setItemTitle("Marble Chess Set");
        lostItems.setItemImage("/img/other/marblechess.jpg");
        lostItems.setItemDescription("Marble chess set with white and black pieces. Lost from the library at the Rockville campus of MCC.");
        lostItems.setFoundStatus(false);
        appUser=appUserRepository.findOne(new Long(3));
        lostItems.setAppUser(appUser);
        lostItemsRepository.save(lostItems);
        appUser.addLostItems(lostItems);
        appUserRepository.save(appUser);
        // Item 5
        lostItems=new LostItems();
        lostItems.setItemType("clothes");
        lostItems.setItemTitle("Yellow T-Shirt");
        lostItems.setItemImage("/img/clothes/Yellowflash.jpg");
        lostItems.setItemDescription("Yellow t-shirt with comic book insignia of the Flash on front. Womens M. Lost from the bathroom at Rockville Library. ");
        lostItems.setFoundStatus(false);
        appUser=appUserRepository.findOne(new Long(1));
        lostItems.setAppUser(appUser);
        lostItemsRepository.save(lostItems);
        appUser.addLostItems(lostItems);
        appUserRepository.save(appUser);
        // Item 6
        lostItems=new LostItems();
        lostItems.setItemType("other");
        lostItems.setItemTitle("Monkey's Paw");
        lostItems.setItemImage("/img/other/The_Monkey's_Paw.jpg");
        lostItems.setItemDescription("Shriveled severed monkey's paw. Allegedly grants wishes that backfire horribly. Lost from the tomb W. W. Jacobs.");
        lostItems.setFoundStatus(false);
        appUser=appUserRepository.findOne(new Long(1));
        lostItems.setAppUser(appUser);
        lostItemsRepository.save(lostItems);
        appUser.addLostItems(lostItems);
        appUserRepository.save(appUser);
        // Item 7
        lostItems=new LostItems();
        lostItems.setItemType("pet");
        lostItems.setItemTitle("Tiger");
        lostItems.setItemImage("/img/pets/Siberischer_tiger_de_edit02.jpg");
        lostItems.setItemDescription("Lost from the National Zoo. It's a free roaming tiger, there can't be that many out there.");
        lostItems.setFoundStatus(false);
        appUser=appUserRepository.findOne(new Long(3));
        lostItems.setAppUser(appUser);
        lostItemsRepository.save(lostItems);
        appUser.addLostItems(lostItems);
        appUserRepository.save(appUser);
        // Item 8
        lostItems=new LostItems();
        lostItems.setItemType("clothes");
        lostItems.setItemTitle("Baseball Hat");
        lostItems.setItemImage("/img/clothes/nationals cap.jpeg");
        lostItems.setItemDescription("Blue and white baseball hat with logo for the Washington Nationals. One Size Fits All. Jimmy written on inside brim." +
                "Lost at Montgomery Mall.");
        lostItems.setFoundStatus(false);
        appUser=appUserRepository.findOne(new Long(2));
        lostItems.setAppUser(appUser);
        lostItemsRepository.save(lostItems);
        appUser.addLostItems(lostItems);
        appUserRepository.save(appUser);
        // Item 9
        lostItems=new LostItems();
        lostItems.setItemType("other");
        lostItems.setItemTitle("Red Car");
        lostItems.setItemImage("/img/other/Christine.jpg");
        lostItems.setItemDescription("Red and white 1958 Plymouth Fury. Last seen in Maine. Former owner Arnie Cunningham.");
        lostItems.setFoundStatus(false);
        appUser=appUserRepository.findOne(new Long(3));
        lostItems.setAppUser(appUser);
        lostItemsRepository.save(lostItems);
        appUser.addLostItems(lostItems);
        appUserRepository.save(appUser);
        // Item 10
        lostItems=new LostItems();
        lostItems.setItemType("clothes");
        lostItems.setItemTitle("Ballet Shoes");
        lostItems.setItemImage("/img/clothes/balletshoes.jpeg");
        lostItems.setItemDescription("Pink ballet shoes with light blue laces. Womens size medium.Lost at the Tanz Dance Academy in Freiburg, Germany. " +
                "If found please return to Suzy Bannion");
        lostItems.setFoundStatus(false);
        appUser=appUserRepository.findOne(new Long(1));
        lostItems.setAppUser(appUser);
        lostItemsRepository.save(lostItems);
        appUser.addLostItems(lostItems);
        appUserRepository.save(appUser);
        // Item 11
        lostItems=new LostItems();
        lostItems.setItemType("other");
        lostItems.setItemTitle("Large Unopened Crate");
        lostItems.setItemImage("/img/other/woodencrate.jpg");
        lostItems.setItemDescription("Large unopened wooden crate. Labels on crate say: Miskatonic University, Dr. Wiliam Dyer, Antartic Expediation. " +
                "Lost from the New York Port Authority");
        lostItems.setFoundStatus(true);
        appUser=appUserRepository.findOne(new Long(2));
        lostItems.setAppUser(appUser);
        lostItemsRepository.save(lostItems);
        appUser.addLostItems(lostItems);
        appUserRepository.save(appUser);
        // Item 12
        lostItems=new LostItems();
        lostItems.setItemType("pet");
        lostItems.setItemTitle("Horse");
        lostItems.setItemImage("/img/pets/f3a65ef6dce359221c016a12677ead39.jpg");
        lostItems.setItemDescription("Chestnut brown horse. Female. Last seen in the vicinity of the Roy Rogers by Lakeforest mall." +
                "Posseses a gray and blue sadle with crimson trim.");
        lostItems.setFoundStatus(false);
        appUser=appUserRepository.findOne(new Long(1));
        lostItems.setAppUser(appUser);
        lostItemsRepository.save(lostItems);
        appUser.addLostItems(lostItems);
        appUserRepository.save(appUser);



    }
}