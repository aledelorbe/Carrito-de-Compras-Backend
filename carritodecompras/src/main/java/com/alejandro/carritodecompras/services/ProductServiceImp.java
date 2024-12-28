package com.alejandro.carritodecompras.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alejandro.carritodecompras.entities.Product;
import com.alejandro.carritodecompras.repositories.ProductRepository;

@Service
public class ProductServiceImp implements ProductService {

    // To inject the repository dependency.
    @Autowired
    private ProductRepository repository;

    // -----------------------------
    // Methods for product entity
    // -----------------------------

    // To list all of products (records) in the table 'products'
    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return (List<Product>) repository.findAll(); // cast because the method findAll returns an iterable.
    }

    // To get a specific product based on its id
    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
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

    // -----------------------------
    // Methods for custom queries of product entity
    // -----------------------------

    // To get all the available products (with status 1)
    @Override
    @Transactional(readOnly = true)
    public List<Product> findAllAvailableProducts(Long status) {
        return repository.findByStatus(status);
    }

    // To get all the available products (with status 1) with certain category.
    @Override
    @Transactional(readOnly = true)
    public List<Product> findAllAvailableProductsByCategory(Long status, String category) {
        return repository.findByStatusAndCategory(status, category);
    }

    // To get all the available products (with status 1) with certain brand.
    @Override
    @Transactional(readOnly = true)
    public List<Product> findAllAvailableProductsByBrand(Long status, String brand) {
        return repository.findByStatusAndBrand(status, brand);
    }

}
