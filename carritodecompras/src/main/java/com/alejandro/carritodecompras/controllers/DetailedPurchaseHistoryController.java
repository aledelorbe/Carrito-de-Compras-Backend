package com.alejandro.carritodecompras.controllers;

import java.util.List;

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
import com.alejandro.carritodecompras.utils.UtilDetail;
import com.alejandro.carritodecompras.utils.UtilValidation;

import jakarta.validation.Valid;

@RestController // To create a api rest.
@RequestMapping("/api/details") // To create a base path.
public class DetailedPurchaseHistoryController {

    // To Inject the service dependency
    @Autowired
    private DetailedPurchaseHistoryService service;

    @Autowired
    private UtilValidation utilValidation;

    // -----------------------------
    // Methods for DetailedPurchaseHistory entity
    // -----------------------------

    // To create an endpoint that allows invoking the 'addDetailPurchase' method.
    // The annotation called 'RequestBody' allows receiving data of a product
    // This endpoint is only used to test the 'addDetailPurchase' method ****
    @PostMapping()
    public ResponseEntity<?> saveDetailPurchase(@Valid @RequestBody UtilDetail utilDetail, BindingResult result) {
        // To handle the obligations of object attributes
        if (result.hasFieldErrors()) {
            return utilValidation.validation(result);
        }

        // When a new detail is created to respond return the same detail
        DetailedPurchaseHistory newDetailedPurchaseHistory = service.addDetailPurchase(utilDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDetailedPurchaseHistory);
    }

    // To create an endpoint that allows invoking the 'addDetailsPurchase' method.
    // The annotation called 'RequestBody' allows receiving data of many products
    // This endpoint is only used to test the 'addDetailsPurchase' method ****
    @PostMapping("/many")
    public ResponseEntity<?> saveDetailsPurchase(@Valid @RequestBody List<UtilDetail> utilDetails, BindingResult result) {
        // To handle the obligations of object attributes
        if (result.hasFieldErrors()) {
            return utilValidation.validation(result);
        }

        // When many new details are created to respond return the same many details
        List<DetailedPurchaseHistory> newDetailedsPurchaseHistory = service.addDetailsPurchase(utilDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDetailedsPurchaseHistory);
    }
}