package com.example.demo.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
}
