package com.alejandro.carritodecompras.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alejandro.carritodecompras.entities.DetailedPurchaseHistory;
import com.alejandro.carritodecompras.entities.Product;
import com.alejandro.carritodecompras.repositories.DetailedPurchaseHistoryRepository;
import com.alejandro.carritodecompras.repositories.ProductRepository;
import com.alejandro.carritodecompras.utils.UtilDetail;

@Service
public class DetailedPurchaseHistoryServiceImp implements DetailedPurchaseHistoryService {

    // To inject the repository dependency.
    @Autowired
    private DetailedPurchaseHistoryRepository repository;

    // To inject the repository dependency.
    @Autowired
    private ProductRepository productRepository;

    // -----------------------------
    // Methods for product entity
    // -----------------------------

    // To list all of products (records) in the table 'products'
    @Override
    @Transactional(readOnly = true)
    public List<DetailedPurchaseHistory> findAllByPurchaseHistoryId(Long id) {
        return (List<DetailedPurchaseHistory>) repository.findAll(); // cast because the method findAll returns an
                                                                     // iterable.
    }

    // To save a new detail in the db
    // and the relationship of this object with the product record.
    @Override
    @Transactional
    public DetailedPurchaseHistory addDetailPurchase(UtilDetail utilDetail) {
        // Search for a product.
        DetailedPurchaseHistory detail = new DetailedPurchaseHistory();
        Optional<Product> optionalProduct = productRepository.findById(utilDetail.getIdProduct());

        // If the product exists then
        optionalProduct.ifPresent(productDb -> {
            // Fill the object of 'many' class with information and
            // create the relationship between 'many' object and the 'one' object
            detail.setQuantity(utilDetail.getQuantity());
            detail.setTotal(productDb.getPrice() * utilDetail.getQuantity());

            detail.setProduct(productDb);
        });

        return repository.save(detail);
    }

    // To save many new details in the db
    // and the relationship of these objects with each product record.
    @Override
    @Transactional
    public List<DetailedPurchaseHistory> addDetailsPurchase(List<UtilDetail> utilDetails) {

        List<DetailedPurchaseHistory> details = new ArrayList<>();

        for (UtilDetail util : utilDetails) {

            // Search for a product.
            DetailedPurchaseHistory detail = new DetailedPurchaseHistory();
            Optional<Product> optionalProduct = productRepository.findById(util.getIdProduct());

            // If the product exists then
            optionalProduct.ifPresent(productDb -> {
                // Fill the object of 'many' class with information and
                // create the relationship between 'many' object and the 'one' object
                detail.setQuantity(util.getQuantity());
                detail.setTotal(productDb.getPrice() * util.getQuantity());

                detail.setProduct(productDb);
                details.add(detail);
            });
        }

        return (List<DetailedPurchaseHistory>) repository.saveAll(details);
    }

}
