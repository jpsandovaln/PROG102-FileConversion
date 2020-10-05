/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.model.parameter.multimedia;

import java.util.ArrayList;
import java.util.List;

import org.fundacionjala.converter.model.commons.exception.InvalidDataException;
import org.fundacionjala.converter.model.commons.validation.CodecValidation;
import org.fundacionjala.converter.model.commons.validation.IValidationStrategy;
import org.fundacionjala.converter.model.commons.validation.ValidationContext;
import org.fundacionjala.converter.model.commons.validation.NotNullOrEmpty;
import org.fundacionjala.converter.model.commons.validation.FormatValidation;
import org.fundacionjala.converter.model.commons.validation.FramesGifValidation;
import org.fundacionjala.converter.model.commons.validation.GifValidation;

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
    private static final String LOOP_FOREVER = "0";
    private static final String NO_LOOP = "-1";
    //Parameters of thumbnail
    public static final String PALETTE_THUMBNAIL = "\"fps=10,scale=320:-1:flags=lanczos,split[s0][s1];[s0]palettegen[p];[s1][p]paletteuse\"";
    public static final String START_TIME = "10";
    public static final String DURATION = "5";
    public static final String ZERO = "0";
    public static final String MP4 = ".mp4";
    private String videoCodec;
    private String frames;
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
        switch (this.getFormat()) {
            case MP4:
                validationStrategyList.add(new NotNullOrEmpty("videoCodec", this.videoCodec));
                validationStrategyList.add(new CodecValidation(this.videoCodec));
                validationStrategyList.add(new NotNullOrEmpty("audioCodec", this.getCodec()));
                validationStrategyList.add(new CodecValidation(this.getCodec()));
                break;
            case GIF:
                validationStrategyList.add(new NotNullOrEmpty("frames", this.frames));
                validationStrategyList.add(new NotNullOrEmpty("controlLoop", this.controlLoop));
                validationStrategyList.add(new NotNullOrEmpty("duration", this.getDuration()));
                validationStrategyList.add(new NotNullOrEmpty("start", this.getStart()));
                validationStrategyList.add(new NotNullOrEmpty("secondsToOutput", this.getSecondsToOutput()));
                validationStrategyList.add(new FramesGifValidation(this.frames));
                validationStrategyList.add(new GifValidation(this.getDuration(), this.getStart(), this.getSecondsToOutput()));
                if ((!this.controlLoop.equals(LOOP_FOREVER)) || (!this.controlLoop.equals(NO_LOOP))) {
                    new Exception("Invalid value of controlLoop");
                }
                break;
            default:
                validationStrategyList.add(new NotNullOrEmpty("inputFile", this.getInputFile()));
                validationStrategyList.add(new NotNullOrEmpty("md5", this.getMd5()));
                validationStrategyList.add(new NotNullOrEmpty("format", this.getFormat()));
                validationStrategyList.add(new FormatValidation(this.getFormat()));
                break;
        }
        ValidationContext context = new ValidationContext(validationStrategyList);
        context.validation();
    }
}
