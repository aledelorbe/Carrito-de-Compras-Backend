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

import com.alejandro.carritodecompras.entities.PurchaseHistory;
import com.alejandro.carritodecompras.services.PurchaseHistoryService;
import com.alejandro.carritodecompras.utils.UtilDetail;
import com.alejandro.carritodecompras.utils.UtilValidation;

import jakarta.validation.Valid;

@RestController // To create a api rest.
@RequestMapping("/api/purchases") // To create a base path.
public class PurchaseHistoryController {

    // To Inject the service dependency
    @Autowired
    private PurchaseHistoryService service;

    @Autowired
    private UtilValidation utilValidation;

    // -----------------------------
    // Methods for PurchaseHistory entity
    // -----------------------------

    // To create an endpoint that allows invocating the method saveDetailedsPurchaseHistory.
    // The annotation called 'RequestBody' allows receiving data of many products
    // This endpoint is only used to test the 'addPurchase' method ****
    @PostMapping("/many")
    public ResponseEntity<?> savePurchase(@Valid @RequestBody List<UtilDetail> utilDetails, BindingResult result) {
        // To handle the obligations of object attributes
        if (result.hasFieldErrors()) {
            return utilValidation.validation(result);
        }

        // When a new detail is created to respond return the same detail
        PurchaseHistory newPurchaseHistory = service.addPurchase(utilDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPurchaseHistory);
    }
}
