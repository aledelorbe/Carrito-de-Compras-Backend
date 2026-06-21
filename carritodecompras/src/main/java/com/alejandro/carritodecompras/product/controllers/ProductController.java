package com.alejandro.carritodecompras.product.controllers;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.carritodecompras.product.models.dtos.ProductUserResponseProjection;
import com.alejandro.carritodecompras.product.models.entities.Product;

import com.alejandro.carritodecompras.product.services.ProductService;
import com.alejandro.carritodecompras.utils.UtilValidation;


@RestController // To create an api rest.
@RequestMapping("/api/products") // To create a base path.
@Validated
public class ProductController {

    // To Inject the service dependency
    @Autowired
    private ProductService productService;

    @Autowired
    private UtilValidation utilValidation;


    // ENDPOINTS FOR THE ADMIN ROLE -----------------------------

    // To create an endpoint that allows invoking the findAllPerGroup method.
    @GetMapping("/admin/all")
    public ResponseEntity<?> getAllProducts(
        @RequestParam(defaultValue = "0") 
        @Min(value = 0, message = "The page number must be greater than or equal to 0")
        int page,
        @RequestParam(defaultValue = "5") 
        @Min(value = 5, message = "The page size must be greater than or equal to 5")
        int size) 
    {
        return ResponseEntity.ok(productService.findAllPerGroup(page, size));
    }

    // To create an endpoint that allows invoking the findById method.
    @GetMapping("/admin/{id}")
    public ResponseEntity<?> product(@PathVariable Long id) {
        // Search a specific product and if it's present then return it.
        Optional<Product> optionalProduct = productService.findById(id);

        if (optionalProduct.isPresent()) {
            return ResponseEntity.ok(optionalProduct.orElseThrow());
        }
        // Else returns code response 404
        return ResponseEntity.notFound().build();
    }
    
    // To create an endpoint that allows invoking the save method.
    // The annotation called 'RequestBody' allows receiving data of a product
    @PostMapping("/admin")
    public ResponseEntity<?> saveProduct(@Valid @RequestBody Product product, BindingResult result) {
        // To handle the obligations of object attributes
        if (result.hasFieldErrors()) {
            return utilValidation.validation(result);
        }
        
        // When a new product is created to respond return the same product
        product.setStatus(1L);
        Product newProduct = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }
    
    // To create an endpoint that allows update all of attribute values a specific
    // product based its id.
    @PutMapping("/admin/{id}")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody Product product, BindingResult result,
    @PathVariable Long id) {
        // To handle of obligations of object attributes
        if (result.hasFieldErrors()) {
            return utilValidation.validation(result);
        }
        
        // Find specific product and if it's present then return specific product
        Optional<Product> optionalProduct = productService.update(id, product);
        
        if (optionalProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalProduct.orElseThrow());
        }
        // Else return code response 404
        return ResponseEntity.notFound().build();
    }

    // To create an endpoint that allows setting a status value for a specific
    // product based its id.
    @PatchMapping("/admin/{id}")
    public ResponseEntity<?> updateStatusProduct(@PathVariable Long id) {
        // Find specific product and if it's present then return specific product
        Optional<Product> optionalProduct = productService.updateStatusByProductId(id);

        if (optionalProduct.isPresent()) {
            return ResponseEntity.ok(optionalProduct.orElseThrow());
        }
        // Else return code response 404
        return ResponseEntity.notFound().build();
    }

    // To create an endpoint that allows invoking the findAllProductsByCategory method.
    @GetMapping("/admin/category/{category}")
    public ResponseEntity<?> getProductsByCategory(@PathVariable String category,
        @RequestParam(defaultValue = "0") @Min(value = 0, message = "The page number must be greater than or equal to 0") int page,
        @RequestParam(defaultValue = "5") @Min(value = 5, message = "The page size must be greater than or equal to 5") int size
    ) {
        return ResponseEntity.ok(productService.findAllProductsByCategory(category, page, size));
    }

    // To create an endpoint that allows invoking the findAllProductsByBrand method.
    @GetMapping("/admin/brand/{brand}")
    public ResponseEntity<?> getProductsByBrand(@PathVariable String brand,
       @RequestParam(defaultValue = "0") @Min(value = 0, message = "The page number must be greater than or equal to 0") int page,
       @RequestParam(defaultValue = "5") @Min(value = 5, message = "The page size must be greater than or equal to 5") int size
    ) {
        return ResponseEntity.ok(productService.findAllProductsByBrand(brand, page, size));
    }

    // To create an endpoint that allows invoking the findTop10ByNameContainingIgnoreCase method.
    @GetMapping("/admin/name/{name}")
    public ResponseEntity<?> getProductByName(@PathVariable String name) {
        return ResponseEntity.ok(productService.findTop10ByNameContainingIgnoreCase(name));
    }

    // ENDPOINTS FOR THE PUBLIC ROLE -----------------------------
        
    // To create an endpoint that allows invoking the findById method.
    @GetMapping("/public/{id}")
    public ResponseEntity<?> getPublicProduct(@PathVariable Long id) {
        // Search a specific product and if it's present then return it.
        Optional<ProductUserResponseProjection> optionalProduct = productService.findPublicProductById(id);

        if (optionalProduct.isPresent()) {
            return ResponseEntity.ok(optionalProduct.orElseThrow());
        }
        // Else returns code response 404
        return ResponseEntity.notFound().build();
    }

    // To create an endpoint that allows invoking the findTop10ByStatusAndNameContainingIgnoreCase method.
    @GetMapping("/public/name/{name}")
    public ResponseEntity<?> getProductByNameAndStatus(@PathVariable String name) {
        return ResponseEntity.ok(productService.findTop10ByStatusAndNameContainingIgnoreCase(1L, name));
    }

    // To create an endpoint that allows invoking the method
    // findAllAvailableProducts.
    @GetMapping("/public/available")
    public ResponseEntity<?> availableProducts(
        @RequestParam(defaultValue = "0") 
        @Min(value = 0, message = "The page number must be greater than or equal to 0")
        int page,
        @RequestParam(defaultValue = "5") 
        @Min(value = 5, message = "The page size must be greater than or equal to 5")
        int size
    ) {
        return ResponseEntity.ok(productService.findAllAvailableProducts(1L, page, size));
    }

    // To create an endpoint that allows invoking the 
    // findAllAvailableProductsByCategory method.
    @GetMapping("/public/available/category/{category}")
    public ResponseEntity<?> getAvailableProductsByCategory(@PathVariable String category,
        @RequestParam(defaultValue = "0") @Min(value = 0, message = "The page number must be greater than or equal to 0") int page,
        @RequestParam(defaultValue = "5") @Min(value = 5, message = "The page size must be greater than or equal to 5") int size
    ) {
        return ResponseEntity.ok(productService.findAllAvailableProductsByCategory(1L, category, page, size));
    }

    // To create an endpoint that allows invoking the method
    // findAllAvailableProductsByBrand.
    @GetMapping("/public/available/brand/{brand}")
    public ResponseEntity<?> availableProductsByBrand(@PathVariable String brand,
        @RequestParam(defaultValue = "0") @Min(value = 0, message = "The page number must be greater than or equal to 0") int page,
        @RequestParam(defaultValue = "5") @Min(value = 5, message = "The page size must be greater than or equal to 5") int size
    ) {
        return ResponseEntity.ok(productService.findAllAvailableProductsByBrand(1L, brand, page, size));
    }

}
