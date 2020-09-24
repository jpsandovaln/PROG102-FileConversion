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
