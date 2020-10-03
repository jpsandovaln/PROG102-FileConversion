/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.model.parameter.extractText;

import org.fundacionjala.converter.model.commons.validation.LanguageValidation;
import org.fundacionjala.converter.model.commons.validation.FormatValidation;
import org.fundacionjala.converter.model.commons.validation.NotNullOrEmpty;
import org.fundacionjala.converter.model.commons.validation.IValidationStrategy;
import org.fundacionjala.converter.model.commons.validation.InputFileValidation;
import org.fundacionjala.converter.model.commons.validation.ValidationContext;
import org.fundacionjala.converter.model.parameter.ModelParameter;
import org.fundacionjala.converter.model.commons.exception.InvalidDataException;
import java.util.ArrayList;
import java.util.List;

public class ExtractTextParameter extends ModelParameter {
    private String language;
    private String fileName;
    private String format;
    public static final String LANG_COMMAND = "-l";

    public ExtractTextParameter() {
    }
    /**
     * Returns name of generated file
     * @return fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets fileName
     * @param fileName the name of file
     */
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(final String language) {
        this.language = language;
    }

    /**
     * @return the type
     */
    public String getFormat() {
        return format;
    }

    /**
     * @param format to set
     */
    public void setFormat(final String format) {
        this.format = format;
    }

    /**
     * Validates the parameters
     * @throws InvalidDataException
     */
    public void validate() throws InvalidDataException {
        List<IValidationStrategy> validationStrategyList = new ArrayList<>();
        validationStrategyList.add(new LanguageValidation(this.language));
        validationStrategyList.add(new NotNullOrEmpty("language", this.language));
        validationStrategyList.add(new NotNullOrEmpty("format", this.format));
        validationStrategyList.add(new NotNullOrEmpty("inputFile", this.getInputFile()));
        validationStrategyList.add(new FormatValidation(this.format));
        validationStrategyList.add(new InputFileValidation(this.getInputFile()));
        ValidationContext context = new ValidationContext(validationStrategyList);
        context.validation();
    }

    /**
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ExtractTextParameter [language=" + language + ", format=" + format + "]";
    }
}
