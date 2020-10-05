/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller.request;

import java.util.ArrayList;
import java.util.List;

import org.fundacionjala.converter.model.commons.exception.InvalidDataException;
import org.fundacionjala.converter.model.commons.validation.CodecValidation;
import org.fundacionjala.converter.model.commons.validation.IValidationStrategy;
import org.fundacionjala.converter.model.commons.validation.ValidationContext;
import org.fundacionjala.converter.model.commons.validation.NotNullOrEmpty;
import org.fundacionjala.converter.model.commons.validation.FramesGifValidation;
import org.fundacionjala.converter.model.commons.validation.GifValidation;

public class RequestVideoParameter extends RequestMultimediaParameter {

    private static final String MOV = ".mov";
    private static final String GIF = ".gif";
    private static final String LOOP_FOREVER = "0";
    private static final String NO_LOOP = "-1";
    private static final String CODEC = "mp3";
    private String videoCodec;
    private String frames;
    private String controlLoop;
    private boolean extractThumbnail;

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
    public void setFrames(final String frames) {
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
    public void setExtractThumbnail(final boolean extractThumbnail) {
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
    public void setControlLoop(final String controlLoop) {
        this.controlLoop = controlLoop;
    }

    /**
     * Validates the video parameters
     * @throws Exception
     * @throws InvalidDataException
     */
    @Override
    public void validate() throws Exception, InvalidDataException {
        List<IValidationStrategy> validationStrategyList;
        if (this.getExportFormat().equals(MOV)) {
            this.setCodec(CODEC);
        } else {
            validationStrategyList = new ArrayList<>();
            if (this.getExportFormat().equals(GIF)) {
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
            } else {
                validationStrategyList.add(new NotNullOrEmpty("videoCodec", this.videoCodec));
                validationStrategyList.add(new CodecValidation(this.videoCodec));
            }
            ValidationContext context = new ValidationContext(validationStrategyList);
            context.validation();
        }
        super.validate();
    }
}
