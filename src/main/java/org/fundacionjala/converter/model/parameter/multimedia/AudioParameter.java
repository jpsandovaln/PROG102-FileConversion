package org.fundacionjala.converter.model.parameter.multimedia;

import java.io.IOException;

public class AudioParameter extends MultimediaParameter {

    private String format;
    private String bitRate;
    private String channel;
    private String sampleRate;
    private String volume;
    private boolean isCut = false;
    private AudioParameterValidation audioValidation = new AudioParameterValidation();

    /**
     * @return audio format
     */
    public String getFormat() {
        return format;
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
     * @return audio volume
     */
    public String getVolume() {
        return volume;
    }

    /**
     * @return if is cut
     */
    public boolean getIsCut() {
        return isCut;
    }

    /**
     * Sets inputFile value
     * @param inputFile the inputFile to set
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
     * Sets format value
     * @param format the  to set
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
     * Sets volume value
     * @param volume the  to set
     */
    public void setVolume(final String volume) {
        this.volume = volume;
    }

    /**
     * Sets cut value
     * @param cut the  to set
     */
    public void setCut(final boolean cut) {
        isCut = cut;
    }
}
