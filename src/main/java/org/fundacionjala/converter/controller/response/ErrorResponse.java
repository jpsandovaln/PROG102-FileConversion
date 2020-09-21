package org.fundacionjala.converter.controller.response;

public class ErrorResponse<T> extends Response {
  private String error;

  public ErrorResponse(final T status, final String error) {
    super(status);
    this.error = error;
  }

  /***
   * getError()
ç   * @return String with a messages error
   */
  public String getError() {
    return error;
  }

  /**
   * setError
   * @param error String
   */
  public void setError(final String error) {
    this.error = error;
  }

}
