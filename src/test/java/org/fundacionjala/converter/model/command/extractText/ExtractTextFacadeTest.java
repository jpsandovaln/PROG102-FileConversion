package org.fundacionjala.converter.model.command.extractText;

import org.fundacionjala.converter.model.commons.exception.ConvertDocException;
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

    ExtractTextModel extractTextModel = new ExtractTextModel();
    ExtractTextFacade extractTextFacade = new ExtractTextFacade();
    ExtractTextParameter parameter = new ExtractTextParameter();
    @Test
    public void extractTextWithNullFormat() {
        Throwable exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    parameter.setLanguage("spa");
                    parameter.setFormat(null);
                    extractTextFacade.extractText(parameter);
                }
        );
    }
    @Test
    public void extractTextWithEmptyFormat() {
        Throwable exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    parameter.setLanguage("spa");
                    parameter.setFormat(" ");
                    extractTextFacade.extractText(parameter);
                }
        );
    }
    @Test
    public void extractTextWithNullLanguage() {
        Throwable exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    parameter.setLanguage(null);
                    extractTextFacade.extractText(parameter);
                }
        );
    }
    @Test
    public void extractTextWithInvalidFormat() {
        Throwable exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    parameter.setLanguage("spa");
                    parameter.setFormat("pptx");
                    extractTextFacade.extractText(parameter);
                }
        );
    }
    @Test
    public void extractTextWithFormatTxt() throws IOException, InterruptedException, ExecutionException, NoSuchAlgorithmException, InvalidDataException, ReadFileException, ConvertDocException {
        parameter.setFormat(".txt");
        parameter.setLanguage("spa");
        parameter.setFileName("demo");
        parameter.setInputFile("storage/inputFiles/imagen7.jpg");
        List<String> expected = new ArrayList<>();
        expected.add("storage/convertedFiles/ffa5f5433efe74bc99530e84798b2ffd.txt");
        List<String> result = new ArrayList<>();
        result = extractTextFacade.extractText(parameter);
        Assertions.assertEquals(expected, result);
        Files.deleteIfExists(Path.of("storage/convertedFiles/ffa5f5433efe74bc99530e84798b2ffd.txt"));
    }
    @Test
    public void extractTextWithFormatDocx() throws IOException, InterruptedException, ExecutionException, NoSuchAlgorithmException, InvalidDataException, ReadFileException, ConvertDocException {
        parameter.setFormat(".docx");
        parameter.setLanguage("spa");
        parameter.setFileName("demo");
        parameter.setInputFile("storage/inputFiles/imagen7.jpg");
        List<String> expected = new ArrayList<>();
        expected.add("storage/convertedFiles/ffa5f5433efe74bc99530e84798b2ffd.docx");
        List<String> result = new ArrayList<>();
        result = extractTextFacade.extractText(parameter);
        Assertions.assertEquals(expected, result);
        Files.deleteIfExists(Path.of("storage/convertedFiles/ffa5f5433efe74bc99530e84798b2ffd.docx"));
    }
    @Test
    public void extractTextWithFormatPdf() throws IOException, InterruptedException, ExecutionException, NoSuchAlgorithmException, InvalidDataException, ReadFileException, ConvertDocException {
        parameter.setFormat(".pdf");
        parameter.setLanguage("spa");
        parameter.setFileName("demo");
        parameter.setInputFile("storage/inputFiles/imagen7.jpg");
        List<String> expected = new ArrayList<>();
        expected.add("storage/convertedFiles/ffa5f5433efe74bc99530e84798b2ffd.pdf");
        List<String> result = new ArrayList<>();
        result = extractTextFacade.extractText(parameter);
        Assertions.assertEquals(expected, result);
        Files.deleteIfExists(Path.of("storage/convertedFiles/ffa5f5433efe74bc99530e84798b2ffd.pdf"));
    }
    @Test
    public void extractTextWithLanguageEnglish() throws IOException, InterruptedException, ExecutionException, NoSuchAlgorithmException, InvalidDataException, ReadFileException, ConvertDocException {
        parameter.setFormat(".pdf");
        parameter.setLanguage("en");
        parameter.setFileName("demo");
        parameter.setInputFile("storage/inputFiles/imagen7.jpg");
        List<String> expected = new ArrayList<>();
        expected.add("storage/convertedFiles/ffa5f5433efe74bc99530e84798b2ffd.pdf");
        List<String> result = new ArrayList<>();
        result = extractTextFacade.extractText(parameter);
        Assertions.assertEquals(expected, result);
        Files.deleteIfExists(Path.of("storage/convertedFiles/ffa5f5433efe74bc99530e84798b2ffd.pdf"));
    }
    @Test
    public void extractTextWithInvalidLanguage() {
        Throwable exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    parameter.setLanguage("spañol");
                    extractTextFacade.extractText(parameter);
                }
        );
    }
    @Test
    public void extractTextWithEmptyLanguage() {
        Throwable exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    parameter.setFormat(" ");
                    extractTextFacade.extractText(parameter);
                }
        );
    }
}