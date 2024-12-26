package com.alejandro.carritodecompras.controllers;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.carritodecompras.entities.Product;

import com.alejandro.carritodecompras.services.ProductService;
import com.alejandro.carritodecompras.utils.UtilValidation;

@RestController // To create a api rest.
@RequestMapping("/api/products") // To create a base path.
public class ProductController {

    // To Inject the service dependency
    @Autowired
    private ProductService service;

    @Autowired
    private UtilValidation utilValidation;

    // -----------------------------
    // Methods for product entity
    // -----------------------------

    // To create an endpoint that allows invocating the method findAll.
    @GetMapping()
    public List<Product> products() {
        return service.findAll();
    }

    // To create an endpoint that allows invocating the method fingById.
    @GetMapping("/{id}")
    public ResponseEntity<?> product(@PathVariable Long id) {
        // Search a specific product and if it's present then return it.
        Optional<Product> optionalProduct = service.findById(id);
        if (optionalProduct.isPresent()) {
            return ResponseEntity.ok(optionalProduct.orElseThrow());
        }
        // Else returns code response 404
        return ResponseEntity.notFound().build();
    }

    // To create an endpoint that allows invocating the method save.
    // The annotation called 'RequestBody' allows receiving data of a product
    @PostMapping()
    public ResponseEntity<?> saveProduct(@Valid @RequestBody Product product, BindingResult result) {
        // To handle the obligations of object attributes
        if (result.hasFieldErrors()) {
            return utilValidation.validation(result);
        }

        // When a new product is created to respond return the same product
        product.setStatus(1L);
        Product newProduct = service.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    // To create an endpoint that allows update all of atributte values a specific
    // product based its id.
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody Product product, BindingResult result,
            @PathVariable Long id) {
        // To handle of obligations of object attributes
        if (result.hasFieldErrors()) {
            return utilValidation.validation(result);
        }

        // Find specific product and if it's present then return specific product
        Optional<Product> optionalProduct = service.update(id, product);

        if (optionalProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalProduct.orElseThrow());
        }
        // Else return code response 404
        return ResponseEntity.notFound().build();
    }

    // To create an endpoint that allows setting a status value for a specific
    // product based its id.
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateStatusProduct(@PathVariable Long id) {
        // Find specific product and if it's present then return specific product
        Optional<Product> optionalProduct = service.updateStatusByProductId(id);
        if (optionalProduct.isPresent()) {
            return ResponseEntity.ok(optionalProduct.orElseThrow());
        }
        // Else return code response 404
        return ResponseEntity.notFound().build();
    }
}
