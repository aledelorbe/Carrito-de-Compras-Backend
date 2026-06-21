package com.alejandro.carritodecompras.user.controllers;


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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.carritodecompras.user.models.dtos.UserResponseDto;
import com.alejandro.carritodecompras.user.models.entities.User;
import com.alejandro.carritodecompras.user.models.projections.UserResponseProjection;
import com.alejandro.carritodecompras.user.services.UserService;
import com.alejandro.carritodecompras.utils.UtilValidation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;


@RestController // To create an api rest.
@RequestMapping("/api/users") // To create a base path.
public class UserController {

    // To Inject the service dependency
    @Autowired
    private UserService userService;

    @Autowired
    private UtilValidation utilValidation;

    // -----------------------------
    // Methods for user entity
    // -----------------------------

    // ENDPOINTS FOR THE ADMIN ROLE -----------------------------

    // To create an endpoint that allows invoking the findAllPerGroup method.
    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts(
        @RequestParam(defaultValue = "0") 
        @Min(value = 0, message = "The page number must be greater than or equal to 0")
        int page,
        @RequestParam(defaultValue = "5") 
        @Min(value = 5, message = "The page size must be greater than or equal to 5")
        int size) 
    {
        return ResponseEntity.ok(userService.findAllPerGroup(page, size));
    }

    // To create an endpoint that allows invoking the findById method.
    @GetMapping("/{id}")
    public ResponseEntity<?> user(@PathVariable Long id) {
        // Search a specific user and if it's present then return it.
        Optional<User> optionalUser = userService.findById(id);

        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.orElseThrow());
        }
        // Else returns code response 404
        return ResponseEntity.notFound().build();
    }

    // To create an endpoint that allows invoking the findById method.
    @GetMapping("/name/{name}")
    public ResponseEntity<?> user(@PathVariable String name) {
        return ResponseEntity.ok(userService.findTop10ByNameContainingIgnoreCase(name));
    }

    // To create an endpoint that allows invoking the save method.
    // The annotation called 'RequestBody' allows receiving data of a user
    @PostMapping()
    public ResponseEntity<?> saveUser(@Valid @RequestBody User user, BindingResult result) {
        // To handle the obligations of object attributes
        if (result.hasFieldErrors()) {
            return utilValidation.validation(result);
        }

        // When a new user is created to respond return the same user
        User newUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    // To create an endpoint that allows updating all the values
    // of a specific user based its id.
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody User user, BindingResult result, @PathVariable Long id) {
        // To handle of obligations of object attributes
        if (result.hasFieldErrors()) {
            return utilValidation.validation(result);
        }

        // Find specific user and if it's present then return specific user
        Optional<UserResponseDto> optionalUser = userService.update(id, user);

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
        Optional<UserResponseDto> optionalUser = userService.deleteById(id);
        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.orElseThrow());
        }
        // Else return code response 404
        return ResponseEntity.notFound().build();
    }

    // ENDPOINTS FOR THE OWNER -----------------------------

    // To create an endpoint that allows invoking the findById method.
    @GetMapping("/owner/{id}")
    public ResponseEntity<?> getOwnerUser(@PathVariable Long id) {
        // Search a specific product and if it's present then return it.
        Optional<UserResponseProjection> optionalProduct = userService.findOwnerUserById(id);

        if (optionalProduct.isPresent()) {
            return ResponseEntity.ok(optionalProduct.orElseThrow());
        }
        // Else returns code response 404
        return ResponseEntity.notFound().build();
    }

}
