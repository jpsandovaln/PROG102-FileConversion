package org.fundacionjala.converter.model.parameter.multimedia;

import org.fundacionjala.converter.model.commons.exception.InvalidDataException;
import org.fundacionjala.converter.model.commons.validation.IValidationStrategy;
import org.fundacionjala.converter.model.commons.validation.ModelParameterValidation;
import org.fundacionjala.converter.model.commons.validation.ValidationContext;
import org.fundacionjala.converter.model.commons.validation.audio.InputFileAudioValidation;
import org.fundacionjala.converter.model.commons.validation.audio.FormatAudioValidation;
import org.fundacionjala.converter.model.commons.validation.audio.BitRateAudioValidation;
import org.fundacionjala.converter.model.commons.validation.audio.ChannelAudioValidation;
import org.fundacionjala.converter.model.commons.validation.audio.SampleRateAudioValidation;
import org.fundacionjala.converter.model.commons.validation.audio.CodecAudioValidation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AudioParameter extends MultimediaParameter {

    private String format;
    private String bitRate;
    private String channel;
    private String sampleRate;
    private boolean isCut = false;

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
            super.setInputFile(inputFile);
    }

    /**
     * Sets format value
     * @param format the  to set
     */
    public void setFormat(final String format) {
            this.format = format;
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
     * Sets cut value
     * @param cut the  to set
     */
    public void setCut(final boolean cut) {
        isCut = cut;
    }

    /**
     * Validate audioParameter fields
     */
    public void validate() throws InvalidDataException {
        List<IValidationStrategy> validationStrategyList = new ArrayList<>();
        validationStrategyList.add(new ModelParameterValidation(this));
        validationStrategyList.add(new CodecAudioValidation(super.getCodec()));
        validationStrategyList.add(new FormatAudioValidation(this.format));
        validationStrategyList.add(new BitRateAudioValidation(this.bitRate));
        validationStrategyList.add(new ChannelAudioValidation(this.channel));
        validationStrategyList.add(new SampleRateAudioValidation(this.sampleRate));
        validationStrategyList.add(new InputFileAudioValidation(this.getInputFile()));
        ValidationContext context = new ValidationContext(validationStrategyList);
        context.validation();
    }
}
