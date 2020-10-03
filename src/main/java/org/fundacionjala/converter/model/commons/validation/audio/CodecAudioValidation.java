package org.fundacionjala.converter.model.commons.validation.audio;

import org.fundacionjala.converter.model.commons.exception.InvalidDataException;
import org.fundacionjala.converter.model.commons.validation.IValidationStrategy;
import org.fundacionjala.converter.model.commons.validation.NotNullOrEmpty;

import java.util.ArrayList;
import java.util.List;

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
        if (!codecsAllowed.contains(this.codec)) {
            throw new InvalidDataException("Invalid audio codec");
        }
        if (codec == null || codec.isBlank()) {
            new NotNullOrEmpty("audio codec", codec);
        }
    }
}
