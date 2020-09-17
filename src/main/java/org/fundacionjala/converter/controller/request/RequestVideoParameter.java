/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller.request;

import org.springframework.web.multipart.MultipartFile;

public class RequestVideoParameter extends RequestMultimediaParameter {

    private String videoCodec;

    public void setVideoCodec(String videoCodec) {
        this.videoCodec = videoCodec;
    }

    public String getFrames() {
        return frames;
    }

    public void setFrames(String frames) {
        this.frames = frames;
    }

    public int getExtractThumbnail() {
        return extractThumbnail;
    }

    public void setExtractThumbnai(int extractFrame) {
        this.extractThumbnail = extractFrame;
    }

    private String frames;
    private int extractThumbnail;


    public RequestVideoParameter(MultipartFile file, String format, String sampleRate, String audioCodec, String videoCodec, String frames, int extractThumbnail) {
        super(file, format, sampleRate, audioCodec);
        this.videoCodec = videoCodec;
        this.frames = frames;
        this.extractThumbnail = extractThumbnail;
    }



    /**
     *
     * @throws Exception
     */
    @Override
    public void validate() throws Exception {
        super.validate();
        if (this.getVideoCodec() == null || "".equals(this.getVideoCodec())) {
            throw new Exception("failed Channels Rate empty");
        }
    }

    /**
     *
     * @return
     */
    public String getVideoCodec() {
        return videoCodec;
    }


}
