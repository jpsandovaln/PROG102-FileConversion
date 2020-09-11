package org.fundacionjala.converter.controller.request;

public class FfmpegParameter extends RequestParameter {

    private String sampleRate;

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
    private String audioCodec;
    private String start;
    private int duration;

    /**
     *
     * @return
     */
    @Override
    public boolean validate() {
        return false;
    }
}
