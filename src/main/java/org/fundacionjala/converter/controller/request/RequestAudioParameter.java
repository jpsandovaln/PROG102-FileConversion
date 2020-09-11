package org.fundacionjala.converter.controller.request;

public class RequestAudioParameter extends FfmpegParameter {

    private String bitRate;
    private int channels;
    private String sampleRate;
    private int vol;

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
    public int getChannels() {
        return channels;
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
     * @return
     */
    public int getVol() {
        return vol;
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
