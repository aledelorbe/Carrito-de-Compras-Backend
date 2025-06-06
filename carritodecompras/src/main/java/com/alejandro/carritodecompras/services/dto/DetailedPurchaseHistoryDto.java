package com.alejandro.carritodecompras.services.dto;

import com.alejandro.carritodecompras.entities.DetailedPurchaseHistory;

// To create a view for the 'DetailedPurchaseHistory' objects
// This view is used when a user requests to watch all the details of a certain purchase
public class DetailedPurchaseHistoryDto {

    private DetailedPurchaseHistory detail;
    
    private String name;
    
    private String image;

    public DetailedPurchaseHistoryDto(DetailedPurchaseHistory detail, String name, String image) {
        this.detail = detail;
        this.name = name;
        this.image = image;
    }

    public DetailedPurchaseHistory getDetail() {
        return detail;
    }

    public void setDetail(DetailedPurchaseHistory detail) {
        this.detail = detail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

