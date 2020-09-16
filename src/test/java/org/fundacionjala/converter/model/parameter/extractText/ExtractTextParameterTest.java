package org.fundacionjala.converter.model.parameter.extractText;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.fundacionjala.converter.model.command.CommandBuilder;
import org.fundacionjala.converter.model.command.ExtractTextModel;
import org.fundacionjala.converter.model.parameter.ModelParameter;

public class ExtractTextParameterTest {
    String iFile = "D:\\AT\\PROG102\\code\\PROG102-FileConversion\\storage\\inputFiles\\image.png";
    String oFile = "D:\\AT\\PROG102\\code\\PROG102-FileConversion\\storage\\outputFiles\\image-txt";
    String languageSp = "español";
    String languageEn = "english";
    String type = "word";
    String toolPath = "d:/AT/PROG102/code/PROG102-FileConversion/thirdParty/ocr/tesseract.exe";
    ModelParameter eTextParameter = new ExtractTextParameter(iFile, oFile, "md5");
    ExtractTextModel eTextModelSp = new ExtractTextModel (languageSp, type, eTextParameter);
    CommandBuilder cBuilder = new CommandBuilder();
    List<List<String>> listCommands = new ArrayList<>();

    @Test
    public void selectLanguageSpanish() {
        String expected = "-l spa";
        String actual = eTextModelSp.selectLanguage(languageSp);
        assertEquals (expected, actual);
    }
    @Test
    public void selectTypeWord() {
        String expected = ".docx";
        String actual = eTextModelSp.selectExtension(type);
        assertEquals (expected, actual);
    }
    @Test
    public void createExTextModelWithInitialParameters() {
        String expected = "ExtractTextModel [eTextParameter=" + eTextParameter + ", language=" + languageSp + ", type=" + type + "]";
        String actual = eTextModelSp.toString();
        assertEquals(expected, actual);
    }
    @Test
    public void createCommandWhenLanguageisSpAndDocumentIsWord(){
        String expected = toolPath + " " + iFile + " " + oFile + " " + "-l spa";
        listCommands = eTextModelSp.createCommand(eTextParameter);
        String actual = listCommands.get(0).get(0) + " " + listCommands.get(0).get(1) + " " + listCommands.get(0).get(2) + " " + listCommands.get(0).get(3);
        assertEquals(expected, actual);
    }
}