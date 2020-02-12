package com.gpsolutions.edu.beershop.exception;

public class NoSuchBeerException extends Exception {

    public NoSuchBeerException() {
    }

    public NoSuchBeerException(final String message) {
        super(message);
    }
}
