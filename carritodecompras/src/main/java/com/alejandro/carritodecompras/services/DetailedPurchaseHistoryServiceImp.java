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

import utils.UtilDetail;

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
    public DetailedPurchaseHistory addDetailProduct(UtilDetail utilDetail) {
        DetailedPurchaseHistory detail = new DetailedPurchaseHistory();
        Optional<Product> optionalProduct = productRepository.findById(utilDetail.getIdProduct());

        optionalProduct.ifPresent(productDb -> {
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
    public List<DetailedPurchaseHistory> addDetailProducts(List<UtilDetail> utilDetails) {

        List<DetailedPurchaseHistory> details = new ArrayList<>();

        for(UtilDetail util: utilDetails) {

            DetailedPurchaseHistory detail = new DetailedPurchaseHistory();
            Optional<Product> optionalProduct = productRepository.findById(util.getIdProduct());
    
            optionalProduct.ifPresent(productDb -> {
                detail.setQuantity(util.getQuantity());
                detail.setTotal(productDb.getPrice() * util.getQuantity());
    
                detail.setProduct(productDb);
                details.add(detail);
            });
        }

        return (List<DetailedPurchaseHistory>) repository.saveAll(details);
    }

}
