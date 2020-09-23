package org.fundacionjala.converter.model.parameter.extractText;

import org.fundacionjala.converter.model.commons.exception.InvalidDataException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ExtractTextParameterTest {
    /*String inputFile = "D:\\AT\\PROG102\\code\\PROG102-FileConversion\\storage\\inputFiles\\image.png";
    String outputFile = "D:\\AT\\PROG102\\code\\PROG102-FileConversion\\storage\\outputFiles\\image-txt";
    String languageSp = "español";
    String languageEn = "english";
    String type = "word";
    String toolPath = "thirdParty/ocr/tesseract.exe";
    ModelParameter eTextParameter = new ExtractTextParameter();
    ExtractTextModel eTextModelSp = new ExtractTextModel();
    CommandBuilder cBuilder = new CommandBuilder();
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
        (eTextParameter).setLanguage(languageSp);
        (eTextParameter).setType(type);
        listCommands = eTextModelSp.createCommand(eTextParameter);
        String actual = listCommands.get(0).get(0) + " " + listCommands.get(0).get(1) + " " + listCommands.get(0).get(2) + " " + listCommands.get(0).get(3);
        assertEquals(expected, actual);
    }*/
/*
    @Test
    public void invalidLanguage() {
        ExtractTextParameter parameter = new ExtractTextParameter();
        parameter.setLanguage("spak");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            parameter.validate();
        });


    }*//*
    @Test(expected = InvalidDataException.class)
    public void emptyLanguage() throws InvalidDataException {
        ExtractTextParameter parameter = new ExtractTextParameter();
        parameter.setLanguage("");
        parameter.validate();
    }
    @Test
    public void nullLanguage() throws InvalidDataException {
        ExtractTextParameter parameter = new ExtractTextParameter();
        parameter.setLanguage(null);
        parameter.validate();
    }
    @Test
    public void emptySpacesLanguage() throws InvalidDataException {
        ExtractTextParameter parameter = new ExtractTextParameter();
        parameter.setLanguage("  ");
        parameter.validate();
    }*/
}