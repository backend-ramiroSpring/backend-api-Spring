package com.Ramiro.backendspringboot.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}

/* Si es que no se encuentra un recurso, cuando realizamos busquedas */