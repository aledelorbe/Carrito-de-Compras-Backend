package com.alejandro.carritodecompras.services;

import java.util.List;
import java.util.Optional;

import com.alejandro.carritodecompras.entities.PurchaseHistory;
import com.alejandro.carritodecompras.entities.User;
import com.alejandro.carritodecompras.services.dto.DetailedPurchaseHistoryDto;
import com.alejandro.carritodecompras.utils.UtilDetail;

public interface UserService {

    // Declaration of methods to use in 'serviceImp' file

    // -----------------------------
    // Methods for user entity
    // -----------------------------

    public List<User> findAll();

    public Optional<User> findById(Long id);

    public User save(User user);

    public Optional<User> update(Long id, User user);

    public Optional<User> deleteById(Long id);

    // -----------------------------
    // Methods for purchase entity
    // -----------------------------

    public User addPurchaseToUser(User userDb, List<UtilDetail> utilDetails);

    public List<PurchaseHistory> getPurchasesByUserId(Long userId);
    
    public List<DetailedPurchaseHistoryDto> getDetailsOfPurchaseByUserId(Long userId, Long purchaseId);
}
