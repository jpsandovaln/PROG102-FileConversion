package org.fundacionjala.converter.model.parameter.metadata;

import org.fundacionjala.converter.model.commons.exception.InvalidDataException;
import org.fundacionjala.converter.model.parameter.ModelParameter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MetadataParameterTest {
    MetadataParameter metadataParameter = new MetadataParameter(
            "storage/inputFiles/test.mp3",
            "j", "v", "meta",
            "e1b3fab24c8af81c1aa13dbbb4e44ff0");
    @Test
    public void metadataWithNullFormat() {
        Throwable exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    metadataParameter.setFormat(null);
                    metadataParameter.validate();
                }
        );
    }

    @Test
    public void metadataWithInvalidFormat() {
        Throwable exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    metadataParameter.setFormat("k");
                    metadataParameter.validate();
                }
        );
    }

    @Test
    public void metadataWithNullDetail() {
        Throwable exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    metadataParameter.setDetail(null);
                    metadataParameter.validate();
                }
        );
    }

    @Test
    public void metadataWithInvalidDetail() {
        Throwable exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    metadataParameter.setDetail("k");
                    metadataParameter.validate();
                }
        );
    }

    @Test
    public void metadataWithNullDetailAndFormat() {
        Throwable exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    metadataParameter.setDetail(null);
                    metadataParameter.setFormat(null);
                    metadataParameter.validate();
                }
        );
    }

    @Test
    public void metadataWithInvalidDetailAndFormat() {
        Throwable exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    metadataParameter.setDetail("k");
                    metadataParameter.setFormat("k");
                    metadataParameter.validate();
                }
        );
    }

    @Test
    public void getDetailMetadataParameterTest() {
        String result = metadataParameter.getDetail();
        String expected = "v";
        assertEquals(result, expected);
    }

    @Test
    public void getFormatMetadataParameterTest() {
        String result = metadataParameter.getFormat();
        String expected = "j";
        assertEquals(result, expected);
    }

    @Test
    public void getInputFileMetadataParameterTest() {
        String result = metadataParameter.getInputFile();
        String expected = "storage/inputFiles/test.mp3";
        assertEquals(result, expected);
    }

    @Test
    public void getOutputFileMetadataParameterTest() {
        String result = metadataParameter.getOutputFile();
        String expected = "meta";
        assertEquals(result, expected);
    }

    @Test
    public void getMd5MetadataParameterTest() {
        String result = metadataParameter.getMd5();
        String expected = "e1b3fab24c8af81c1aa13dbbb4e44ff0";
        assertEquals(expected, result);
    }

    @Test
    public void setDetailsMetadataParameterTest() throws InvalidDataException {
        metadataParameter.setDetail("common");
        metadataParameter.validate();
        String result = metadataParameter.getDetail();
        String expected = "common";
        assertEquals(result, expected);
    }

    @Test
    public void setFormatMetadataParameterTest() throws InvalidDataException {
        metadataParameter.setFormat("T");
        metadataParameter.validate();
        String result = metadataParameter.getFormat();
        String expected = "T";
        assertEquals(result, expected);
    }

    @Test
    public void setInputFileMetadataParameterTest() throws IOException {
        metadataParameter.setInputFile("storage/inputFiles/aud.txt");
        String result = metadataParameter.getInputFile();
        String expected = "storage/inputFiles/aud.txt";
        assertEquals(result, expected);
    }

    @Test
    public void setOutputFileMetadataParameterTest() {
        metadataParameter.setOutputFile("metadata");
        String result = metadataParameter.getOutputFile();
        String expected = "metadata";
        assertEquals(result, expected);
    }

    @Test
    public void setMd5MetadataParameterTest() {
        metadataParameter.setMd5("e1b3fab24c8af81c1aa13duse4e44ff0");
        String result = metadataParameter.getMd5();
        String expected = "e1b3fab24c8af81c1aa13duse4e44ff0";
        assertNotEquals(expected, result);
    }
}