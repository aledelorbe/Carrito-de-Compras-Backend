package com.alejandro.carritodecompras.repositories;

import org.springframework.data.repository.CrudRepository;

import com.alejandro.carritodecompras.entities.DetailedPurchaseHistory;

public interface DetailedPurchaseHistoryRepository extends CrudRepository<DetailedPurchaseHistory, Long> {
    
}
