package com.alejandro.carritodecompras.purchase.repositories;


import org.springframework.data.repository.CrudRepository;

import com.alejandro.carritodecompras.purchase.enums.OrderStatus;
import com.alejandro.carritodecompras.purchase.models.entities.PurchaseHistory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PurchaseHistoryRepository extends CrudRepository<PurchaseHistory, Long> {

    Page<PurchaseHistory> findByStatus(OrderStatus orderStatus, Pageable pageable);

}
