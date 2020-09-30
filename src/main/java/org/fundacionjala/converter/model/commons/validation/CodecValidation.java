/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.model.commons.validation;

import java.util.ArrayList;
import java.util.List;

import org.fundacionjala.converter.model.commons.exception.InvalidDataException;
/**
 * @author Laura Montaño
 * @version 0.1
 */
public class CodecValidation implements IValidationStrategy {

    private String codec;
    private List<String> codecsAllowed;
    private static final String LIBX264 = "libx264";
    private static final String H264 = "h264";
    private static final String MP3 = "mp3";
    private static final String COPY = "copy";

    public CodecValidation(final String codec) {
        this.codec = codec;
        codecsAllowed = new ArrayList<>();
        codecsAllowed.add(LIBX264);
        codecsAllowed.add(H264);
        codecsAllowed.add(MP3);
        codecsAllowed.add(COPY);
    }

    /**
     *
     * @throws InvalidDataException
     */
    @Override
    public void validate() throws InvalidDataException {
        if (!codecsAllowed.contains(this.codec)) {
            throw new InvalidDataException("Invalid codec");
        }
    }
}
