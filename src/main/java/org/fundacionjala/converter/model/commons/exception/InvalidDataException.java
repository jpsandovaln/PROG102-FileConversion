package org.fundacionjala.converter.model.commons.exception;

public class InvalidDataException extends Exception {
    private static final String MESSAGE = "Invalid Data.";

    public InvalidDataException() {
        super(MESSAGE);
    }

    public InvalidDataException(final Throwable ex) {
        super(MESSAGE, ex);
    }

    public InvalidDataException(final String currentMessage, final Throwable ex) {
        super(currentMessage, ex);
    }

    public InvalidDataException(final String currentMessage) {
        super(currentMessage);
    }
}
