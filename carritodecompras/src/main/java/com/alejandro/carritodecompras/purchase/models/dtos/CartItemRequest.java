package com.alejandro.carritodecompras.purchase.models.dtos;

import jakarta.validation.constraints.NotNull;

// This class is used to create auxiliar objects
// These objects are received of a shop cart (a purchase) with this structure
public class CartItemRequest {

    @NotNull // To obligate to this attribute not to empty
    private Long idProduct;

    @NotNull // To obligate to this attribute not to empty
    private Long quantity;

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
