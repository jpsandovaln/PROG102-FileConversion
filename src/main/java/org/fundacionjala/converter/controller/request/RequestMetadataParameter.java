package org.fundacionjala.converter.controller.request;

public class RequestMetadataParameter extends RequestParameter {
    private String fileName;
    private String detail;

    /**
     *
     * @return
     */
    @Override
    public boolean validate() {
        return false;
    }
}
