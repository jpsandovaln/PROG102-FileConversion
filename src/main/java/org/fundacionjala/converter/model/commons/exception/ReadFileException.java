package org.fundacionjala.converter.model.commons.exception;

public class ReadFileException extends Exception {
    private static final String MESSAGE = "Error while reading a file.";

    public ReadFileException() {
        super(MESSAGE);
    }

    public ReadFileException(final Throwable ex) {
        super(MESSAGE, ex);
    }

    public ReadFileException(final String currentMessage, final Throwable ex) {
        super(currentMessage, ex);
    }

    public ReadFileException(final String currentMessage) {
        super(currentMessage);
    }
}
