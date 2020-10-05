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
public class CodecAudioValidation implements IValidationStrategy {
    private String codec;
    private List<String> codecsAllowed;
    private static final String LIBMP3LAME = "libmp3lame";

    public CodecAudioValidation(final String codec) {
        this.codec = codec;
        codecsAllowed = new ArrayList<>();
        codecsAllowed.add(LIBMP3LAME);
    }

    /**
     * validate audio codec
     * @throws InvalidDataException
     */
    @Override
    public void validate() throws InvalidDataException {
        if (codec == null || codec.isBlank()) {
            new NotNullOrEmpty("audio codec", codec);
        }
        if (!codecsAllowed.contains(this.codec)) {
            throw new InvalidDataException("Invalid audio codec");
        }
    }
}
