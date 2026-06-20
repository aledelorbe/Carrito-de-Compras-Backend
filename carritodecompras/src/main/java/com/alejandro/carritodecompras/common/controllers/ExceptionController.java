package com.alejandro.carritodecompras.common.controllers;

import java.time.LocalDateTime;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alejandro.carritodecompras.common.models.ErrorMessage;
import com.alejandro.carritodecompras.exceptions.NoStockException;
import com.alejandro.carritodecompras.exceptions.ResourceNotFoundException;

// This class is used to handle when an exception is fired 
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

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler({ ResourceNotFoundException.class })
    public ResponseEntity<ErrorMessage> handleResourceNotFound(ResourceNotFoundException e) {
        ErrorMessage error = new ErrorMessage();
        error.setDateTime(LocalDateTime.now());
        error.setError("Recurso no encontrado");
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler({ NoStockException.class })
    public ResponseEntity<ErrorMessage> handleNoStock(NoStockException e) {
        ErrorMessage error = new ErrorMessage();
        error.setDateTime(LocalDateTime.now());
        error.setError("Sin stock disponible");
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}