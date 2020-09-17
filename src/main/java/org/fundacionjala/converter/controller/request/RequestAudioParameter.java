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

public class RequestAudioParameter extends RequestMultimediaParameter {

    private String bitRate;
    private int channels;
    public RequestAudioParameter(final MultipartFile file, final String format,
                                 final String sampleRate, final String audioCodec, final String bitRate, final int channels) {
        super(file, format, sampleRate, audioCodec);
        this.bitRate = bitRate;
        this.channels = channels;
    }

    /**
     *
     * @throws Exception
     */
    @Override
    public void validate() throws Exception {
        super.validate();
        if (this.getBitRate() == null || "".equals(this.getBitRate())) {
            throw new Exception("failed BitRate Rate empty");
        }
        if ("".equals(this.getChannels())) {
            throw new Exception("failed Channels Rate empty");
        }

    }


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


}
