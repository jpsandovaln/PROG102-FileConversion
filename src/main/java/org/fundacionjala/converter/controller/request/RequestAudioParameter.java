/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller.request;

import org.fundacionjala.converter.model.commons.validation.IValidationStrategy;
import org.fundacionjala.converter.model.commons.validation.NotNullOrEmpty;
import org.fundacionjala.converter.model.commons.validation.ValidationContext;
import org.fundacionjala.converter.model.commons.validation.audio.CodecAudioValidation;
import org.fundacionjala.converter.model.commons.validation.audio.SampleRateAudioValidation;
import org.fundacionjala.converter.model.commons.validation.audio.ChannelAudioValidation;
import org.fundacionjala.converter.model.commons.validation.audio.BitRateAudioValidation;
import org.fundacionjala.converter.model.commons.validation.audio.FormatAudioValidation;

import java.util.ArrayList;
import java.util.List;

public class RequestAudioParameter extends RequestMultimediaParameter {

    private String bitRate;
    private String channel;
    private String sampleRate;
    private boolean cut;

    /**
     * Gets if audio is cut or not
     * @return is Audio is cut
     */
    public boolean isCut() {
        return cut;
    }

    /**
     * Sets if audio is cut or not
     * @param cut
     */
    public void setCut(final boolean cut) {
        this.cut = cut;
    }

    /**
     * Gets bit rate
     * @return audio bit rate
     */
    public String getBitRate() {
        return bitRate;
    }

    /**
     * Gets channel
     * @return audio channel
     */
    public String getChannel() {
        return channel;
    }

    /**
     * Gets sample rate
     * @return audio sample rate
     */
    public String getSampleRate() {
        return sampleRate;
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
     * Validates audio parameters
     * @throws Exception
     */
    @Override
    public void validate() throws Exception {
        List<IValidationStrategy> validationStrategyList = new ArrayList<>();
        validationStrategyList.add(new CodecAudioValidation(this.getCodec()));
        validationStrategyList.add(new FormatAudioValidation(this.getExportFormat()));
        validationStrategyList.add(new BitRateAudioValidation(this.bitRate));
        validationStrategyList.add(new ChannelAudioValidation(this.channel));
        validationStrategyList.add(new SampleRateAudioValidation(this.sampleRate));
        validationStrategyList.add(new NotNullOrEmpty("start", this.getStart()));
        validationStrategyList.add(new NotNullOrEmpty("duration", this.getDuration()));
        ValidationContext context = new ValidationContext(validationStrategyList);
        context.validation();

    }
}
