package org.fundacionjala.converter.model.commons.validation;

import org.fundacionjala.converter.model.commons.exception.InvalidDataException;

public class DurationAndStartAudioValidation implements IValidationStrategy {

    private int cutDuration;
    private int cutStart;
    private int totalDuration;

    public DurationAndStartAudioValidation(final String cutDuration, final String cutStart, final String totalDuration) {
        GifValidation gifValidator = new GifValidation();
        this.cutDuration = Integer.parseInt(cutDuration);
        this.cutStart = gifValidator.convertToSeconds(cutStart);
        this.totalDuration = gifValidator.convertToSeconds(totalDuration);
    }

    /**
     * validate duration and start Audio parameters
     * @throws InvalidDataException
     */
    @Override
    public void validate() throws InvalidDataException {
        if ((cutStart < 0) || (totalDuration < 0) || (cutDuration <= 0) || (totalDuration < cutStart + cutDuration)) {
            throw new InvalidDataException("Invalid arguments to cut the audio file.");
        }
    }
}
