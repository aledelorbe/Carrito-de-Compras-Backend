package com.alejandro.carritodecompras.controllers;

import java.time.LocalDateTime;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alejandro.carritodecompras.entities.ErrorMessage;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<ErrorMessage> clientDuplicate(Exception e) {

        String errorMessage;

        // To know if this exception is fired by an update or create action.
        if ( e.getMessage().contains("insert")) {
            errorMessage = "Error! El usuario que se desea registrar ya se encuentra en la base de datos.";
        } else {
            errorMessage = "Error! Este nombre de usuario al cual se desea actualizar ya lo posee otro usuario.";
        }

        ErrorMessage error = new ErrorMessage();
        error.setDateTime(LocalDateTime.now());
        error.setError(errorMessage);
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.CONFLICT.value());

        return ResponseEntity.internalServerError().body(error);
    }
}