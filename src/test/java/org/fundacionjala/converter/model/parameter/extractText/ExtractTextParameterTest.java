package org.fundacionjala.converter.model.parameter.extractText;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExtractTextParameterTest {
    ExtractTextParameter parameter = new ExtractTextParameter();
    @Test
    public void getFileName() {
        parameter.setFileName("storage/inputFiles/imagen.jpg");
        String expected = "storage/inputFiles/imagen.jpg";
        String result = parameter.getFileName();
        assertEquals(expected, result);
    }

    @Test
    public void setFileName() {
        parameter.setFileName("storage/inputFiles/imagen2.jpg");
        String expected = "storage/inputFiles/imagen2.jpg";
        String result = parameter.getFileName();
        assertEquals(expected, result);
    }

    @Test
    public void getLanguage() {
        parameter.setLanguage("spa");
        String expected = "spa";
        String result = parameter.getLanguage();
        assertEquals(expected, result);
    }

    @Test
    public void setLanguage() {
        parameter.setLanguage("spa");
        String expected = "spa";
        String result = parameter.getLanguage();
        assertEquals(expected, result);
    }

    @Test
    public void getFormat() {
        parameter.setFormat(".docx");
        String expected = ".docx";
        String result = parameter.getFormat();
        assertEquals(expected, result);
    }

    @Test
    public void setFormat() {
        parameter.setFormat(".docx");
        String expected = ".docx";
        String result = parameter.getFormat();
        assertEquals(expected, result);
    }

    @Test
    public void testToString() {
        parameter.setLanguage("spa");
        parameter.setFormat(".docx");
        String expected = "ExtractTextParameter [language=spa, format=.docx]";
        String result = parameter.toString();
        assertEquals(expected, result);
    }
}