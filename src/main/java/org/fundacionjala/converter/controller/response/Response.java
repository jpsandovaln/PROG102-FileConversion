/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller.response;

public abstract class Response<T> {
    private T status;

    public Response(final T status) {
        this.status = status;
    }

    /***
     * getSTatus()
     *
     * @return T
     */
    public T getSTatus() {
      return status;
    }

    /***
     * setStatus()
     *
     * @param status T
     */
    public void setStatus(final T status) {
        this.status = status;
    }
}
