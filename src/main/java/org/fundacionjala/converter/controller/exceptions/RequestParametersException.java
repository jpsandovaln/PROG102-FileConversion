/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
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
