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
     *
     * @return
     */
    @Override
    public boolean validate() {
        return false;
    }
}
