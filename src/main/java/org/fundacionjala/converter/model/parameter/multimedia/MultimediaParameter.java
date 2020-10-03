package org.fundacionjala.converter.model.parameter.multimedia;

import org.fundacionjala.converter.model.parameter.ModelParameter;

public abstract class MultimediaParameter<T> extends ModelParameter {

    private String name;
    private String codec;
    private String start;
    private String duration;
    private String secondsToOutput;
    private boolean extractMetadata = false;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name value
     * @param name the name to set
     */
    public void setName(final String name) throws NullPointerException {
        this.name = name;
    }

    /**
     * @return the codec
     */
    public String getCodec() {
        return codec;
    }

    /**
     * Sets codec value
     * @param codec the codec to set
     */
    public void setCodec(final String codec) throws NullPointerException {
        this.codec = codec;
    }

    /**
     * @return the start
     */
    public String getStart() {
        return start;
    }

    /**
     * Sets start value
     * @param start the start to set
     */
    public void setStart(final String start) throws NullPointerException {
        this.start = start;
    }

    /**
     * @return the duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Sets duration value
     * @param duration the duration to set
     */
    public void setDuration(final String duration) throws NullPointerException {
        this.duration = duration;
    }

    /**
     * @return secondsToOutput
     */
    public String getSecondsToOutput() {
        return secondsToOutput;
    }

    /**
     * Sets secondsToOutput value
     * @param secondsToOutput the secondsToOutput to set
     */
    public void setSecondsToOutput(final String secondsToOutput) {
        this.secondsToOutput = secondsToOutput;
    }

    /**
     * @return extractMetadata
     */
    public boolean isExtractMetadata() {
        return extractMetadata;
    }

    /**
     * Sets extractMetadata value
     * @param extractMetadata the extractMetadata to set
     */
    public void setExtractMetadata(final boolean extractMetadata) throws NullPointerException {
        this.extractMetadata = extractMetadata;
    }
}
