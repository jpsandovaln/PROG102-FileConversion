package org.fundacionjala.converter.model.commons.validation;


import org.fundacionjala.converter.model.commons.exception.InvalidDataException;
import org.fundacionjala.converter.model.parameter.ModelParameter;

public class ModelParameterValidation implements IValidationStrategy {
    private ModelParameter parameter;
    public ModelParameterValidation(final ModelParameter parameter) {
        this.parameter = parameter;
    }

    /**
     * Validate model parameter null
     * @throws InvalidDataException
     */
    public void validate() throws InvalidDataException {
        if (parameter == null) {
            throw new InvalidDataException("the model is null");
        }
    }
}
