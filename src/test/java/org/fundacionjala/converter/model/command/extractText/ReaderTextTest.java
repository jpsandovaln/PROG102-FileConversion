package org.fundacionjala.converter.model.command.extractText;

import org.fundacionjala.converter.model.commons.exception.InvalidDataException;
import org.fundacionjala.converter.model.commons.exception.ReadFileException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReaderTextTest {
    ReaderText reader = new ReaderText();
    @Test
    void readFileWithInvalidPathFile() {
        Throwable exception = Assertions.assertThrows(
                ReadFileException.class,
                () -> {
                    reader.readFile("pathFile");
                }
        );
    }
}