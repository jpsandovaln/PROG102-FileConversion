package org.fundacionjala.converter.controller.response;

public abstract class Response<T> {
    private T status;

    public Response(final T status) {
        this.status = status;
    }

    public T getSTatus() {
      return status;
    }

    public void setStatus(final T status) {
        this.status = status;
    }
}
