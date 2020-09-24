package org.fundacionjala.converter.model.commons.validation;

import org.fundacionjala.converter.model.commons.exception.InvalidDataException;

import java.util.ArrayList;
import java.util.List;

public class FormatValidation implements IValidationStrategy {
    private String format;
    public static final String FORMAT_TXT = ".txt";
    public static final String FORMAT_DOCX = ".docx";
    public static final String FORMAT_PDF = ".pdf";
    private List<String> formatsAllowed;

    public FormatValidation(final String format) {
        this.format = format;
        formatsAllowed = new ArrayList<>();
        formatsAllowed.add(FORMAT_TXT);
        formatsAllowed.add(FORMAT_DOCX);
        formatsAllowed.add(FORMAT_PDF);
    }

    /**
     * @throws InvalidDataException
     */
    @Override
    public void validate() throws InvalidDataException {
        if (!formatsAllowed.contains(this.format)) {
            throw new InvalidDataException("Invalid format");
        }
    }
}
