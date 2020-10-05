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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
/**
 * @author Julia Escalante
 * @version 0.1
 */
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
            throw new InvalidDataException("the file is mull");
        }
        File file = new File(inputFile);
        if (!file.exists()) {
            throw new InvalidDataException("the file or path does not exist");
        }
        String typeFile = null;
        try {
            typeFile = Files.probeContentType(file.toPath());
        } catch (IOException e) {
            throw new InvalidDataException("this file does not type audio");
        }
        if (!typeFile.contains("audio")) {
            throw new InvalidDataException("this file does not type audio");
        }
    }
}
