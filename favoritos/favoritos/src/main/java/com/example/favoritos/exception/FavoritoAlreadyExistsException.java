package com.example.favoritos.exception;

public class FavoritoAlreadyExistsException extends RuntimeException {
    public FavoritoAlreadyExistsException(String message) {
        super(message);
    }
}