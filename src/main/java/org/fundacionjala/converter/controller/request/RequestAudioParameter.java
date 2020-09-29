/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller.request;

public class RequestAudioParameter extends RequestParameter {

    private String name;
    private String codec;
    private String bitRate;
    private String channel;
    private String sampleRate;
    private String start;
    private String duration;
    private boolean isCut;
    private boolean extractThumbnail;
    private boolean extractMetadata;

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
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getCodec() {
        return codec;
    }

    /**
     *
     * @param codec
     */
    public void setCodec(final String codec) {
        this.codec = codec;
    }

    /**
     *
     * @return
     */
    public String getBitRate() {
        return bitRate;
    }

    /**
     *
     * @return
     */
    public String getChannel() {
        return channel;
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
    public String getDuration() {
        return duration;
    }

    /**
     *
     * @return
     */
    public boolean getExtractMetadata() {
        return extractMetadata;
    }

    /**
     *
     * @param extractMetadata
     */
    public void setExtractMetadata(final boolean extractMetadata) {
        this.extractMetadata = extractMetadata;
    }

    /**
     *
     * @return
     */
    public boolean isCut() {
        return this.isCut;
    }

    /**
     *
     * @param cut
     */
    public void setCut(final boolean cut) {
        this.isCut = cut;
    }

    /**
     *
     * @param bitRate
     */
    public void setBitRate(final String bitRate) {
        this.bitRate = bitRate;
    }

    /**
     *
     * @param channel
     */
    public void setChannel(final String channel) {
        this.channel = channel;
    }

    /**
     *
     * @param duration
     */
    public void setDuration(final String duration) {
        this.duration = duration;
    }

}
