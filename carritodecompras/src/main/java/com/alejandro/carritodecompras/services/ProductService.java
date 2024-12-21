package com.alejandro.carritodecompras.services;

import java.util.List;
import java.util.Optional;

import com.alejandro.carritodecompras.entities.Product;

public interface ProductService {

    // Declaration of methods to use in 'serviceImp' file

    // -----------------------------
    // Methods for product entity
    // -----------------------------

    public List<Product> findAll();

    public Optional<Product> findById(Long id);

    public Product save(Product product);

    public Optional<Product> update(Long id, Product product);

    public Optional<Product> updateStatusByProductId(Long id);
}
