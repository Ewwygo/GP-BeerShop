package com.gpsolutions.edu.beershop.exception;

public class SuchClientAlreadyExistException extends Exception {

    public SuchClientAlreadyExistException(){super();}

    public SuchClientAlreadyExistException(final String message) {
        super(message);
    }
}
