package org.fundacionjala.converter.params;

/**
 * @author Mirko Romay
 * @version 0.1
 */
public class AudioParameter extends Parameter {

    /**
     * @return format
     */
    public String getFormat() {
        return format;
    }

    /**
     * @return codec
     */

    public String getCodec() {
        return codec;
    }

    /**
     * @return bitRate
     */
    public String getBitRate() {
        return bitRate;
    }

    /**
     *
     * @return channels
     */
    public String getChannels() {
        return channels;
    }

    /**
     * @return sampleRate
     */
    public String getSampleRate() {
        return sampleRate;
    }

    /**
     * @return start
     */
    public String getStart() {
        return start;
    }

    /**
     * @return duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * @return vol
     */
    public String getVol() {
        return vol;
    }

    /**
     * @return md5
     */
    public String getMd5() {
        return md5;
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
     * @param codec
     */
    public void setCodec(final String codec) {
        this.codec = codec;
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
     * @param channels
     */
    public void setChannels(final String channels) {
        this.channels = channels;
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
     * @param start
     */
    public void setStart(final String start) {
        this.start = start;
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
     * @param vol
     */
    public void setVol(final String vol) {
        this.vol = vol;
    }

    /**
     *
     * @param md5
     */
    public void setMd5(final String md5) {
        this.md5 = md5;
    }

    private String format;
    private String codec;
    private String bitRate;
    private String channels;
    private String sampleRate;
    private String start;
    private String duration;
    private String vol;
    private String md5;
}
