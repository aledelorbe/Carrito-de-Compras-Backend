package com.alejandro.carritodecompras.purchase.repositories;


import org.springframework.data.repository.CrudRepository;

import com.alejandro.carritodecompras.purchase.models.entities.DetailedPurchaseHistory;


public interface DetailedPurchaseHistoryRepository extends CrudRepository<DetailedPurchaseHistory, Long> {
    
}
