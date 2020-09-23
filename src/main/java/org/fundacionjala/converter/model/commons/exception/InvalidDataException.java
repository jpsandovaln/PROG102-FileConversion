package org.fundacionjala.converter.model.commons.exception;

public class InvalidDataException extends Exception {
    private static final String MESSAGE = "Invalid Data.";

    public InvalidDataException() {
        super(MESSAGE);
    }

    public InvalidDataException(final Throwable ex) {
        super(MESSAGE, ex);
    }

    public InvalidDataException(final String currantMessage, final Throwable ex) {
        super(currantMessage, ex);
    }

    public InvalidDataException(final String currentMessage) {
        super(currentMessage);
    }
}
