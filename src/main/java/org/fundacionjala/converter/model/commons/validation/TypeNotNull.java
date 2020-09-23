package org.fundacionjala.converter.model.commons.validation;

import org.fundacionjala.converter.model.command.extractText.DocType;
import org.fundacionjala.converter.model.commons.exception.InvalidDataException;

public class TypeNotNull implements IValidationStrategy {
    private DocType value;
    private String field;

    public TypeNotNull(final String field, final DocType value) {
        this.value = value;
        this.field = field;
    }

    /**
     *
     * @throws InvalidDataException
     */
    @Override
    public void validate() throws InvalidDataException {
        if (this.value == null) {
            throw new InvalidDataException("Invalid data on field = " + this.field);
        }
    }
}
