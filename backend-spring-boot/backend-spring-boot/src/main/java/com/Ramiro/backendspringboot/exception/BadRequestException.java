package com.Ramiro.backendspringboot.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException() {
    }

    public BadRequestException(String message) {
        super(message);
    }
}

/* Excepcion cuando se envia un dato, y esa estructura de la cuenta no cumpla con la validacion
*  */