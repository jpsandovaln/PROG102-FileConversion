package org.fundacionjala.converter.model.commons.validation.audio;

import org.fundacionjala.converter.model.commons.exception.InvalidDataException;
import org.fundacionjala.converter.model.commons.validation.IValidationStrategy;
import org.fundacionjala.converter.model.commons.validation.NotNullOrEmpty;

import java.util.ArrayList;
import java.util.List;

public class FormatAudioValidation implements IValidationStrategy {
    private String format;
    private List<String> formatsAllowed;
    private static final String MP3 = ".mp3";
    private static final String WAV = ".wav";

    public FormatAudioValidation(final String format) {
        this.format = format;
        formatsAllowed = new ArrayList<>();
        formatsAllowed.add(MP3);
        formatsAllowed.add(WAV);
    }

    /**
     * Validate audio Format
     * @throws InvalidDataException
     */
    @Override
    public void validate() throws InvalidDataException {
        if (!formatsAllowed.contains(this.format)) {
            throw new InvalidDataException("Invalid audio format");
        }
        if (format == null || format.isBlank()) {
            new NotNullOrEmpty("audio format", format);
        }
    }
}
