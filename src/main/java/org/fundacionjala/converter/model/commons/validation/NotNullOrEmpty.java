/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.model.commons.validation;

import org.fundacionjala.converter.model.commons.exception.InvalidDataException;

public class NotNullOrEmpty implements IValidationStrategy {
    private String value;
    private String field;

    public NotNullOrEmpty(final String field, final String value) {
        this.value = value;
        this.field = field;
    }

    /**
     *
     * @throws InvalidDataException
     */
    @Override
    public void validate() throws InvalidDataException {
        if (this.value == null || this.value.isBlank()) {
            throw new InvalidDataException("Invalid data on field = " + this.field);
        }
    }
}
