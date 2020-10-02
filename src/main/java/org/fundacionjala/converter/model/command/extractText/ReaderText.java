/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.model.command.extractText;

import org.fundacionjala.converter.model.commons.exception.ReadFileException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReaderText {
    private BufferedReader buffer;

    public ReaderText() {
        buffer = null;
    }

    /**
     * Reads a file
     * @param fileName the path and name of the file
     * @return the content of the file in a string
     */
    public String readFile(final String fileName) throws ReadFileException {
        String readString = "";
        try {
            String currentLine;
            buffer = new BufferedReader(new FileReader(fileName));
            while ((currentLine = buffer.readLine()) != null) {
                readString += currentLine;
            }
        } catch (IOException e) {
            throw new ReadFileException("Error while reading a file");
        } finally {
            if (buffer != null) {
                try {
                    buffer.close();
                } catch (IOException e) {
                    throw new ReadFileException("Error while reading a file");
                }
            }
        }
        return readString;
    }
}
