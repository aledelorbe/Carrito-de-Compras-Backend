package com.alejandro.carritodecompras.services;

import java.util.List;

import com.alejandro.carritodecompras.entities.PurchaseHistory;
import com.alejandro.carritodecompras.utils.UtilDetail;

public interface PurchaseHistoryService {
    // Declaration of methods to use in 'serviceImp' file

    // -----------------------------
    // Methods for PurchaseHistory entity
    // -----------------------------

    public PurchaseHistory addPurchase(List<UtilDetail> utilDetails);

}
