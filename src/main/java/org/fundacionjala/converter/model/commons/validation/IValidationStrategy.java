package org.fundacionjala.converter.model.commons.validation;

import org.fundacionjala.converter.model.commons.exception.InvalidDataException;

public interface IValidationStrategy {
    void validate() throws InvalidDataException;
}
