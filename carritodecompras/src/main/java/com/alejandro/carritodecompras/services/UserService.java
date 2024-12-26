package com.alejandro.carritodecompras.services;

import java.util.List;
import java.util.Optional;

import com.alejandro.carritodecompras.entities.User;

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
}
