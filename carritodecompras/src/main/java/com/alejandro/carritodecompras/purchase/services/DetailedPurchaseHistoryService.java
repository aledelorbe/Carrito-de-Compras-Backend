package com.alejandro.carritodecompras.purchase.services;


import java.util.List;

import com.alejandro.carritodecompras.purchase.models.dtos.CartItemRequestDto;
import com.alejandro.carritodecompras.purchase.models.entities.DetailedPurchaseHistory;


public interface DetailedPurchaseHistoryService {

    // Declaration of methods to use in 'serviceImp' file

    // -----------------------------
    // Methods for DetailedPurchaseHistory entity
    // -----------------------------

    public DetailedPurchaseHistory addDetailPurchase(CartItemRequestDto utilDetail);

    public List<DetailedPurchaseHistory> addDetailsPurchase(List<CartItemRequestDto> utilDetails);

}
