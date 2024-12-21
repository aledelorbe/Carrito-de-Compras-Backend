package com.alejandro.carritodecompras.repositories;

import org.springframework.data.repository.CrudRepository;

import com.alejandro.carritodecompras.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
    
}
