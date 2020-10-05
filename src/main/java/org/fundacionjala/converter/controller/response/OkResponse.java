/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller.response;

public class OkResponse<T> extends Response {
  private String message;

  public OkResponse(final T status, final String message) {
    super(status);
    this.message = message;
  }

  /***
   * getMessage()
   * @return String
   */
  public String getMessage() {
    return message;
  }

  /**
   * setMessage
   * @param message
   */
  public void setMessage(final String message) {
    this.message = message;
  }

}
