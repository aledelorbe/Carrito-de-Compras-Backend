package com.alejandro.carritodecompras.repositories;

import org.springframework.data.repository.CrudRepository;

import com.alejandro.carritodecompras.entities.PurchaseHistory;

public interface PurchaseHistoryRepository extends CrudRepository<PurchaseHistory, Long>{

}
