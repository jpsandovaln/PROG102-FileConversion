package org.fundacionjala.converter.database.exception;

public class NullAttributeException extends Exception {

    public NullAttributeException(final String currentMessage) {
        super("The attribute: \"" + currentMessage + "\" is null");
    }
}
