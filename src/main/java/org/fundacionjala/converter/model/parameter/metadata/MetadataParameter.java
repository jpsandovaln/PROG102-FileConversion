package org.fundacionjala.converter.model.parameter.metadata;

import org.fundacionjala.converter.model.commons.validation.IValidationStrategy;
import org.fundacionjala.converter.model.commons.validation.DetailMetadataValidation;
import org.fundacionjala.converter.model.commons.validation.FormatMetadataValidation;
import org.fundacionjala.converter.model.commons.validation.NotNullOrEmpty;
import org.fundacionjala.converter.model.commons.validation.ValidationContext;
import org.fundacionjala.converter.model.parameter.ModelParameter;
import org.fundacionjala.converter.model.commons.exception.InvalidDataException;

import java.util.List;
import java.util.ArrayList;

public class MetadataParameter extends ModelParameter {

    private String format;
    private String detail;

    public MetadataParameter(final String inputFile, final String format,
            final String detail, final String outputFile, final String md5) {
        super(inputFile, outputFile, md5);
        this.detail = detail;
        this.format = format;
    }

    /**
     * @return format
     */
    public String getFormat() {
        return format;
    }

    /**
     * @return detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * @param format
     */
    @Override
    public void setFormat(final String format) {
        this.format = format;
    }

    /**
     * @param detail
     */
    public void setDetail(final String detail) {
        this.detail = detail;
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
