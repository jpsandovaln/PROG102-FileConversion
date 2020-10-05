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

    private static final int SECONDS_MIN = 60;
    private static final int SECONDS_HOUR = 3600;
    private int duration;
    private int time;
    private int start;

    public GifValidation() {
    }

    /**
     * @param duration
     * @param start
     * @param secondsToOutput
     */
    public GifValidation(final String duration, final String start, final String secondsToOutput) throws NumberFormatException, NullPointerException {
        this.duration = convertToSeconds(duration);
        this.start = convertToSeconds(start);
        this.time = Integer.parseInt(secondsToOutput);
    }

    /**
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets duration value
     * @param duration the duration to set
     */
    public void setDuration(final int duration) {
        this.duration = duration;
    }

    /**
     * @return the start
     */
    public int getStart() {
        return start;
    }

    /**
     * Sets start value
     * @param start the start to set
     */
    public void setStart(final int start) {
        this.start = start;
    }

    /**
     * Converts duration to seconds
     * @param duration - String reference to object duration of this video file
     */
    public int convertToSeconds(final String time) throws ArrayIndexOutOfBoundsException, NullPointerException {
        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        int second = Integer.parseInt(parts[2]);
        return second + (SECONDS_MIN * minute) + (SECONDS_HOUR * hour);
    }

    /**
     *
     * @throws InvalidDataException
     */
    @Override
    public void validate() throws InvalidDataException {
        if ((start < 0) || (start >= duration) || (time < 0) || (time >= duration) || ((start + time) >= duration)) {
            throw new InvalidDataException("Invalid arguments to convert a Gif");
        }
    }
}
