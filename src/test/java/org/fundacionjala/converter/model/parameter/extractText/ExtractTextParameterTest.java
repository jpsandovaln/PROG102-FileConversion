package org.fundacionjala.converter.model.parameter.extractText;

import org.fundacionjala.converter.model.commons.exception.InvalidDataException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ExtractTextParameterTest {
    ExtractTextParameter parameter = new ExtractTextParameter();
    @Test
    public void getFileName() {
        parameter.setFileName("storage/inputFiles/imagen.jpg");
        String expected = "storage/inputFiles/imagen.jpg";
        String result = parameter.getFileName();
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void setFileName() {
        parameter.setFileName("storage/inputFiles/imagen2.jpg");
        String expected = "storage/inputFiles/imagen2.jpg";
        String result = parameter.getFileName();
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getLanguage() {
        parameter.setLanguage("spa");
        String expected = "spa";
        String result = parameter.getLanguage();
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void setLanguage() {
        parameter.setLanguage("spa");
        String expected = "spa";
        String result = parameter.getLanguage();
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getFormat() {
        parameter.setFormat(".docx");
        String expected = ".docx";
        String result = parameter.getFormat();
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void setFormat() {
        parameter.setFormat(".docx");
        String expected = ".docx";
        String result = parameter.getFormat();
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testToString() {
        parameter.setLanguage("spa");
        parameter.setFormat(".docx");
        String expected = "ExtractTextParameter [language=spa, format=.docx]";
        String result = parameter.toString();
        Assertions.assertEquals(expected, result);
    }
    @Test
    public void nullFormat() {
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
    public void emptyFormat() {
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
    public void emptyLanguage() {
        Throwable exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    parameter.setLanguage(" ");
                    parameter.validate();
                }
        );
    }
    @Test
    public void nullLanguage() {
        Throwable exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    parameter.setLanguage(null);
                    parameter.validate();
                }
        );
    }
    @Test
    public void invalidFormat() {
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
    public void invalidLanguage() {
        Throwable exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    parameter.setLanguage("spaniol");
                    parameter.validate();
                }
        );
    }
}