package com.alejandro.carritodecompras.purchase.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.carritodecompras.purchase.enums.OrderStatus;
import com.alejandro.carritodecompras.purchase.services.PurchaseHistoryService;

import jakarta.validation.constraints.Min;


@RestController // To create an api rest.
@RequestMapping("/api/purchases") // To create a base path.
@Validated // To enable validation for request parameters.
public class PurchaseHistoryController {

    // To Inject the service dependency
    @Autowired
    private PurchaseHistoryService purchaseHistoryService;

    // @Autowired
    // private UtilValidation utilValidation;

    // -----------------------------
    // Methods for PurchaseHistory entity
    // -----------------------------

    // // To create an endpoint that allows invoking the 'addPurchase' method.
    // // The annotation called 'RequestBody' allows receiving data of many products
    // // This endpoint is only used to test the 'addPurchase' method ****
    // @PostMapping("/many")
    // public ResponseEntity<?> savePurchase(@Valid @RequestBody List<CartItemRequestDto> utilDetails, BindingResult result) {
    //     // To handle the obligations of object attributes
    //     if (result.hasFieldErrors()) {
    //         return utilValidation.validation(result);
    //     }

    //     // When a new purchase is created to respond return the same purchase
    //     PurchaseHistory newPurchaseHistory = service.addPurchase(utilDetails);
    //     return ResponseEntity.status(HttpStatus.CREATED).body(newPurchaseHistory);
    // }

    @GetMapping("/admin/order-status/{orderStatus}")
    public ResponseEntity<?> savePurchase(@PathVariable OrderStatus orderStatus,
        @RequestParam(defaultValue = "0") 
        @Min(value = 0, message = "The page number must be greater than or equal to 0")
        int page,
        @RequestParam(defaultValue = "5") 
        @Min(value = 5, message = "The page size must be greater than or equal to 5")
        int size
    ) {
        return ResponseEntity.ok(purchaseHistoryService.findByStatus(orderStatus, page, size));
    }
    
}
