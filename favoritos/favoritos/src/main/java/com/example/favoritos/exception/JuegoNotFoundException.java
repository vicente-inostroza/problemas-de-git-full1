package com.example.favoritos.exception;

public class JuegoNotFoundException extends RuntimeException {
    public JuegoNotFoundException(String message) {
        super(message);
    }
}