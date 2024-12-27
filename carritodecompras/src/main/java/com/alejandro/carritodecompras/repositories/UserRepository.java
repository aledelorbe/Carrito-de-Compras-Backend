package com.alejandro.carritodecompras.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.alejandro.carritodecompras.entities.PurchaseHistory;
import com.alejandro.carritodecompras.entities.User;
import com.alejandro.carritodecompras.services.dto.DetailedPurchaseHistoryDto;

public interface UserRepository extends CrudRepository<User, Long> {

    // --------------------------------
    // Custom queries
    // --------------------------------

    // To get all purchases of a certain user
    @Query("SELECT u.purchases FROM User u INNER JOIN u.purchases WHERE u.id = ?1")
    List<PurchaseHistory> getPurchasesByUserId(Long id_user);

    // To get all the details of a certain purchase of a certain user
    @Query("""
            SELECT new com.alejandro.carritodecompras.services.dto.DetailedPurchaseHistoryDto(d, pro.name, pro.image) 
            FROM User u 
            INNER JOIN u.purchases p 
            INNER JOIN p.details d 
            INNER JOIN d.product pro 
            WHERE u.id = ?1 AND p.id = ?2""")
    List<DetailedPurchaseHistoryDto> getDetailsOfPurchaseByUserId(Long id_user, Long id_purchase);
}
