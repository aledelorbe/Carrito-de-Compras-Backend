package com.alejandro.carritodecompras.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alejandro.carritodecompras.entities.PurchaseHistory;
import com.alejandro.carritodecompras.entities.User;
import com.alejandro.carritodecompras.repositories.UserRepository;
import com.alejandro.carritodecompras.utils.UtilDetail;

@Service
public class UserServiceImp implements UserService {

    // To inject the repository dependency.
    @Autowired
    private UserRepository repository;

    // To inject the repository dependency.
    @Autowired
    private PurchaseHistoryService purchaseHistoryService;

    // -----------------------------
    // Methods for user entity
    // -----------------------------

    // To list all of users (records) in the table 'users'
    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) repository.findAll(); // cast because the method findAll returns an iterable.
    }

    // To get a specific user based on its id
    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    // To save a new user in the db
    // This method is a 'join point'
    @Override
    @Transactional
    public User save(User user) {
        return repository.save(user);
    }

    // To update a specific user based on its id
    @Override
    @Transactional
    public Optional<User> update(Long id, User user) {
        // Find a specific user
        Optional<User> optionalUser = repository.findById(id);

        // If the user is present then...
        if (optionalUser.isPresent()) {
            // update that record and return an optional value
            User userDb = optionalUser.get();

            userDb.setName(user.getName());
            userDb.setLastname(user.getLastname());

            return Optional.ofNullable(repository.save(userDb));
        }

        return optionalUser;
    }

    // To delete a specific user based on its id
    @Override
    @Transactional
    public Optional<User> deleteById(Long id) {
        // Search a specific user
        Optional<User> optionalUser = repository.findById(id);

        // If the user is present then delete that user
        optionalUser.ifPresent(userDb -> {
            repository.deleteById(id);
        });

        return optionalUser;
    }

    // -----------------------------
    // Methods for purchase entity
    // -----------------------------

    // To add new purchase to user
    @Override
    @Transactional
    public User addPurchaseToUser(User userDb, List<UtilDetail> utilDetails) {
        PurchaseHistory purchaseDb = purchaseHistoryService.addPurchase(utilDetails);

        userDb.getPurchases().add(purchaseDb);

        return repository.save(userDb);
    }
}
