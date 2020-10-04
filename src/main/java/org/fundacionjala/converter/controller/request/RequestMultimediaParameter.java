/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller.request;

public class RequestMultimediaParameter extends RequestParameter {

    private String name;
    private String codec;
    private String start;
    private String duration;
    private boolean extractMetadata;
    private String secondsToOutput;

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name value
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return codec
     */
    public String getCodec() {
        return codec;
    }

    /**
     * Sets codec value
     * @param codec the codec to set
     */
    public void setCodec(final String codec) {
        this.codec = codec;
    }

    /**
     * @return start
     */
    public String getStart() {
        return start;
    }

    /**
     * Sets start value
     * @param start the start to set
     */
    public void setStart(final String start) {
        this.start = start;
    }

    /**
     * @return duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Sets duration value
     * @param duration the duration to set
     */
    public void setDuration(final String duration) {
        this.duration = duration;
    }

    /**
     * @return extractMetadata
     */
    public boolean isExtractMetadata() {
        return extractMetadata;
    }

    /**
     * Sets extractMetadata value
     * @param extractMetadata the extractMetadata to set
     */
    public void setExtractMetadata(final boolean extractMetadata) {
        this.extractMetadata = extractMetadata;
    }
    /**
     * @return secondsToOutput
     */
    public String getSecondsToOutput() {
        return secondsToOutput;
    }

    /**
     * Sets secondsToOutput value
     * @param secondsToOutput the secondsToOutput to set
     */
    public void setSecondsToOutput(final String secondsToOutput) {
        this.secondsToOutput = secondsToOutput;
    }

    /**
     * Validates multimedia parameters
     * @throws Exception
     */
    @Override
    public void validate() throws Exception {
        super.validate();
        if (this.getCodec() == null || "".equals(this.getCodec())) {
            throw new Exception("Failed, empty audioCodec");
        }
    }
}
