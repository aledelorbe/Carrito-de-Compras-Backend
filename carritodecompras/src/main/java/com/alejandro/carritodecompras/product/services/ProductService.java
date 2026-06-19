package com.alejandro.carritodecompras.product.services;


import java.util.List;
import java.util.Optional;

import com.alejandro.carritodecompras.product.models.dtos.PageResponseDto;
import com.alejandro.carritodecompras.product.models.dtos.ProductUserResponseProjection;
import com.alejandro.carritodecompras.product.models.entities.Product;
import com.alejandro.carritodecompras.product.models.projections.ProductSearchProjection;


public interface ProductService {

    // Declaration of methods to use in 'serviceImp' file

    // -----------------------------
    // Methods for product entity
    // -----------------------------

    // ENDPOINTS FOR THE ADMIN ROLE -----------------------------

    PageResponseDto<Product> findAllPerGroup(int page, int pageSize);

    Optional<Product> findById(Long id);

    Product save(Product product);

    Optional<Product> update(Long id, Product product);

    Optional<Product> updateStatusByProductId(Long id);

    // ENDPOINTS FOR THE PUBLIC ROLE -----------------------------

    Optional<ProductUserResponseProjection> findPublicProductById(Long id);
    
    List<ProductSearchProjection> findTop10ByStatusAndNameContainingIgnoreCase(Long status, String name);

    // -----------------------------
    // Methods for custom queries of product entity
    // -----------------------------

    PageResponseDto<ProductUserResponseProjection> findAllAvailableProducts(Long status, int page, int pageSize);

    PageResponseDto<ProductUserResponseProjection> findAllAvailableProductsByCategory(Long status, String category, int page, int pageSize);

    PageResponseDto<ProductUserResponseProjection> findAllAvailableProductsByBrand(Long status, String brand, int page, int pageSize);
    
}
