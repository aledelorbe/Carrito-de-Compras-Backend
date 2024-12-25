package com.alejandro.carritodecompras.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.carritodecompras.entities.DetailedPurchaseHistory;
import com.alejandro.carritodecompras.services.DetailedPurchaseHistoryService;

import jakarta.validation.Valid;
import utils.UtilDetail;

@RestController // To create a api rest.
@RequestMapping("/api/details") // To create a base path.
public class DetailedPurchaseHistoryController {

    // To Inject the service dependency
    @Autowired
    private DetailedPurchaseHistoryService service;

    // -----------------------------
    // Methods for DetailedPurchaseHistory entity
    // -----------------------------

    // To create an endpoint that allows invocating the method addDetailProduct.
    // The annotation called 'RequestBody' allows receiving data of a product
    // This endpoint is only used to test the 'addDetailProduct' method ****
    @PostMapping()
    public ResponseEntity<?> saveDetailedPurchaseHistory(@Valid @RequestBody UtilDetail utilDetail, BindingResult result) {
        // To handle the obligations of object attributes
        if (result.hasFieldErrors()) {
            return validation(result);
        }

        // When a new detail is created to respond return the same detail
        DetailedPurchaseHistory newDetailedPurchaseHistory = service.addDetailProduct(utilDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDetailedPurchaseHistory);
    }

    // To create an endpoint that allows invocating the method saveDetailedsPurchaseHistory.
    // The annotation called 'RequestBody' allows receiving data of many products
    // This endpoint is only used to test the 'addDetailProducts' method ****
    @PostMapping("/many")
    public ResponseEntity<?> saveDetailedsPurchaseHistory(@Valid @RequestBody List<UtilDetail> utilDetails, BindingResult result) {
        // To handle the obligations of object attributes
        if (result.hasFieldErrors()) {
            return validation(result);
        }

        // When a new detail is created to respond return the same detail
        List<DetailedPurchaseHistory> newDetailedsPurchaseHistory = service.addDetailProducts(utilDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDetailedsPurchaseHistory);
    }

    // -----------------------------
    // Method to validate
    // -----------------------------

    // To send a JSON object with messages about the obligations of each object
    // attribute
    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(e -> {
            errors.put(e.getField(), "El campo " + e.getField() + " " + e.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
}