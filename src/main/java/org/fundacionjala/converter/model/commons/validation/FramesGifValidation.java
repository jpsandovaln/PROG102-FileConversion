/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.model.commons.validation;

import org.fundacionjala.converter.model.commons.exception.InvalidDataException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Laura Montaño
 * @version 0.1
 */
public class FramesGifValidation implements IValidationStrategy {
    private String frames;
    public static final String FRAMES_21 = "21";
    public static final String FRAMES_24 = "24";
    public static final String FRAMES_27 = "27";
    public static final String FRAMES_30 = "30";
    private List<String> framesAllowed;

    public FramesGifValidation(final String frames) {
        this.frames = frames;
        framesAllowed = new ArrayList<>();
        framesAllowed.add(FRAMES_21);
        framesAllowed.add(FRAMES_24);
        framesAllowed.add(FRAMES_27);
        framesAllowed.add(FRAMES_30);
    }

    /**
     * Validates the framess of the gif command
     * @throws InvalidDataException
     */
    @Override
    public void validate() throws InvalidDataException {
        if (!framesAllowed.contains(this.frames)) {
            throw new InvalidDataException("Invalid frame");
        }
    }
}
