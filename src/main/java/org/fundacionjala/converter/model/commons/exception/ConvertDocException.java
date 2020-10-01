package org.fundacionjala.converter.model.commons.exception;

public class ConvertDocException extends Exception {
    private static final String MESSAGE = "Error while converting.";

    public ConvertDocException() {
        super(MESSAGE);
    }

    public ConvertDocException(final Throwable ex) {
        super(MESSAGE, ex);
    }

    public ConvertDocException(final String currantMessage, final Throwable ex) {
        super(currantMessage, ex);
    }

    public ConvertDocException(final String currentMessage) {
        super(currentMessage);
    }
}
