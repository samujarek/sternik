package com.sternik.samujarek.repositories;

public class BusAlreadyExistsException extends Exception {
    private static final long serialVersionUID = -4576295597218170159L;

    public BusAlreadyExistsException() {     
    }

    public BusAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BusAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusAlreadyExistsException(String message) {
        super(message);
    }

    public BusAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}