package com.alejandro.carritodecompras.product.services;


import java.util.List;
import java.util.Optional;

import com.alejandro.carritodecompras.product.models.dtos.PageResponseDto;
import com.alejandro.carritodecompras.product.models.dtos.ProductUserResponseProjection;
import com.alejandro.carritodecompras.product.models.entities.Product;
import com.alejandro.carritodecompras.product.models.projections.ProductSearchProjection;


public interface ProductService {

    // Declaration of methods to use in 'serviceImp' file

    // ENDPOINTS FOR THE ADMIN ROLE -----------------------------

    PageResponseDto<Product> findAllPerGroup(int page, int pageSize);

    Optional<Product> findById(Long id);

    Product save(Product product);

    Optional<Product> update(Long id, Product product);

    Optional<Product> updateStatusByProductId(Long id);

    PageResponseDto<Product> findAllProductsByCategory(String category, int page, int pageSize);

    PageResponseDto<Product> findAllProductsByBrand(String brand, int page, int pageSize);

    List<Product> findTop10ByNameContainingIgnoreCase(String name);


    // ENDPOINTS FOR THE PUBLIC ROLE -----------------------------

    Optional<ProductUserResponseProjection> findPublicProductById(Long id);
    
    List<ProductSearchProjection> findTop10ByStatusAndNameContainingIgnoreCase(Long status, String name);

    PageResponseDto<ProductUserResponseProjection> findAllAvailableProducts(Long status, int page, int pageSize);

    PageResponseDto<ProductUserResponseProjection> findAllAvailableProductsByCategory(Long status, String category, int page, int pageSize);

    PageResponseDto<ProductUserResponseProjection> findAllAvailableProductsByBrand(Long status, String brand, int page, int pageSize);
    
}
