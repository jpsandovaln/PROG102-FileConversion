package org.fundacionjala.converter.model.command.extractText;

import org.fundacionjala.converter.model.parameter.extractText.ExtractTextParameter;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ExtractTextModelTest {
    ExtractTextModel extractTextModel = new ExtractTextModel();
    @Test
    public void createCommandTest() throws IOException {
        ExtractTextParameter parameter = new ExtractTextParameter();
        parameter.setLanguage("spa");
        parameter.setInputFile("storage/inputFiles/imagen.jpg");
        parameter.setOutputFile("storage/inputFiles/");
        parameter.setFileName("demo");
        List<List<String>> expected = new ArrayList<>();
        List<String> listParameters = new ArrayList<>();
        listParameters.add("thirdParty/ocr/tesseract.exe");
        listParameters.add(ExtractTextParameter.LANG_COMMAND);
        listParameters.add(parameter.getLanguage());
        listParameters.add(parameter.getInputFile());
        listParameters.add(parameter.getOutputFile() + parameter.getFileName());
        expected.add(listParameters);
        List<List<String>> result = extractTextModel.createCommand(parameter);
        assertEquals(expected, result);
    }
}