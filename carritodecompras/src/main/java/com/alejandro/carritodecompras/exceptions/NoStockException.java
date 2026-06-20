package com.alejandro.carritodecompras.exceptions;

public class NoStockException extends RuntimeException {

    public NoStockException() {
        super("There is not enough stock available.");
    }

    public NoStockException(String message) {
        super(message);
    }
    
}