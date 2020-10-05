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
        if (format == null || format.isBlank()) {
            new NotNullOrEmpty("audio format", format);
        }
        if (!formatsAllowed.contains(this.format)) {
            throw new InvalidDataException("Invalid audio format");
        }
    }
}
