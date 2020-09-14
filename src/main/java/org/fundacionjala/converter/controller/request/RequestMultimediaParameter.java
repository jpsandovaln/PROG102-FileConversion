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

    private String sampleRate;
    private String audioCodec;
    private String start;
    private int duration;

    /**
     *
     * @return
     */
    public String getSampleRate() {
        return sampleRate;
    }

    /**
     *
     * @param sampleRate
     */
    public void setSampleRate(final String sampleRate) {
        this.sampleRate = sampleRate;
    }

    /**
     *
     * @return
     */
    public String getAudioCodec() {
        return audioCodec;
    }

    /**
     *
     * @param audioCodec
     */
    public void setAudioCodec(final String audioCodec) {
        this.audioCodec = audioCodec;
    }

    /**
     *
     * @return
     */
    public String getStart() {
        return start;
    }

    /**
     *
     * @param start
     */
    public void setStart(final String start) {
        this.start = start;
    }

    /**
     *
     * @return
     */
    public int getDuration() {
        return duration;
    }

    /**
     *
     * @param duration
     */
    public void setDuration(final int duration) {
        this.duration = duration;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean validate() {
        return false;
    }
}
