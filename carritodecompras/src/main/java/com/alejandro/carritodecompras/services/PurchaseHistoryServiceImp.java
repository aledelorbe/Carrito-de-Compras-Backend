package com.alejandro.carritodecompras.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alejandro.carritodecompras.entities.Product;
import com.alejandro.carritodecompras.entities.PurchaseHistory;
import com.alejandro.carritodecompras.repositories.ProductRepository;
import com.alejandro.carritodecompras.repositories.PurchaseHistoryRepository;

import utils.UtilDetail;

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

    // To save many new details in the db
    // and the relationship of these objects with each product record.
    @Override
    @Transactional
    public PurchaseHistory addPurchase(List<UtilDetail> utilDetails) {

        PurchaseHistory newPurchaseHistory = new PurchaseHistory();
        Double total = 0.0;
        boolean isFirstCicle = true;

        for (UtilDetail util : utilDetails) {
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

        return repository.save(newPurchaseHistory);

        // PurchaseHistory purchaseHistoryDb = repository.save(newPurchaseHistory);

        // List<DetailedPurchaseHistory> details = new ArrayList<>();

        // for (UtilDetail util : utilDetails) {

        // // Search for a product.
        // DetailedPurchaseHistory detail = new DetailedPurchaseHistory();
        // Optional<Product> optionalProduct =
        // productRepository.findById(util.getIdProduct());

        // // If the product exists then
        // optionalProduct.ifPresent(productDb -> {
        // // Fill the object of 'many' class with information and
        // // create the relationship between 'many' object and the 'one' object
        // detail.setQuantity(util.getQuantity());
        // detail.setTotal(productDb.getPrice() * util.getQuantity());

        // detail.setProduct(productDb);
        // details.add(detail);
        // });
        // }

        // return (List<DetailedPurchaseHistory>) repository.saveAll(details);
    }

}
