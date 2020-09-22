package org.fundacionjala.converter.model.parameter.extractText;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.fundacionjala.converter.model.command.ExtractTextModel;
import org.fundacionjala.converter.model.parameter.ModelParameter;

public class ExtractTextParameterTest {
    String inputFile = "D:\\AT\\PROG102\\code\\PROG102-FileConversion\\storage\\inputFiles\\image.png";
    String outputFile = "D:\\AT\\PROG102\\code\\PROG102-FileConversion\\storage\\outputFiles\\image-txt";
    String languageSp = "español";
    String languageEn = "english";
    String type = "word";
    String toolPath = "thirdParty/ocr/tesseract.exe";
    ModelParameter eTextParameter = new ExtractTextParameter();
    ExtractTextModel eTextModelSp = new ExtractTextModel();
    List<List<String>> listCommands = new ArrayList<>();

    @Test
    public void selectLanguageSpanish() {
        ((ExtractTextParameter) eTextParameter).setLanguage(languageSp);
        String expected = "-l spa";
        String actual = ((ExtractTextParameter) eTextParameter).getLanguage();
        assertEquals(expected, actual);
    }

    @Test
    public void selectTypeWord() {
        ((ExtractTextParameter) eTextParameter).setType(type);
        String expected = ".docx";
        String actual = ((ExtractTextParameter) eTextParameter).getType();
        assertEquals(expected, actual);
    }

    @Test
    public void createExTextParameterWithInitialParameters() {
        ((ExtractTextParameter) eTextParameter).setLanguage(languageSp);
        ((ExtractTextParameter) eTextParameter).setType(type);
        String expected = "ExtractTextParameter [language=-l spa, type=.docx]";
        String actual = eTextParameter.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void createCommandWhenLanguageisSpanishAndDocumentIsWord() throws IOException {
        String expected = toolPath + " " + inputFile + " " + outputFile + " " + "-l spa";
        eTextParameter.setInputFile(inputFile);
        eTextParameter.setOutputFile(outputFile);
        ((ExtractTextParameter) eTextParameter).setLanguage(languageSp);
        ((ExtractTextParameter) eTextParameter).setType(type);
        listCommands = eTextModelSp.createCommand(eTextParameter);
        String actual = listCommands.get(0).get(0) + " " + listCommands.get(0).get(1) + " " + listCommands.get(0).get(2) + " " + listCommands.get(0).get(3);
        assertEquals(expected, actual);
    }
}