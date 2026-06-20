package com.alejandro.carritodecompras.purchase.services;

import java.util.List;

import com.alejandro.carritodecompras.purchase.models.dtos.CartItemRequest;
import com.alejandro.carritodecompras.purchase.models.entities.PurchaseHistory;

public interface PurchaseHistoryService {
    
    // Declaration of methods to use in 'serviceImp' file

    // -----------------------------
    // Methods for PurchaseHistory entity
    // -----------------------------

    public PurchaseHistory addPurchase(List<CartItemRequest> utilDetails);

}
