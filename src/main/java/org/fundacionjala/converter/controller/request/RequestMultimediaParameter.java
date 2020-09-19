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

    /**
     *
     * @throws Exception
     */
    @Override
    public void validate() throws Exception {
        super.validate();
        if (this.getSampleRate() == null || "".equals(this.getSampleRate())) {
            throw new Exception("failed Sample Rate empty");
        }

        if (this.getAudioCodec() == null || "".equals(this.getAudioCodec())) {
            throw new Exception("failed audiocodec empty");
        }

    }

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

}
