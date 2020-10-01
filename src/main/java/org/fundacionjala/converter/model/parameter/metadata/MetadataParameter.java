package org.fundacionjala.converter.model.parameter.metadata;

import org.fundacionjala.converter.model.commons.exception.InvalidDataException;
import org.fundacionjala.converter.model.commons.validation.*;
import org.fundacionjala.converter.model.parameter.ModelParameter;

import java.util.ArrayList;
import java.util.List;

public class MetadataParameter extends ModelParameter {

    private String format;
    private String detail;

    public MetadataParameter(final String inputFile, final String format, final String detail,
                             final String outputFile, final String md5) {
        super(inputFile, outputFile, md5);
        this.detail = detail;
        this.format = format;
    }
    /**
     *
     * @return format
     */
    public String getFormat() {
        return format;
    }

    /**
     *
     * @return detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * Validates the parameters
     * @throws InvalidDataException
     */
    public void validate() throws InvalidDataException {
        List<IValidationStrategy> validationStrategyList = new ArrayList<>();
        validationStrategyList.add(new DetailMetadataValidation(this.detail));
        validationStrategyList.add(new NotNullOrEmpty("detail", this.detail));
        validationStrategyList.add(new NotNullOrEmpty("format", this.format));
        validationStrategyList.add(new FormatMetadataValidation(this.format));
        ValidationContext context = new ValidationContext(validationStrategyList);
        context.validation();
    }
}
