package org.fundacionjala.converter.model.command.extractText;

import com.lowagie.text.DocumentException;
import org.fundacionjala.converter.model.commons.exception.InvalidDataException;
import org.fundacionjala.converter.model.parameter.extractText.ExtractTextParameter;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;

public class ExtractTextFacadeTest {

    ExtractTextModel extractTextModel = new ExtractTextModel();
    ExtractTextFacade extractTextFacade = new ExtractTextFacade();
    ExtractTextParameter parameter = new ExtractTextParameter();
    @Test
    public void extractTextWithNullFormat2() {
        Throwable exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    parameter.setFormat("pptx");
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
                    parameter.setFormat("pptx");
                    extractTextFacade.extractText(parameter);
                }
        );
    }
/*


    @Test
    public void extractTextWithNullFormat() {
        assertThatExceptionOfType(InvalidDataException.class)
                .isThrownBy(() -> {
                    parameter.setFormat("pptx");
                    extractTextFacade.extractText(parameter);
                });
    }

    @Test
    public void extractTextWithNullLanguage() {
        assertThatExceptionOfType(InvalidDataException.class)
                .isThrownBy(() -> {
                    parameter.setLanguage(null);
                    extractTextFacade.extractText(parameter);
                });
    }
    /*@Test(expected = InvalidDataException.class)
    public void extractTextWithNullFormat() throws InvalidDataException, InterruptedException, ExecutionException, DocumentException, NoSuchAlgorithmException, IOException {
        parameter.setFormat(null);
        extractTextFacade.extractText(parameter);
    }
    @Test(expected = InvalidDataException.class)
    public void extractTextWithNullLanguage() throws InvalidDataException, InterruptedException, ExecutionException, NoSuchAlgorithmException, DocumentException, IOException {
        parameter.setLanguage(null);
        extractTextFacade.extractText(parameter);
    }
    @Test(expected = InvalidDataException.class)
    public void extractTextWithInvalidLanguage() throws InvalidDataException, InterruptedException, ExecutionException, NoSuchAlgorithmException, DocumentException, IOException {
        parameter.setLanguage("spañol");
        extractTextFacade.extractText(parameter);
    }
    @Test(expected = InvalidDataException.class)
    public void extractTextWithInvalidFormat() throws InvalidDataException, InterruptedException, ExecutionException, NoSuchAlgorithmException, DocumentException, IOException {
        parameter.setFormat("pptx");
        extractTextFacade.extractText(parameter);
    }*/

}