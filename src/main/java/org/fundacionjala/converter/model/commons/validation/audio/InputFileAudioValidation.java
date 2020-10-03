package org.fundacionjala.converter.model.commons.validation.audio;

import org.fundacionjala.converter.model.commons.exception.InvalidDataException;
import org.fundacionjala.converter.model.commons.validation.IValidationStrategy;
import org.fundacionjala.converter.model.commons.validation.NotNullOrEmpty;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class InputFileAudioValidation implements IValidationStrategy {
    private String inputFile;
    public InputFileAudioValidation(final String inputFile) {
        this.inputFile = inputFile;
    }

    /**
     * validate file audio
     * @throws InvalidDataException
     */
    @Override
    public void validate() throws InvalidDataException {
        if (inputFile == null || inputFile.isBlank()) {
            new NotNullOrEmpty("inputFile", inputFile);
        }
        File file = new File(inputFile);
        if (!file.exists()) {
            throw new InvalidDataException("the file or path does not exist");
        }
        String typeFile = null;
        try {
            typeFile = Files.probeContentType(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!typeFile.contains("audio")) {
            throw new InvalidDataException("this file does not audio");
        }
    }
}
