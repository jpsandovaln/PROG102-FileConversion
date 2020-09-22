package org.fundacionjala.converter.model.parameter.extractText;

public class ExtractTextParameterException extends Exception {
    private static final String MESSAGE = "Invalid parameter.";

    public ExtractTextParameterException(final String message) {
        super(message);
    }
}
