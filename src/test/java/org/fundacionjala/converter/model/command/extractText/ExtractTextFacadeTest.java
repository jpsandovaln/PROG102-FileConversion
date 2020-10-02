package org.fundacionjala.converter.model.command.extractText;

import org.fundacionjala.converter.model.commons.exception.ConvertDocException;
import org.fundacionjala.converter.model.commons.exception.ExtractTextException;
import org.fundacionjala.converter.model.commons.exception.InvalidDataException;
import org.fundacionjala.converter.model.commons.exception.ReadFileException;
import org.fundacionjala.converter.model.parameter.extractText.ExtractTextParameter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.io.IOException;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.nio.file.Files;

public class ExtractTextFacadeTest {

    ExtractTextFacade extractTextFacade = new ExtractTextFacade();
    ExtractTextParameter parameter = new ExtractTextParameter();

    @Test
    public void extractTextWithFormatTxt() throws IOException, ExtractTextException {
        parameter.setFormat(".txt");
        parameter.setLanguage("spa");
        parameter.setFileName("demo");
        parameter.setInputFile("storage/inputFiles/imagen7.jpg");
        List<String> expected = new ArrayList<>();
        expected.add("storage/convertedFiles/ffa5f5433efe74bc99530e84798b2ffd.txt");
        List<String> result;
        result = extractTextFacade.extractText(parameter);
        Assertions.assertEquals(expected, result);
        Files.deleteIfExists(Path.of("storage/convertedFiles/ffa5f5433efe74bc99530e84798b2ffd.txt"));
    }
    @Test
    public void extractTextWithFormatDocx() throws IOException, ExtractTextException {
        parameter.setFormat(".docx");
        parameter.setLanguage("spa");
        parameter.setFileName("demo");
        parameter.setInputFile("storage/inputFiles/imagen7.jpg");
        List<String> expected = new ArrayList<>();
        expected.add("storage/convertedFiles/ffa5f5433efe74bc99530e84798b2ffd.docx");
        List<String> result;
        result = extractTextFacade.extractText(parameter);
        Assertions.assertEquals(expected, result);
        Files.deleteIfExists(Path.of("storage/convertedFiles/ffa5f5433efe74bc99530e84798b2ffd.docx"));
    }
    @Test
    public void extractTextWithFormatPdf() throws IOException, ExtractTextException {
        parameter.setFormat(".pdf");
        parameter.setLanguage("spa");
        parameter.setFileName("demo");
        parameter.setInputFile("storage/inputFiles/imagen7.jpg");
        List<String> expected = new ArrayList<>();
        expected.add("storage/convertedFiles/ffa5f5433efe74bc99530e84798b2ffd.pdf");
        List<String> result;
        result = extractTextFacade.extractText(parameter);
        Assertions.assertEquals(expected, result);
        Files.deleteIfExists(Path.of("storage/convertedFiles/ffa5f5433efe74bc99530e84798b2ffd.pdf"));
    }
    @Test
    public void extractTextWithLanguageEnglish() throws IOException, ExtractTextException{
        parameter.setFormat(".pdf");
        parameter.setLanguage("en");
        parameter.setFileName("demo");
        parameter.setInputFile("storage/inputFiles/imagen7.jpg");
        List<String> expected = new ArrayList<>();
        expected.add("storage/convertedFiles/ffa5f5433efe74bc99530e84798b2ffd.pdf");
        List<String> result;
        result = extractTextFacade.extractText(parameter);
        Assertions.assertEquals(expected, result);
        Files.deleteIfExists(Path.of("storage/convertedFiles/ffa5f5433efe74bc99530e84798b2ffd.pdf"));
    }
}