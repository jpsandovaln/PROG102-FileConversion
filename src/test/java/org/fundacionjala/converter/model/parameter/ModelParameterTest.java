package org.fundacionjala.converter.model.parameter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.fundacionjala.converter.model.parameter.multimedia.AudioParameter;

public class ModelParameterTest {

    ModelParameter parameter = new AudioParameter();

    @Test
    public void testSetInputFileNullThrowsNullPointerException() {
        try {
            parameter.setInputFile(null);
        } catch (NullPointerException | IOException e) {
            assertEquals("NullPointerException", e.getMessage());
        }
    }
    @Test
    public void testSetOutputFileNullThrowsNullPointerException() {
        try {
            parameter.setOutputFile(null);
        } catch (NullPointerException e) {
            assertEquals("NullPointerException", e.getMessage());
        }
    }
    @Test
    public void testSetMd5NullThrowsNullPointerException() {
        try {
            parameter.setMd5(null);
        } catch (NullPointerException e) {
            assertEquals("NullPointerException", e.getMessage());
        }
    }
    @Test
    public void testSetFormatNullThrowsNullPointerException() {
        try {
            parameter.setFormat(null);
        } catch (NullPointerException e) {
            assertEquals("NullPointerException", e.getMessage());
        }
    }
}