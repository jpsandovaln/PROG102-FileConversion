package org.fundacionjala.converter.controller.exceptions;

public class NonExistentException extends Exception {
    public NonExistentException(final String operation) {
        super("The" + operation +  "operation is invalid because query does not return any result");
    }
}
