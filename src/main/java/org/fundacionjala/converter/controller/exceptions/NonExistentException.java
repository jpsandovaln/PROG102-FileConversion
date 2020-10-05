/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller.exceptions;

public class NonExistentException extends Exception {
    public NonExistentException(final String operation) {
        super("The" + operation +  "operation is invalid because query does not return any result");
    }
}
