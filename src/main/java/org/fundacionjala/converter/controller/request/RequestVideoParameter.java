package org.fundacionjala.converter.controller.request;

public class RequestVideoParameter extends FfmpegParameter {
//eye

    /**
     *
     * @return
     */
    public String getVideoCodec() {
        return videoCodec;
    }

    private String videoCodec;

    /**
     *
     * @return
     */
    @Override
    public boolean validate() {
        return false;
    }
}
