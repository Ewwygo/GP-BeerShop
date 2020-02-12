package com.gpsolutions.edu.beershop.exception;

public class SuchBeerAlreadyExistException extends Exception {

    public SuchBeerAlreadyExistException() {
    }

    public SuchBeerAlreadyExistException(final String message) {
        super(message);
    }
}
