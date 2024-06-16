package com.gridnine.testing.exception;

public class FlightTimeIsNotCorrectException extends RuntimeException {

    public FlightTimeIsNotCorrectException(String message) {
        super(message);
    }
}
