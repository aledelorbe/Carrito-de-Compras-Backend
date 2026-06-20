package com.alejandro.carritodecompras.user.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alejandro.carritodecompras.product.models.dtos.PageResponseDto;
import com.alejandro.carritodecompras.user.models.dtos.UserResponseDto;
import com.alejandro.carritodecompras.user.models.entities.User;
import com.alejandro.carritodecompras.user.models.projections.UserResponseProjection;
import com.alejandro.carritodecompras.user.repositories.UserRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Service
public class UserServiceImp implements UserService {

    // To inject the repository dependency.
    @Autowired
    private UserRepository userRepository;



    // -----------------------------
    // Methods for user entity
    // -----------------------------

    // ENDPOINTS FOR THE ADMIN ROLE -----------------------------

    // To list all of users (records) in the 'users' table 
    @Override
    @Transactional(readOnly = true)
    public PageResponseDto<UserResponseProjection> findAllPerGroup(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<UserResponseProjection> pageResult = userRepository.getAllUsers(pageable);
        return PageResponseDto.fromPage(pageResult);
    }

    // To get a specific user based on its id
    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseProjection> findTop10ByNameContainingIgnoreCase(String name){
        return userRepository.findTop10ByNameContainingIgnoreCase(name);
    }

    // To save a new user in the db
    // This method is a 'join point'
    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    // To update a specific user based on its id
    @Override
    @Transactional
    public Optional<UserResponseDto> update(Long id, User user) {
        // Find a specific user
        Optional<User> optionalUser = userRepository.findById(id);

        // If the user is present then...
        if (optionalUser.isPresent()) {
            // update that record and return an optional value
            User userDb = optionalUser.get();

            userDb.setName(user.getName());
            userDb.setLastname(user.getLastname());

            User newUserDb = userRepository.save(userDb);

            return Optional.of(UserResponseDto.fromUser(newUserDb));
        }

        return Optional.empty();
    }

    // To delete a specific user based on its id
    @Override
    @Transactional
    public Optional<UserResponseDto> deleteById(Long id) {
        // Search a specific user
        Optional<User> optionalUser = userRepository.findById(id);

        // If the user is present then delete that user
        if (optionalUser.isPresent() ) {
            userRepository.deleteById(id);
            return Optional.of(UserResponseDto.fromUser(optionalUser.get()));
        }
        return Optional.empty();
    }

    // ENDPOINTS FOR THE OWNER -----------------------------

    // To get a specific user based on its id
    @Override
    @Transactional(readOnly = true)
    public Optional<UserResponseProjection> findOwnerUserById(Long id) {
        return userRepository.findOwnerUserById(id);
    }
    
}
