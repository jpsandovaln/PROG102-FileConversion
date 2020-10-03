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
    public static final String MP4 = ".mp4";
    private String videoCodec;
    private String frames;
    private String secondsToOutput;
    private String controlLoop;
    private boolean extractThumbnail = false;
    private List<String> outputFiles;

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
     * @return videoCodec
     */
    public String getVideoCodec() {
        return videoCodec;
    }

    /**
     * @return frames
     */
    public String getFrames() {
        return frames;
    }

    /**
     * Sets the frames per second
     * @param frames the frames to set
     */
    public void setFrames(final String frames) throws NullPointerException {
        this.frames = frames;
    }

    /**
     * @return extractThumbnail
     */
    public boolean isExtractThumbnail() {
        return extractThumbnail;
    }

    /**
     * Sets extractThumbnail value
     * @param extractThumbnail the extractThumbnail to set
     */
    public void setExtractThumbnail(final boolean extractThumbnail) throws NullPointerException {
        this.extractThumbnail = extractThumbnail;
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
    public void setSecondsToOutput(final String secondsToOutput) throws NullPointerException {
        this.secondsToOutput = secondsToOutput;
    }

    /**
     * @return controlLoop
     */
    public String getControlLoop() {
        return controlLoop;
    }

    /**
     * Sets controlLoop value
     * @param controlLoop the controlLoop to set
     */
    public void setControlLoop(final String controlLoop) throws NullPointerException {
        this.controlLoop = controlLoop;
    }

    /**
     * @return the outputFiles
     */
    public List<String> getOutputFiles() throws NullPointerException {
        return outputFiles;
    }

    /**
     * Sets outputFiles value
     * @param outputFiles the outputFiles to set
     */
    public void setOutputFiles(final List<String> outputFiles) throws NullPointerException {
        this.outputFiles = outputFiles;
    }
    /**
     * Validates the video parameters
     * @throws InvalidDataException
     */
    public void validate() throws InvalidDataException {
        List<IValidationStrategy> validationStrategyList = new ArrayList<>();
        validationStrategyList.add(new CodecValidation(this.videoCodec));
        validationStrategyList.add(new CodecValidation(this.getCodec()));
        validationStrategyList.add(new NotNullOrEmpty("videoCodec", this.videoCodec));
        validationStrategyList.add(new NotNullOrEmpty("audioCodec", this.getCodec()));
        validationStrategyList.add(new NotNullOrEmpty("format", this.getFormat()));
        validationStrategyList.add(new FormatValidation(this.getFormat()));
        ValidationContext context = new ValidationContext(validationStrategyList);
        context.validation();
    }
}
