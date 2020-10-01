package org.fundacionjala.converter.model.commons.validation;

import org.fundacionjala.converter.model.commons.exception.InvalidDataException;

import java.util.ArrayList;
import java.util.List;

public class DetailMetadataValidation implements IValidationStrategy {

    private String detail;
    private static final String VERBOSO = "v";
    private static final String DEFAULT = "d";
    private static final String COMMONS = "common";
    private List<String> detailsAllowed;

    public DetailMetadataValidation(final String detail) {
        this.detail = detail;
        detailsAllowed = new ArrayList<>();
        detailsAllowed.add(VERBOSO);
        detailsAllowed.add(DEFAULT);
        detailsAllowed.add(COMMONS);
    }

    /**
     *
     * @throws InvalidDataException
     */
    @Override
    public void validate() throws InvalidDataException {
        if (!detailsAllowed.contains(this.detail)) {
            throw new InvalidDataException("Invalid detail");
        }
    }
}
