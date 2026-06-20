package com.alejandro.carritodecompras.user.repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alejandro.carritodecompras.purchase.models.dtos.DetailedPurchaseHistoryDto;
import com.alejandro.carritodecompras.purchase.models.entities.PurchaseHistory;
import com.alejandro.carritodecompras.user.models.entities.User;
import com.alejandro.carritodecompras.user.models.projections.UserResponseProjection;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;


public interface UserRepository extends JpaRepository<User, Long> {

    // To get all user
    @Query(value = "SELECT id, name, lastname FROM user", nativeQuery = true)
    Page<UserResponseProjection> getAllUsers(Pageable pageable);

    // To get an specific user based on its id
    @Query(value = """
    SELECT
        id_user AS id,
        name,
        lastname
    FROM user
    WHERE id_user = :id
    """, nativeQuery = true)
    Optional<UserResponseProjection> findOwnerUserById(Long id);

    List<UserResponseProjection> findTop10ByNameContainingIgnoreCase(String name);

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
