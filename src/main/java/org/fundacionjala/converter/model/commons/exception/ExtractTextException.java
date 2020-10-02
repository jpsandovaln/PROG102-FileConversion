package org.fundacionjala.converter.model.commons.exception;

public class ExtractTextException extends Exception {
    private static final String MESSAGE = "Error while extracting text.";

    public ExtractTextException() {
        super(MESSAGE);
    }
    public ExtractTextException(final String currentMessage) {
        super(currentMessage);
    }
}
