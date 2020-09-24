package org.fundacionjala.converter.model.parameter.extractText;

import org.fundacionjala.converter.model.command.extractText.DocType;

import java.util.ArrayList;
import java.util.List;

public class ExtractTextParameterValidator {
    private List<String> languagesAllowed;
    private static final String LANGUAGE_SPA = "spa";

    public ExtractTextParameterValidator() {
        languagesAllowed = new ArrayList<>();
        languagesAllowed.add(LANGUAGE_SPA);
    }

    /**
     * Evaluates if the language is valid
     * @param language to evaluate
     * @return boolean
     */
    public boolean isValidLanguage(final String language) {
        return (!language.isBlank() && languagesAllowed.contains(language));
    }

    /**
     * Evaluates if the type is valid
     * @param type to evaluate
     * @return boolean
     */
    public boolean isValidType(final DocType type) {
        return (type != null);
    }
}
