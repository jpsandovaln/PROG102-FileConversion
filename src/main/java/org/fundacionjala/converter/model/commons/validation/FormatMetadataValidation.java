package org.fundacionjala.converter.model.commons.validation;

import org.fundacionjala.converter.model.commons.exception.InvalidDataException;

import java.util.ArrayList;
import java.util.List;

public class FormatMetadataValidation implements IValidationStrategy {

    private String format;
    private static final String TXT = "t";
    private static final String JSON = "j";
    private static final String CSV = "T";
    private static final String HTML = "h";
    private static final String XMP = "XMP";
    private List<String> formatsAllowed;

    public FormatMetadataValidation(final String format) {
        this.format = format;
        formatsAllowed = new ArrayList<>();
        formatsAllowed.add(TXT);
        formatsAllowed.add(JSON);
        formatsAllowed.add(CSV);
        formatsAllowed.add(HTML);
        formatsAllowed.add(XMP);
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
