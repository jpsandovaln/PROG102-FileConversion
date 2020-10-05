/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.model.commons.validation.audio;

import org.fundacionjala.converter.model.commons.exception.InvalidDataException;
import org.fundacionjala.converter.model.commons.validation.IValidationStrategy;
import org.fundacionjala.converter.model.commons.validation.NotNullOrEmpty;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Julia Escalante
 * @version 0.1
 */
public class SampleRateAudioValidation implements IValidationStrategy {
    private String sampleRate;
    private List<String> sampleRatesAllowed;
    private static final String SAMPLE_RATE1 = "22050";

    public SampleRateAudioValidation(final String sampleRate) {
        this.sampleRate = sampleRate;
        sampleRatesAllowed = new ArrayList<>();
        sampleRatesAllowed.add(SAMPLE_RATE1);
    }

    /**
     * Validate sample rate audio
     * @throws InvalidDataException
     */
    @Override
    public void validate() throws InvalidDataException {
        if (sampleRate == null || sampleRate.isBlank()) {
            new NotNullOrEmpty("sampleRate", sampleRate);
        }
        if (!sampleRatesAllowed.contains(this.sampleRate)) {
            throw new InvalidDataException("Invalid sample rate");
        }
    }
}
