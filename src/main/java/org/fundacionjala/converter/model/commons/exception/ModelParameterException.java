package org.fundacionjala.converter.model.commons.exception;

public class ModelParameterException extends Exception {
    private static final String MESSAGE = "Invalid Parameter.";

    public ModelParameterException() {
        super(MESSAGE);
    }
    public ModelParameterException(final String currentMessage) {
        super(currentMessage);
    }
}
