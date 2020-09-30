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
/**
 * @author Laura Montaño
 * @version 0.1
 */
public class GifValidation implements IValidationStrategy {

    private int duration;
    private int time;
    private int start;

    /**
     * @param duration
     * @param timeToSkip
     * @param secondsToOutput
     */
    public GifValidation(final String duration, final String timeToSkip, final String secondsToOutput) throws NumberFormatException, NullPointerException {
        this.duration = Integer.parseInt(duration);
        this.start = Integer.parseInt(timeToSkip);
        this.time = Integer.parseInt(secondsToOutput);
    }

    /**
     *
     * @throws InvalidDataException
     */
    @Override
    public void validate() throws InvalidDataException {
        if ((start < 0) && (start >= duration) && (time < 0) && (time <= start) && (time >= duration) && ((start + time) >= duration)) {
            throw new InvalidDataException("Invalid arguments to convert a Gif");
        }
    }
}
