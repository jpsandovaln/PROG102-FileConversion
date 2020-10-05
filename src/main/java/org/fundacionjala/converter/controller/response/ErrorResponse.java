/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller.response;

public class ErrorResponse<T> extends Response {
  private String error;

  public ErrorResponse(final T status, final String error) {
    super(status);
    this.error = error;
  }

  /***
   * Gets Error
ç   * @return String with a messages error
   */
  public String getError() {
    return error;
  }

  /**
   * Sets Error
   * @param error String
   */
  public void setError(final String error) {
    this.error = error;
  }

}
