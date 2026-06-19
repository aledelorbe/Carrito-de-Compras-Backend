package com.alejandro.carritodecompras.product.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alejandro.carritodecompras.product.models.dtos.PageResponseDto;
import com.alejandro.carritodecompras.product.models.dtos.ProductUserResponseProjection;
import com.alejandro.carritodecompras.product.models.entities.Product;
import com.alejandro.carritodecompras.product.repositories.ProductRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Service
public class ProductServiceImp implements ProductService {

    // To inject the repository dependency.
    @Autowired
    private ProductRepository repository;

    // -----------------------------
    // Methods for product entity
    // -----------------------------

    // ENDPOINTS FOR THE ADMIN ROLE -----------------------------

    // To list all of products (records) in the table 'products'
    @Override
    @Transactional(readOnly = true)
    public PageResponseDto<Product> findAllPerGroup(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Product> pageResult = repository.findAll(pageable);
        return PageResponseDto.fromPage(pageResult);
    }

    // To save a new product in the db
    // This method is a 'join point'
    @Override
    @Transactional
    public Product save(Product product) {
        return repository.save(product);
    }

    // To update a specific product based on its id
    @Override
    @Transactional
    public Optional<Product> update(Long id, Product product) {
        // Find a specific product
        Optional<Product> optionalProduct = repository.findById(id);

        // If the product is present then...
        if (optionalProduct.isPresent()) {
            // update that record and return an optional value
            Product productDb = optionalProduct.get();

            productDb.setName(product.getName());
            productDb.setDescription(product.getDescription());
            productDb.setBrand(product.getBrand());
            productDb.setCategory(product.getCategory());
            productDb.setPrice(product.getPrice());
            productDb.setImage(product.getImage());

            return Optional.ofNullable(repository.save(productDb));
        }

        return optionalProduct;
    }

    // To change the status value for a specific product based its id.
    @Override
    @Transactional
    public Optional<Product> updateStatusByProductId(Long id) {
        // Search for a specific product
        Optional<Product> optionalProduct = repository.findById(id);

        // If the product is present then change the status from inactive to
        // active or vice versa
        optionalProduct.ifPresent(productDb -> {
            if (productDb.getStatus() == 1) {
                productDb.setStatus(0L);
            } else {
                productDb.setStatus(1L);
            }
            repository.save(productDb);
        });

        return optionalProduct;
    }

    // ENDPOINTS FOR THE PUBLIC ROLE -----------------------------

    // To get a specific product based on its id
    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    // -----------------------------
    // Methods for custom queries of product entity
    // -----------------------------

    // To get all the available products (with status 1)
    @Override
    @Transactional(readOnly = true)
    public PageResponseDto<ProductUserResponseProjection> findAllAvailableProducts(Long status, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<ProductUserResponseProjection> pageResult = repository.findByStatus(status, pageable);
        return PageResponseDto.fromPage(pageResult);
    }

    // To get all the available products (with status 1) with certain category.
    @Override
    @Transactional(readOnly = true)
    public PageResponseDto<ProductUserResponseProjection> findAllAvailableProductsByCategory(Long status, String category, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<ProductUserResponseProjection> pageResult = repository.findByStatusAndCategory(status, category, pageable);
        return PageResponseDto.fromPage(pageResult);
    }

    // To get all the available products (with status 1) with certain brand.
    @Override
    @Transactional(readOnly = true)
    public PageResponseDto<ProductUserResponseProjection> findAllAvailableProductsByBrand(Long status, String brand, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<ProductUserResponseProjection> pageResult = repository.findByStatusAndBrand(status, brand, pageable);
        return PageResponseDto.fromPage(pageResult);
    }

}
