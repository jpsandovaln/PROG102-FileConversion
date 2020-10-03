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

    private static final String VIDEO_CODEC_SUPPORTED = "h264";
    private String videoCodec;
    private String frames;
    private String controlLoop;
    private boolean extractThumbnail;

    /**
     * Sets video codec
     * @param videoCodec
     */
    public void setVideoCodec(final String videoCodec) {
        this.videoCodec = videoCodec;
    }

    /**
     * @return videoCodec
     */
    public String getVideoCodec() {
        return videoCodec;
    }

    /**
     * @return frames
     */
    public String getFrames() {
        return frames;
    }

    /**
     * Sets the frames per second
     * @param frames the frames to set
     */
    public void setFrames(final String frames) {
        this.frames = frames;
    }

    /**
     * @return extractThumbnail
     */
    public boolean isExtractThumbnail() {
        return extractThumbnail;
    }

    /**
     * Sets extractThumbnail value
     * @param extractThumbnail the extractThumbnail to set
     */
    public void setExtractThumbnail(final boolean extractThumbnail) {
        this.extractThumbnail = extractThumbnail;
    }

    /**
     * @return controlLoop
     */
    public String getControlLoop() {
        return controlLoop;
    }

    /**
     * Sets controlLoop value
     * @param controlLoop the controlLoop to set
     */
    public void setControlLoop(final String controlLoop) {
        this.controlLoop = controlLoop;
    }

    /**
     * Validates video parameters
     * @throws Exception
     */
    @Override
    public void validate() throws Exception {
        super.validate();
        if (this.videoCodec == null || "".equals(this.videoCodec)) {
            throw new Exception("failed video Codec empty");
        }
        if (!this.videoCodec.equals(VIDEO_CODEC_SUPPORTED)) {
            throw new Exception("Invalid value of video Codec");
        }
    }
}
