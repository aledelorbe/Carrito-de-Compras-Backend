package com.alejandro.carritodecompras.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.alejandro.carritodecompras.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

    // --------------------------------
    // Custom queries
    // --------------------------------

    // To get all the available products (with status 1)
    List<Product> findByStatus(Long status);

    // To get all the available products (with status 1) with certain category
    List<Product> findByStatusAndCategory(Long status, String category);

    // To get all the available products (with status 1) with certain brand
    List<Product> findByStatusAndBrand(Long status, String brand);

}
