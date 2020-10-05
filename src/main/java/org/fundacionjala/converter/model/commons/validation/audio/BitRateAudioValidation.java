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
public class BitRateAudioValidation implements IValidationStrategy {
    private String bitRate;
    private List<String> bitRatesAllowed;
    private static final String WAV_BIT_RATE = "32k";
    private static final String MP3_BIT_RATE = "128k";
    public BitRateAudioValidation(final String bitRate) {
        this.bitRate = bitRate;
        bitRatesAllowed = new ArrayList<>();
        bitRatesAllowed.add(WAV_BIT_RATE);
        bitRatesAllowed.add(MP3_BIT_RATE);
    }

    /**
     * Validate bit rate audio
     * @throws InvalidDataException
     */
    @Override
    public void validate() throws InvalidDataException {
        if (bitRate == null || bitRate.isBlank()) {
            new NotNullOrEmpty("audio bit rate", bitRate);
        }
        if (!bitRatesAllowed.contains(this.bitRate)) {
            throw new InvalidDataException("Invalid audio bit rate");
        }
    }
}
