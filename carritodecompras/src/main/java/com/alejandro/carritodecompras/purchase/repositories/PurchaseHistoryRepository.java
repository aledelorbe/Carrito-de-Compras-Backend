package com.alejandro.carritodecompras.purchase.repositories;

// import org.springframework.data.jpa.repository.Query;

// import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.alejandro.carritodecompras.purchase.models.entities.PurchaseHistory;

public interface PurchaseHistoryRepository extends CrudRepository<PurchaseHistory, Long>{


}
