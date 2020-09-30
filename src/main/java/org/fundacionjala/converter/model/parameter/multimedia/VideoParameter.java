package org.fundacionjala.converter.model.parameter.multimedia;

import java.util.ArrayList;
import java.util.List;

import org.fundacionjala.converter.model.commons.exception.InvalidDataException;
import org.fundacionjala.converter.model.commons.validation.CodecValidation;
import org.fundacionjala.converter.model.commons.validation.IValidationStrategy;
import org.fundacionjala.converter.model.commons.validation.ValidationContext;
import org.fundacionjala.converter.model.commons.validation.NotNullOrEmpty;
import org.fundacionjala.converter.model.commons.validation.FormatValidation;

public class VideoParameter extends MultimediaParameter {

    public static final String VCODEC_COMMAND = "-vcodec";
    public static final String ACODEC_COMMAND = "-acodec";
    public static final String INPUT_COMMAND = "-i";
    public static final String START = "-ss";
    public static final String TIME = "-t";
    public static final String VF = "-vf";
    public static final String PALETTE = ",scale=320:-1:flags=lanczos,split[s0][s1];[s0]palettegen[p];[s1][p]paletteuse\"";
    public static final String LOOP = "-loop";
    public static final String FRAME_RATE = "\"fps=";
    public static final String GIF = ".gif";
    public static final String COPY = "copy";
    //Parameters of thumbnail
    public static final String PALETTE_THUMBNAIL = "\"fps=10,scale=320:-1:flags=lanczos,split[s0][s1];[s0]palettegen[p];[s1][p]paletteuse\"";
    public static final String START_TIME = "10";
    public static final String DURATION = "5";
    public static final String ZERO = "0";

    private String name;
    private String videoCodec;
    private String audioCodec;
    private String frames;
    private String timeToSkip;
    private String secondsToOutput;
    private String controlLoop;
    private String duration;
    private boolean extractMetadata = false;
    private boolean extractThumbnail = false;

    /**
     * @param duration
     */
    public VideoParameter(final String duration) {
        this.duration = duration;
    }

    public VideoParameter() {
    }

    /**
     * Sets video codec
     * @param videoCodec
     */
    public void setVideoCodec(final String videoCodec) {
        this.videoCodec = videoCodec;
    }

    /**
     * Gets audio codec
     * @param audioCodec
     */
    public void setAudioCodec(final String audioCodec) {
        this.audioCodec = audioCodec;
    }

    /**
     * Gets video codec
     * @return videoCodec
     */
    public String getVideoCodec() {
        return videoCodec;
    }

    /**
     * Gets audio codec
     * @return audioCodec
     */
    public String getAudioCodec() {
        return audioCodec;
    }

    /**
     * Returns the frames per second
     * @return String - the frames
     */
    public String getFrames() {
        return frames;
    }

    /**
     * Sets the frames per second
     * @param frames
     */
    public void setFrames(final String frames) {
        this.frames = frames;
    }

    /**
     * Returns extractMetadata value
     * @return boolean - the reference to extractMetadata of this object
     */
    public boolean isExtractMetadata() {
        return extractMetadata;
    }

    /**
     * Sets extractMetadata value
     * @param extractMetadata
     */
    public void setExtractMetadata(final boolean extractMetadata) {
        this.extractMetadata = extractMetadata;
    }

    /**
     * Returns extractThumbnail value
     * @return extractThumbnail
     */
    public boolean isExtractThumbnail() {
        return extractThumbnail;
    }

    /**
     * Sets extractThumbnail value
     * @param extractThumbnail
     */
    public void setExtractThumbnail(final boolean extractThumbnail) {
        this.extractThumbnail = extractThumbnail;
    }

    /**
     * @return the timeToSkip
     */
    public String getTimeToSkip() {
        return timeToSkip;
    }

    /**
     * @param timeToSkip the timeToSkip to set
     */
    public void setTimeToSkip(final String timeToSkip) {
        this.timeToSkip = timeToSkip;
    }

    /**
     * @return the secondsToOutput
     */
    public String getSecondsToOutput() {
        return secondsToOutput;
    }

    /**
     * @param secondsToOutput the secondsToOutput to set
     */
    public void setSecondsToOutput(final String secondsToOutput) {
        this.secondsToOutput = secondsToOutput;
    }

    /**
     * @return the controlLoop
     */
    public String getControlLoop() {
        return controlLoop;
    }

    /**
     * A value of 0 is infinite looping, -1 is no looping, and 1 will loop once meaning it will play twice. So a value of 10 will cause the GIF to play 11 times.
     * @param controlLoop the controlLoop to set
     */
    public void setControlLoop(final String controlLoop) {
        this.controlLoop = controlLoop;
    }

    /**
     * @return the duration in seconds
     */
    public String getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(final String duration) {
        this.duration = duration;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Validates the parameters
     * @throws InvalidDataException
     */
    public void validate() throws InvalidDataException {
        List<IValidationStrategy> validationStrategyList = new ArrayList<>();
        validationStrategyList.add(new CodecValidation(this.videoCodec));
        validationStrategyList.add(new CodecValidation(this.audioCodec));
        validationStrategyList.add(new NotNullOrEmpty("videoCodec", this.videoCodec));
        validationStrategyList.add(new NotNullOrEmpty("audioCodec", this.audioCodec));
        validationStrategyList.add(new NotNullOrEmpty("format", this.getFormat()));
        validationStrategyList.add(new FormatValidation(this.getFormat()));
        ValidationContext context = new ValidationContext(validationStrategyList);
        context.validation();
    }
}
