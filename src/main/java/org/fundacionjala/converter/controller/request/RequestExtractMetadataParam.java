/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller.request;

/**
 * @author Angela Martinez
 * @version 0.1
 */
public class RequestExtractMetadataParam extends RequestParameter {
    private String detail;

    /**
     * Sets amount of detail of metadata
     * @param detail
     */
    public void setDetail(final String detail) {
        this.detail = detail;
    }

    /**
     * @return detail
     */
    public String getDetail() {
        return detail;
    }
}
