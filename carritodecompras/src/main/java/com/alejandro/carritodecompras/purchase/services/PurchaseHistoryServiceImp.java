package com.alejandro.carritodecompras.purchase.services;


import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alejandro.carritodecompras.exceptions.NoStockException;
import com.alejandro.carritodecompras.exceptions.ResourceNotFoundException;
import com.alejandro.carritodecompras.product.models.dtos.PageResponseDto;
import com.alejandro.carritodecompras.product.models.entities.Product;
import com.alejandro.carritodecompras.product.repositories.ProductRepository;
import com.alejandro.carritodecompras.purchase.enums.OrderStatus;
import com.alejandro.carritodecompras.purchase.models.dtos.CartItemRequestDto;
import com.alejandro.carritodecompras.purchase.models.entities.PurchaseHistory;
import com.alejandro.carritodecompras.purchase.repositories.PurchaseHistoryRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


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
    public PurchaseHistory addPurchase(List<CartItemRequestDto> cartItemRequestDtos) {

        // To set the information of a new purchase
        PurchaseHistory newPurchaseHistory = new PurchaseHistory();
        Double total = 0.0;
        boolean isFirstCicle = true;

        List<CartItemRequestDto> sortedItems = cartItemRequestDtos.stream().sorted(Comparator.comparing(CartItemRequestDto::getIdProduct)).toList();
        for(CartItemRequestDto cartItem : sortedItems) {

            int rowsAffected = productRepository.decreaseStockAndSetUnavailable(cartItem.getIdProduct(), cartItem.getQuantity());

            if (rowsAffected == 0) {
                if (!productRepository.existsById(cartItem.getIdProduct())) {
                    throw new ResourceNotFoundException("The product with ID " + cartItem.getIdProduct() + " does not exist.");
                }

                throw new NoStockException();
            }
        }

        for (CartItemRequestDto cartItem : cartItemRequestDtos) {
            // Search for a product.
            Optional<Product> optionalProduct = productRepository.findById(cartItem.getIdProduct());

            // If the product exists then
            if (optionalProduct.isPresent()) {
                Product productDb = optionalProduct.get();
                total += productDb.getPrice() * cartItem.getQuantity();

                if (isFirstCicle) {
                    // Special action for the first cicle
                    newPurchaseHistory.setFirstImage(productDb.getImage());
                    isFirstCicle = false; // change the state
                }
            }
        }
        newPurchaseHistory.setTotal(total);
        newPurchaseHistory.setDetails(detailedRepository.addDetailsPurchase(cartItemRequestDtos));
        // the date is set in the 'PurchaseHistory' entity

        newPurchaseHistory.setStatus(OrderStatus.PAYMENT_PENDING);

        return repository.save(newPurchaseHistory);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponseDto<PurchaseHistory> findByStatus(OrderStatus orderStatus, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<PurchaseHistory> pageResult = repository.findByStatus(orderStatus, pageable);
        return PageResponseDto.fromPage(pageResult);
    }

}
