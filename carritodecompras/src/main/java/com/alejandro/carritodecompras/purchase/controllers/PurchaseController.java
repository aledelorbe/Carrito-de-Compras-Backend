package com.alejandro.carritodecompras.purchase.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.carritodecompras.purchase.models.dtos.CartItemRequestDto;
import com.alejandro.carritodecompras.purchase.services.PurchaseService;
import com.alejandro.carritodecompras.user.models.entities.User;
import com.alejandro.carritodecompras.user.services.UserService;
import com.alejandro.carritodecompras.utils.UtilValidation;

import jakarta.validation.Valid;


@RestController // To create a api rest.
@RequestMapping("/api/purchases") // To create a base path.
public class PurchaseController {

    // To Inject the service dependency
    @Autowired
    private PurchaseService purchaseService;

    // To Inject the service dependency
    @Autowired
    private UserService userService;

    @Autowired
    private UtilValidation utilValidation;


    // ENDPOINTS FOR THE ADMIN ROLE -----------------------------

    // To create an endpoint that allows invoking the 'getPurchasesByUserId' method.
    // The annotation called 'RequestBody' allows receiving data of a user
    @GetMapping("/{userId}/purchases")
    public ResponseEntity<?> getPurchasesByUserId(@PathVariable Long userId) {

        // Search for a specific user if it exists then invoke the 'getPurchasesByUserId' method.
        Optional<User> optionalUser = userService.findById(userId);

        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(purchaseService.getPurchasesByUserId(userId));
        }
        // Else returns code response 404
        return ResponseEntity.notFound().build();
    }

    // ENDPOINTS FOR THE USER ROLE -----------------------------

    // -----------------------------
    // Methods for purchase entity
    // -----------------------------

    // To create an endpoint that allows invoking the 'addPurchaseToUser' method.
    // The annotation called 'RequestBody' allows receiving data of a user
    @PostMapping("/{userId}/purchase")
    public ResponseEntity<?> addPurchaseToUser(@Valid @RequestBody List<CartItemRequestDto> cartItemRequestDto, BindingResult result, @PathVariable Long userId) {
        // To handle the obligations of object attributes
        if (result.hasFieldErrors()) {
            return utilValidation.validation(result);
        }

        if (cartItemRequestDto == null || cartItemRequestDto.isEmpty()) {
            java.util.Map<String, String> errors = new java.util.HashMap<>();
            errors.put("cart", "El carrito de compras no puede estar vacío.");
            return ResponseEntity.badRequest().body(errors);
        }

        java.util.Map<String, String> validationErrors = new java.util.HashMap<>();
        for (int i = 0; i < cartItemRequestDto.size(); i++) {
            CartItemRequestDto item = cartItemRequestDto.get(i);
            if (item == null) {
                validationErrors.put("items[" + i + "]", "El item no puede ser nulo.");
                continue;
            }
            if (item.getIdProduct() == null) {
                validationErrors.put("items[" + i + "].idProduct", "El campo idProduct no puede ser nulo.");
            }
            if (item.getQuantity() == null) {
                validationErrors.put("items[" + i + "].quantity", "El campo quantity no puede ser nulo.");
            } else if (item.getQuantity() <= 0) {
                validationErrors.put("items[" + i + "].quantity", "El campo quantity debe ser mayor que 0.");
            }
        }

        if (!validationErrors.isEmpty()) {
            return ResponseEntity.badRequest().body(validationErrors);
        }

        User newUser = purchaseService.addPurchaseToUser(userId, cartItemRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
    
    // -----------------------------
    // Methods for detail entity
    // -----------------------------

    // To create an endpoint that allows invoking the 'getDetailsOfPurchaseByUserId' method.
    // The annotation called 'RequestBody' allows receiving data of a user
    @GetMapping("/{userId}/purchases/{purchaseId}")
    public ResponseEntity<?> getDetailsOfPurchaseByUserId(@PathVariable Long userId, @PathVariable Long purchaseId) {

        // Search for a specific user if it exists then invoke the 'getDetailsOfPurchaseByUserId' method.
        Optional<User> optionalUser = userService.findById(userId);

        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(purchaseService.getDetailsOfPurchaseByUserId(userId, purchaseId));
        }
        // Else returns code response 404
        return ResponseEntity.notFound().build();
    }

}

