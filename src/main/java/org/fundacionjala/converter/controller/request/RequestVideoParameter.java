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
    public RequestVideoParameter(final MultipartFile file, final String format, final String sampleRate, final String audioCodec, final String videoCodec) {
        super(file, format, sampleRate, audioCodec);
        this.videoCodec = videoCodec;
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
