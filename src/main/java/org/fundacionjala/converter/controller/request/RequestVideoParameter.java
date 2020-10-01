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
    private static final String FRAME_21 = "21";
    private static final String FRAME_24 = "24";
    private static final String FRAME_27 = "27";
    private static final String FRAME_30 = "30";
    private static final String ZERO = "0";
    private static final String ONE_MINUS = "-1";
    private String videoCodec;
    private String frames;
    private boolean extractThumbnail;
    private boolean extractMetadata;
    private String name;
    private String timeToSkip;
    private String secondsToOutput;
    private String controlLoop;
    private String duration = "40";

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
    public boolean getExtractThumbnail() {
        return extractThumbnail;
    }

    /**
     *
     * @param extractThumbnail
     */
    public void setExtractThumbnail(final boolean extractThumbnail) {
        this.extractThumbnail = extractThumbnail;
    }

    /**
     *
     * @return
     */
    public String getVideoCodec() {
        return videoCodec;
    }

    /**
     * @return the extractMetadata
     */
    public boolean isExtractMetadata() {
        return extractMetadata;
    }

    /**
     * @param extractMetadata the extractMetadata to set
     */
    public void setExtractMetadata(final boolean extractMetadata) {
        this.extractMetadata = extractMetadata;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return the timeToSkip
     */
    public String getTimeToSkip() {
        return timeToSkip;
    }

    /**
     * @param timeToSkip the timeToSkip to set
     */
    public void setTimeToSkip(final String timeToSkip) {
        this.timeToSkip = timeToSkip;
    }

    /**
     * @return the secondsToOutput
     */
    public String getSecondsToOutput() {
        return secondsToOutput;
    }

    /**
     * @param secondsToOutput the secondsToOutput to set
     */
    public void setSecondsToOutput(final String secondsToOutput) {
        this.secondsToOutput = secondsToOutput;
    }

    /**
     * @return the controlLoop
     */
    public String getControlLoop() {
        return controlLoop;
    }

    /**
     * @param controlLoop the controlLoop to set
     */
    public void setControlLoop(final String controlLoop) {
        this.controlLoop = controlLoop;
    }

    /**
     * @return the duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(final String duration) {
        this.duration = duration;
    }

    /**
     *
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
