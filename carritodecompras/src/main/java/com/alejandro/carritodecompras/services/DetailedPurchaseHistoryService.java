package com.alejandro.carritodecompras.services;

import java.util.List;

import com.alejandro.carritodecompras.entities.DetailedPurchaseHistory;
import com.alejandro.carritodecompras.utils.UtilDetail;

public interface DetailedPurchaseHistoryService {

    // Declaration of methods to use in 'serviceImp' file

    // -----------------------------
    // Methods for DetailedPurchaseHistory entity
    // -----------------------------

    public List<DetailedPurchaseHistory> findAllByPurchaseHistoryId(Long id);

    public DetailedPurchaseHistory addDetailPurchase(UtilDetail utilDetail);

    public List<DetailedPurchaseHistory> addDetailsPurchase(List<UtilDetail> utilDetails);

}
