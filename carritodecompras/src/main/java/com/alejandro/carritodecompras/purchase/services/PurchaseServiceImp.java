package com.alejandro.carritodecompras.purchase.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alejandro.carritodecompras.exceptions.ResourceNotFoundException;
import com.alejandro.carritodecompras.purchase.models.dtos.CartItemRequestDto;
import com.alejandro.carritodecompras.purchase.models.dtos.DetailedPurchaseHistoryDto;
import com.alejandro.carritodecompras.purchase.models.entities.PurchaseHistory;
import com.alejandro.carritodecompras.user.models.entities.User;
import com.alejandro.carritodecompras.user.repositories.UserRepository;


@Service
public class PurchaseServiceImp implements PurchaseService {

    // To inject the repository dependency.
    @Autowired
    private PurchaseHistoryService purchaseHistoryService;

    // To inject the repository dependency.
    @Autowired
    private UserRepository userRepository;

    // ENDPOINTS FOR THE USER ROLE -----------------------------
    
    // -----------------------------
    // Methods for purchase entity
    // -----------------------------

    // To add new purchase to user
    @Override
    @Transactional
    public User addPurchaseToUser(Long userId, List<CartItemRequestDto> cartItemRequestDtos) {
        User userDb = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario con ID " + userId + " no existe."));

        PurchaseHistory purchaseDb = purchaseHistoryService.addPurchase(cartItemRequestDtos);
        userDb.getPurchases().add(purchaseDb);

        return userRepository.save(userDb);
    }

    // To get all the purchases of a certain user
    @Override
    @Transactional(readOnly = true)
    public List<PurchaseHistory> getPurchasesByUserId(Long userId) {
        return userRepository.getPurchasesByUserId(userId);
    }

    // To get all the details of a certain purchase of a certain user
    @Override
    @Transactional(readOnly = true)
    public List<DetailedPurchaseHistoryDto> getDetailsOfPurchaseByUserId(Long userId, Long purchaseId) {
        return userRepository.getDetailsOfPurchaseByUserId(userId, purchaseId);
    }

}
