package com.example.demo.Models;

import javax.persistence.*;

@Entity
public class LostItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String itemType;

    private String itemImage;

    private String itemTitle;

    private String itemDescription;

    private Boolean foundStatus;

    // Constructors

    public LostItems() {
    }

    public LostItems(String itemType, String itemImage, String itemTitle,
                     String itemDescription, Boolean foundStatus, AppUser appUser) {
        this.itemType = itemType;
        this.itemImage = itemImage;
        this.itemTitle = itemTitle;
        this.itemDescription = itemDescription;
        this.foundStatus = foundStatus;
        this.appUser = appUser;
    }

    // Variable Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Boolean getFoundStatus() {
        return foundStatus;
    }

    public void setFoundStatus(Boolean foundStatus) {
        this.foundStatus = foundStatus;
    }


    // Connection to AppUsers
    @ManyToOne
    @JoinColumn
    private AppUser appUser;

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

}
