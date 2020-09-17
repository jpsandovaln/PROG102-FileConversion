/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller.request;

import org.springframework.web.multipart.MultipartFile;

public class RequestExtractTextParameter extends RequestParameter {
    private String language;
    public RequestExtractTextParameter(final MultipartFile file, final String format, final String language) {
        super(file, format);
        this.language = language;
    }

    /**
     *
     * @throws Exception
     */
    @Override
    public void validate() throws Exception {
        super.validate();
        if (this.getLanguage() == null || "".equals(this.getLanguage())) {
            throw new Exception("failed Language empty");
        }
    }

    /**
     *
     * @return
     */
    public String getLanguage() {
        return language;
    }

    /**
     *
     * @param language
     */
    public void setLanguage(final String language) {
        this.language = language;
    }

}
