package com.gpsolutions.edu.beershop.exception;

public class OrderNotFoundException extends Exception {

    public OrderNotFoundException() {
    }

    public OrderNotFoundException(final String message) {
        super(message);
    }
}
