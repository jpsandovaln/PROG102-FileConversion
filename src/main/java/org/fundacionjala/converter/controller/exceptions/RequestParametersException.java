package org.fundacionjala.converter.controller.exceptions;

public class RequestParametersException extends Exception {
    private static final String MESSAGE = "Error one field empty.";

    public RequestParametersException(final String currentMessage, final Throwable ex) {
        super(currentMessage, ex);
    }

    public RequestParametersException(final Throwable ex) {
        super(MESSAGE, ex);
    }

    public RequestParametersException(final String currentMessage) {
        super(currentMessage);
    }

    public RequestParametersException() {
        super(MESSAGE);
    }
}
