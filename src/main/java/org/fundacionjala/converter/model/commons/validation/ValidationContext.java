package org.fundacionjala.converter.model.commons.validation;
import org.fundacionjala.converter.model.commons.exception.InvalidDataException;
import java.util.List;

public class ValidationContext {
    private List<IValidationStrategy> validationStrategies;

    public ValidationContext(final List<IValidationStrategy> validationStrategies) {
        this.validationStrategies = validationStrategies;
    }

    /**
     *
     * @throws InvalidDataException
     */
    public void validation() throws InvalidDataException {
        for (IValidationStrategy strategy : this.validationStrategies) {
            strategy.validate();
        }
    }
}
