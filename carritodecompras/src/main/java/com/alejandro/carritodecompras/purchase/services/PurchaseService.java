package com.alejandro.carritodecompras.purchase.services;


import java.util.List;

import com.alejandro.carritodecompras.purchase.models.dtos.CartItemRequestDto;
import com.alejandro.carritodecompras.purchase.models.dtos.DetailedPurchaseHistoryDto;
import com.alejandro.carritodecompras.purchase.models.entities.PurchaseHistory;
import com.alejandro.carritodecompras.user.models.entities.User;


public interface PurchaseService {

    // ENDPOINTS FOR THE USER ROLE -----------------------------
    
    // -----------------------------
    // Methods for purchase entity
    // -----------------------------

    User addPurchaseToUser(Long userId, List<CartItemRequestDto> utilDetails);

    List<PurchaseHistory> getPurchasesByUserId(Long userId);
    
    List<DetailedPurchaseHistoryDto> getDetailsOfPurchaseByUserId(Long userId, Long purchaseId);

}
