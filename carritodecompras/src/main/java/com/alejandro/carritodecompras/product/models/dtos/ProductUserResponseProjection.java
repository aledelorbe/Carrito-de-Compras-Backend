package com.alejandro.carritodecompras.product.models.dtos;


public interface ProductUserResponseProjection {
    Long getId();
    String getName();
    String getDescription();
    String getBrand();
    String getCategory();
    Double getPrice();
    String getImage();
}
