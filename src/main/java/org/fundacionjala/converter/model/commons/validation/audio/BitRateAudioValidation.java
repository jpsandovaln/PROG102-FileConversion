package org.fundacionjala.converter.model.commons.validation.audio;

import org.fundacionjala.converter.model.commons.exception.InvalidDataException;
import org.fundacionjala.converter.model.commons.validation.IValidationStrategy;
import org.fundacionjala.converter.model.commons.validation.NotNullOrEmpty;

import java.util.ArrayList;
import java.util.List;

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
