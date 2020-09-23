package org.fundacionjala.converter.model.commons.validation;
import org.fundacionjala.converter.model.commons.exception.InvalidDataException;

import java.util.ArrayList;
import java.util.List;

public class LanguageValidation implements IValidationStrategy {
    private String lang;
    private static final String LANGUAGE_SPA = "spa";
    private List<String> languagesAllowed;

    public LanguageValidation(final String lang) {
        this.lang = lang;
        languagesAllowed = new ArrayList<>();
        languagesAllowed.add(LANGUAGE_SPA);
    }

    /**
     *
     * @throws InvalidDataException
     */
    @Override
    public void validate() throws InvalidDataException {
        if (!languagesAllowed.contains(this.lang)) {
            throw new InvalidDataException("Invalid language");
        }
    }
}
