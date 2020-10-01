/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller.request;

public class RequestExtractTextParameter extends RequestParameter {
    private String language;

    /**
     * @return language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets language value
     * @param language the language to set
     */
    public void setLanguage(final String language) {
        this.language = language;
    }

    /**
     * Validates extract Text parameters
     * @throws Exception
     */
    @Override
    public void validate() throws Exception {
        super.validate();
        if (this.getLanguage() == null || "".equals(this.getLanguage())) {
            throw new Exception("failed Language empty");
        }
    }
}
