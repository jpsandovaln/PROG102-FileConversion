/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller.request;

public class RequestMetadataParameter extends RequestParameter {

    private String fileName;
    private String detail;

    /**
     * @return fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets fileName value
     * @param fileName the fileName to set
     */
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * Sets detail value
     * @param detail the detail to set
     */
    public void setDetail(final String detail) {
        this.detail = detail;
    }

    /**
     * Validates metadata parameters
     * @throws Exception
     */
    @Override
    public void validate() throws Exception {
        super.validate();
        if (this.getFileName() == null || "".equals(this.getFileName())) {
            throw new Exception("File name error");
        }
    }
}
