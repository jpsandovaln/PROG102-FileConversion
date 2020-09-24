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
