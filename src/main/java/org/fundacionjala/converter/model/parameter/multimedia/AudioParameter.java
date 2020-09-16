package org.fundacionjala.converter.model.parameter.multimedia;

import java.io.IOException;

public class AudioParameter extends MultimediaParameter {

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
    private AudioParameterValidation audioValidation = new AudioParameterValidation();

    /**
     *
     * @return new name file
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return audio format
     */
    public String getFormat() {
        return format;
    }

    /**
     *
     * @return audio codec
     */
    public String getCodec() {
        return codec;
    }

    /**
     *
     * @return audio bit rate
     */
    public String getBitRate() {
        return bitRate;
    }

    /**
     *
     * @return audio channel
     */
    public String getChannel() {
        return channel;
    }

    /**
     *
     * @return audio sample rate
     */
    public String getSampleRate() {
        return sampleRate;
    }

    /**
     *
     * @return audio start
     */
    public String getStart() {
        return start;
    }

    /**
     *
     * @return audio duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     *
     * @return audio volume
     */
    public String getVolume() {
        return volume;
    }

    /**
     *
     * @return if is metadata
     */
    public boolean getIsMetadata() {
        return isMetadata;
    }

    /**
     *
     * @return if is cut
     */
    public boolean getIsCut() {
        return isCut;
    }


    /**
     *
     * @param inputFile
     * @throws IOException
     */
    public void setInputFile(final String inputFile) throws IOException {
        if (audioValidation.validateAudioFile(inputFile)) {
            super.setInputFile(inputFile);
        } else {
            // exception el archivo no es un audio.
            System.out.println("the file does not type audio" + inputFile);
        }
    }


    /**
     *
     * @param name
     */
    public void setName(final String name) {
         this.name = audioValidation.validateAudioName(name);
    }

    /**
     *
     * @param format
     */
    public void setFormat(final String format) {
        if (audioValidation.validateAudioFormat(format)) {
            this.format = format;
        } else {
            // exception the format does not belong to audio +audioFormat
            System.out.println("the format does not belong to audio" + format);
        }
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
     * @param channel
     */
    public void setChannel(final String channel) {
        this.channel = channel;
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
     * @param volume
     */
    public void setVolume(final String volume) {
        this.volume = volume;
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
     * @param cut
     */
    public void setCut(final boolean cut) {
        isCut = cut;
    }
}
