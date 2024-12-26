package com.alejandro.carritodecompras.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.carritodecompras.entities.User;
import com.alejandro.carritodecompras.services.UserService;
import com.alejandro.carritodecompras.utils.UtilDetail;
import com.alejandro.carritodecompras.utils.UtilValidation;

import jakarta.validation.Valid;

@RestController // To create a api rest.
@RequestMapping("/api/users") // To create a base path.
public class UserController {

    // To Inject the service dependency
    @Autowired
    private UserService service;

    @Autowired
    private UtilValidation utilValidation;

    // -----------------------------
    // Methods for user entity
    // -----------------------------

    // To create an endpoint that allows invocating the method findAll.
    @GetMapping()
    public List<User> users() {
        return service.findAll();
    }

    // To create an endpoint that allows invocating the method fingById.
    @GetMapping("/{id}")
    public ResponseEntity<?> user(@PathVariable Long id) {
        // Search a specific user and if it's present then return it.
        Optional<User> optionalUser = service.findById(id);
        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.orElseThrow());
        }
        // Else returns code response 404
        return ResponseEntity.notFound().build();
    }

    // To create an endpoint that allows invocating the method save.
    // The annotation called 'RequestBody' allows receiving data of a user
    @PostMapping()
    public ResponseEntity<?> saveUser(@Valid @RequestBody User user, BindingResult result) {
        // To handle the obligations of object attributes
        if (result.hasFieldErrors()) {
            return utilValidation.validation(result);
        }

        // When a new user is created to respond return the same user
        User newUser = service.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    // To create an endpoint that allows update all of atributte values a specific
    // user based its id.
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody User user, BindingResult result,
            @PathVariable Long id) {
        // To handle of obligations of object attributes
        if (result.hasFieldErrors()) {
            return utilValidation.validation(result);
        }

        // Find specific user and if it's present then return specific user
        Optional<User> optionalUser = service.update(id, user);

        if (optionalUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalUser.orElseThrow());
        }
        // Else return code response 404
        return ResponseEntity.notFound().build();
    }

    // To create an endpoint that allows deleting a specific user based its id.
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        // Find specific user and if it's present then return specific user
        Optional<User> optionalUser = service.deleteById(id);
        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.orElseThrow());
        }
        // Else return code response 404
        return ResponseEntity.notFound().build();
    }

    // -----------------------------
    // Methods for purchase entity
    // -----------------------------

    // To create an endpoint that allows invocating the 'addPurchaseToUser' method.
    // The annotation called 'RequestBody' allows receiving data of a user
    @PostMapping("/{userId}/purchase")
    public ResponseEntity<?> addPurchaseToUser(@Valid @RequestBody List<UtilDetail> utilDetails, BindingResult result, @PathVariable Long userId) {
        // To handle the obligations of object attributes
        if (result.hasFieldErrors()) {
            return utilValidation.validation(result);
        }

        // Search for a specific user if it exists then save the pet
        Optional<User> optionalUser = service.findById(userId);

        if (optionalUser.isPresent()) {
            User newUser = service.addPurchaseToUser(optionalUser.get(), utilDetails);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        }
        // Else returns code response 404
        return ResponseEntity.notFound().build();
    }

    // To create an endpoint that allows invocating the 'getPurchasesOfUser' method.
    // The annotation called 'RequestBody' allows receiving data of a user
    @GetMapping("/{userId}/purchase")
    public ResponseEntity<?> getPurchasesOfUser(@PathVariable Long userId) {

        // Search for a specific user if it exists then save the pet
        Optional<User> optionalUser = service.findById(userId);

        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.orElseThrow());
        }
        // Else returns code response 404
        return ResponseEntity.notFound().build();
    }
}
