/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller.request;

import org.springframework.web.multipart.MultipartFile;

public class RequestAudioParameter  {

    private MultipartFile file;
    private String name;
    private String format;
    private String codec;
    private String bitRate;
    private String channel;
    private String sampleRate;
    private String start;
    private String duration;
    private String volume;
    private  boolean isMetadata = false;
    private boolean isCut = false;
    private String inputFile;
    private String outputFile;
    private String md5;

    /**
     *
     * @return
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     *
     * @param file
     */
    public void setFile(final MultipartFile file) {
        this.file = file;
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
    public String getFormat() {
        return format;
    }

    /**
     *
     * @param format
     */
    public void setFormat(final String format) {
        this.format = format;
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
    public boolean isMetadata() {
        return isMetadata;
    }

    /**
     *
     * @param metadata
     */
    public void setMetadata(final boolean metadata) {
        isMetadata = metadata;
    }

    /**
     *
     * @return
     */
    public boolean isCut() {
        return isCut;
    }

    /**
     *
     * @param cut
     */
    public void setCut(final boolean cut) {
        isCut = cut;
    }

    /**
     *
     * @param outputFile
     */
    public void setOutputFile(final String outputFile) {
        this.outputFile = outputFile;
    }

    /**
     *
     * @return
     */
    public String getMd5() {
        return md5;
    }

    /**
     *
     * @param md5
     */
    public void setMd5(final String md5) {
        this.md5 = md5;
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

    /**
     *
     * @param volume
     */
    public void setVolume(final String volume) {
        this.volume = volume;
    }

    /**
     *
     * @param inputFile
     */
    public void setInputFile(final String inputFile) {
        this.inputFile = inputFile;
    }
}
