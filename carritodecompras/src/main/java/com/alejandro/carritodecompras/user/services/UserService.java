package com.alejandro.carritodecompras.user.services;


import java.util.List;
import java.util.Optional;

import com.alejandro.carritodecompras.entities.PurchaseHistory;
import com.alejandro.carritodecompras.product.models.dtos.PageResponseDto;
import com.alejandro.carritodecompras.services.dto.DetailedPurchaseHistoryDto;
import com.alejandro.carritodecompras.user.models.dtos.UserResponseDto;
import com.alejandro.carritodecompras.user.models.entities.User;
import com.alejandro.carritodecompras.user.models.projections.UserResponseProjection;
import com.alejandro.carritodecompras.utils.UtilDetail;


public interface UserService {

    // Declaration of methods to use in 'serviceImp' file

    // -----------------------------
    // Methods for user entity
    // -----------------------------

    // ENDPOINTS FOR THE ADMIN ROLE -----------------------------

    PageResponseDto<UserResponseProjection> findAllPerGroup(int page, int pageSize);

    Optional<User> findById(Long id);

    List<UserResponseProjection> findTop10ByNameContainingIgnoreCase(String name);

    User save(User user);

    Optional<UserResponseDto> update(Long id, User user);

    Optional<UserResponseDto> deleteById(Long id);

    // ENDPOINTS FOR THE OWNER -----------------------------

    Optional<UserResponseProjection> findOwnerUserById(Long id);

    // -----------------------------
    // Methods for purchase entity
    // -----------------------------

    User addPurchaseToUser(User userDb, List<UtilDetail> utilDetails);

    List<PurchaseHistory> getPurchasesByUserId(Long userId);
    
    List<DetailedPurchaseHistoryDto> getDetailsOfPurchaseByUserId(Long userId, Long purchaseId);

}
