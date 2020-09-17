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

public class RequestMetadataParameter extends RequestParameter {
    private String fileName;
    private String detail;
    public RequestMetadataParameter(final MultipartFile file, final String format, final String fileName, final String detail) {
        super(file, format);
        this.fileName = fileName;
        this.detail = detail;
    }

    /**
     *
     * @throws Exception
     */
    @Override
    public void validate() throws Exception {
        super.validate();
        if (this.getFileName() == null || "".equals(this.getFileName())) {
            throw new Exception("failed Channels Rate empty");
        }
        if (this.getDetail() == null || "".equals(this.getDetail())) {
            throw new Exception("failed Channels Rate empty");
        }
    }

    /**
     *
     * @return
     */
    public String getFileName() {
        return fileName;
    }

    /**
     *
     * @return
     */
    public String getDetail() {
        return detail;
    }


}
