package com.alejandro.carritodecompras.product.services;


import java.util.List;
import java.util.Optional;

import com.alejandro.carritodecompras.product.models.dtos.PageResponseDto;
import com.alejandro.carritodecompras.product.models.entities.Product;


public interface ProductService {

    // Declaration of methods to use in 'serviceImp' file

    // -----------------------------
    // Methods for product entity
    // -----------------------------

    // ENDPOINTS FOR THE ADMIN ROLE -----------------------------

    PageResponseDto<Product> findAllPerGroup(int page, int pageSize);

    Product save(Product product);

    Optional<Product> update(Long id, Product product);

    Optional<Product> updateStatusByProductId(Long id);

    // ENDPOINTS FOR THE PUBLIC ROLE -----------------------------

    Optional<Product> findById(Long id);

    // -----------------------------
    // Methods for custom queries of product entity
    // -----------------------------

    PageResponseDto<Product> findAllAvailableProducts(Long status, int page, int pageSize);

    PageResponseDto<Product> findAllAvailableProductsByCategory(Long status, String category, int page, int pageSize);

    PageResponseDto<Product> findAllAvailableProductsByBrand(Long status, String brand, int page, int pageSize);
    
}
