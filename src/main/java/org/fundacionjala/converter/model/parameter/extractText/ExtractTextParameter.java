/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.model.parameter.extractText;

import org.fundacionjala.converter.model.command.extractText.DocType;
import org.fundacionjala.converter.model.parameter.ModelParameter;

public class ExtractTextParameter extends ModelParameter {
    private String language;
    private String fileName;
    private DocType type;
    private ExtractTextParameterValidator validator;
    public static final String LANG_COMMAND = "-l";

    public ExtractTextParameter() {
        validator = new ExtractTextParameterValidator();
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
    public void setLanguage(final String language) throws ExtractTextParameterException {
        if (validator.isValidLanguage(language)) {
            this.language = language;
        } else {
            throw new ExtractTextParameterException("Invalid Language");
        }
    }

    /**
     * @return the type
     */
    public DocType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(final DocType type) throws ExtractTextParameterException {
        if (validator.isValidType(type)) {
            this.type = type;
        } else {
            throw new ExtractTextParameterException("Invalid Type");
        }

    }

    /**
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ExtractTextParameter [language=" + language + ", type=" + type + "]";
    }
}
