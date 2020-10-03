package org.fundacionjala.converter.executor.exception;

public class CommandListEmptyException extends Exception {

    public CommandListEmptyException(final String currentMessage) {
        super("The attribute: \"" + currentMessage + "\" is null");
    }
}
