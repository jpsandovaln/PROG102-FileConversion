/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller.request;

public class RequestAudioParameter extends RequestMultimediaParameter {

    private String bitRate;
    private String channel;
    private String sampleRate;
    private boolean cut;

    /**
     * @return is Audio is cut
     */
    public boolean isCut() {
        return cut;
    }

    /**
     * Sets if audio is cut or not
     * @param cut
     */
    public void setCut(boolean cut) {
        this.cut = cut;
    }

    /**
     * @return audio bit rate
     */
    public String getBitRate() {
        return bitRate;
    }

    /**
     * @return audio channel
     */
    public String getChannel() {
        return channel;
    }

    /**
     * @return audio sample rate
     */
    public String getSampleRate() {
        return sampleRate;
    }


    /**
     * Sets bitRate value
     * @param bitRate the  to set
     */
    public void setBitRate(final String bitRate) {
        this.bitRate = bitRate;
    }

    /**
     * Sets channel value
     * @param channel the  to set
     */
    public void setChannel(final String channel) {
        this.channel = channel;
    }

    /**
     * Sets sampleRate value
     * @param sampleRate the  to set
     */
    public void setSampleRate(final String sampleRate) {
        this.sampleRate = sampleRate;
    }


    /**
     * Validates audio parameters
     * @throws Exception
     */
    @Override
    public void validate() throws Exception {
        super.validate();
        if (this.getSampleRate() == null || "".equals(this.getSampleRate())) {
            throw new Exception("failed Sample Rate empty");
        }
    }
}
