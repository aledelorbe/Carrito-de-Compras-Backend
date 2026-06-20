package com.alejandro.carritodecompras.purchase.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alejandro.carritodecompras.product.models.entities.Product;
import com.alejandro.carritodecompras.product.repositories.ProductRepository;
import com.alejandro.carritodecompras.purchase.models.dtos.CartItemRequest;
import com.alejandro.carritodecompras.purchase.models.entities.PurchaseHistory;
import com.alejandro.carritodecompras.purchase.repositories.PurchaseHistoryRepository;


@Service
public class PurchaseHistoryServiceImp implements PurchaseHistoryService {

    // To inject the repository dependency.
    @Autowired
    private PurchaseHistoryRepository repository;

    // To inject the repository dependency.
    @Autowired
    private ProductRepository productRepository;

    // To inject the repository dependency.
    @Autowired
    private DetailedPurchaseHistoryService detailedRepository;

    // -----------------------------
    // Methods for purchase_history entity
    // -----------------------------

    // To save a purchase and the details of this purchase in the db
    @Override
    @Transactional
    public PurchaseHistory addPurchase(List<CartItemRequest> utilDetails) {

        // To set the information of a new purchase
        PurchaseHistory newPurchaseHistory = new PurchaseHistory();
        Double total = 0.0;
        boolean isFirstCicle = true;

        for (CartItemRequest util : utilDetails) {
            // Search for a product.
            Optional<Product> optionalProduct = productRepository.findById(util.getIdProduct());

            // If the product exists then
            if (optionalProduct.isPresent()) {
                Product productDb = optionalProduct.get();
                total += productDb.getPrice() * util.getQuantity();

                if (isFirstCicle) {
                    // Special action for the first cicle
                    newPurchaseHistory.setFirstImage(productDb.getImage());
                    isFirstCicle = false; // change the state
                }
            }
        }
        newPurchaseHistory.setTotal(total);
        newPurchaseHistory.setDetails(detailedRepository.addDetailsPurchase(utilDetails));
        // the date is set in the 'PurchaseHistory' entity

        return repository.save(newPurchaseHistory);
    }
}
