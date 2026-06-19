package com.alejandro.carritodecompras.product.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alejandro.carritodecompras.product.models.dtos.ProductSearchProjection;
import com.alejandro.carritodecompras.product.models.dtos.ProductUserResponseProjection;
import com.alejandro.carritodecompras.product.models.entities.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {

    // --------------------------------
    // Custom queries
    // --------------------------------

    @Query(value = """
    SELECT
        id_product AS id,
        name,
        description,
        brand,
        category,
        price,
        image
    FROM product
    WHERE id_product = :id
    """, nativeQuery = true)
    Optional<ProductUserResponseProjection> findPublicProductById(Long id);

    List<ProductSearchProjection> findTop10ByStatusAndNameContainingIgnoreCase(Long status, String name);
    
    // To get all the available products (with status 1)
    Page<ProductUserResponseProjection> findByStatus(Long status, Pageable pageable);

    // To get all the available products (with status 1) with certain category
    Page<ProductUserResponseProjection> findByStatusAndCategory(Long status, String category, Pageable pageable);

    // To get all the available products (with status 1) with certain brand
    Page<ProductUserResponseProjection> findByStatusAndBrand(Long status, String brand, Pageable pageable);

}
