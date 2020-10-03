package org.fundacionjala.converter.model.commons.validation.audio;

import org.fundacionjala.converter.model.commons.exception.InvalidDataException;
import org.fundacionjala.converter.model.commons.validation.IValidationStrategy;
import org.fundacionjala.converter.model.commons.validation.NotNullOrEmpty;

import java.util.ArrayList;
import java.util.List;

public class ChannelAudioValidation implements IValidationStrategy {
    private String channel;
    private List<String> channelsAllowed;
    private static final String MONO = "1";
    private static final String STEREO = "2";
    public ChannelAudioValidation(final String channel) {
        this.channel = channel;
        channelsAllowed = new ArrayList<>();
        channelsAllowed.add(MONO);
        channelsAllowed.add(STEREO);
    }

    /**
     * Validate Audio Channel
     * @throws InvalidDataException
     */
    @Override
    public void validate() throws InvalidDataException {
        if (channel == null || channel.isBlank()) {
            new NotNullOrEmpty("audio channel", channel);
        }
        if (!channelsAllowed.contains(this.channel)) {
            throw new InvalidDataException("Invalid audio channel");
        }
    }
}
