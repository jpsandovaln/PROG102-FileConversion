package org.fundacionjala.converter.model.parameter.extractText;

import org.fundacionjala.converter.model.commons.exception.InvalidDataException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ExtractTextParameterTest {
    ExtractTextParameter parameter = new ExtractTextParameter();
    @Test
    public void getFileNameTest() {
        parameter.setFileName("storage/inputFiles/imagen.jpg");
        String expected = "storage/inputFiles/imagen.jpg";
        String result = parameter.getFileName();
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void setFileNameTest() {
        parameter.setFileName("storage/inputFiles/imagen2.jpg");
        String expected = "storage/inputFiles/imagen2.jpg";
        String result = parameter.getFileName();
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getLanguageTest() {
        parameter.setLanguage("spa");
        String expected = "spa";
        String result = parameter.getLanguage();
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void setLanguageTest() {
        parameter.setLanguage("spa");
        String expected = "spa";
        String result = parameter.getLanguage();
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getFormatTest() {
        parameter.setFormat(".docx");
        String expected = ".docx";
        String result = parameter.getFormat();
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void setFormatTest() {
        parameter.setFormat(".docx");
        String expected = ".docx";
        String result = parameter.getFormat();
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testToStringTest() {
        parameter.setLanguage("spa");
        parameter.setFormat(".docx");
        String expected = "ExtractTextParameter [language=spa, format=.docx]";
        String result = parameter.toString();
        Assertions.assertEquals(expected, result);
    }
    @Test
    public void nullFormatTest() {
        Throwable exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    parameter.setLanguage("spa");
                    parameter.setFormat(null);
                    parameter.validate();
                }
        );
    }
    @Test
    public void emptyFormatTest() {
        Throwable exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    parameter.setLanguage("spa");
                    parameter.setFormat(" ");
                    parameter.validate();
                }
        );
    }
    @Test
    public void emptyLanguageTest() {
        Throwable exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    parameter.setLanguage(" ");
                    parameter.validate();
                }
        );
    }
    @Test
    public void nullLanguageTest() {
        Throwable exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    parameter.setLanguage(null);
                    parameter.validate();
                }
        );
    }
    @Test
    public void invalidFormatTest() {
        Throwable exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    parameter.setLanguage("spa");
                    parameter.setFormat("pptx");
                    parameter.validate();
                }
        );
    }
    @Test
    public void invalidLanguageTest() {
        Throwable exception = Assertions.assertThrows(
                InvalidDataException.class, () -> {
                    parameter.setLanguage("spaniol");
                    parameter.validate();
                }
        );
    }
    @Test
    public void invalidInputFileTest() {
        Throwable exception = Assertions.assertThrows(
                InvalidDataException.class, () -> {
                    parameter.setInputFile("pathInvalid");
                    parameter.validate();
                }
        );
    }
    @Test
    public void emptyInputFileTest() {
        Throwable exception = Assertions.assertThrows(
                InvalidDataException.class, () -> {
                    parameter.setInputFile(" ");
                    parameter.validate();
                }
        );
    }
    @Test
    public void nullInputFileTest() {
        Throwable exception = Assertions.assertThrows(
                InvalidDataException.class, () -> {
                    parameter.setInputFile(null);
                    parameter.validate();
                }
        );
    }
}