/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller.request;

public class RequestVideoParameter extends RequestMultimediaParameter {
    private String videoCodec;
    private String frames;
    private int extractThumbnail;

    /**
     *
     * @param videoCodec
     */
    public void setVideoCodec(final String videoCodec) {
        this.videoCodec = videoCodec;
    }

    /**
     *
     * @return
     */
    public String getFrames() {
        return frames;
    }

    /**
     *
     * @param frames
     */
    public void setFrames(final String frames) {
        this.frames = frames;
    }

    /**
     *
     * @return
     */
    public int getExtractThumbnail() {
        return extractThumbnail;
    }

    /**
     *
     * @param extractThumbnail
     */
    public void setExtractThumbnail(final int extractThumbnail) {
        this.extractThumbnail = extractThumbnail;
    }

    /**
     *
     * @throws Exception
     */
    @Override
    public void validate() throws Exception {
        super.validate();
        if (this.getVideoCodec() == null || "".equals(this.getVideoCodec())) {
            throw new Exception("failed Channels Rate empty");
        }
    }

    /**
     *
     * @return
     */
    public String getVideoCodec() {
        return videoCodec;
    }
}
