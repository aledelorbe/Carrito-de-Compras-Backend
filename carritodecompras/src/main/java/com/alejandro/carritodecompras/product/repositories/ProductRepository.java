package com.alejandro.carritodecompras.product.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alejandro.carritodecompras.product.models.entities.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {

    // --------------------------------
    // Custom queries
    // --------------------------------

    // To get all the available products (with status 1)
    Page<Product> findByStatus(Long status, Pageable pageable);

    // To get all the available products (with status 1) with certain category
    Page<Product> findByStatusAndCategory(Long status, String category, Pageable pageable);

    // To get all the available products (with status 1) with certain brand
    Page<Product> findByStatusAndBrand(Long status, String brand, Pageable pageable);

}
