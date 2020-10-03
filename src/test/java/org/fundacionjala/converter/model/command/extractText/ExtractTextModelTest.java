package org.fundacionjala.converter.model.command.extractText;

import org.fundacionjala.converter.model.commons.exception.ModelParameterException;
import org.fundacionjala.converter.model.configPath.ConfigPath;
import org.fundacionjala.converter.model.parameter.extractText.ExtractTextParameter;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;


public class ExtractTextModelTest {
    ExtractTextModel extractTextModel = new ExtractTextModel();

    @Test
    public void createCommandTest() throws IOException, ModelParameterException {
        ExtractTextParameter parameter = new ExtractTextParameter();
        parameter.setLanguage("spa");
        parameter.setInputFile("storage/inputFiles/imagen.jpg");
        parameter.setOutputFile("storage/inputFiles/");
        parameter.setFileName("demo");
        List<List<String>> expected = new ArrayList<>();
        List<String> listParameters = new ArrayList<>();
        listParameters.add(ConfigPath.getExtractTextTool());
        listParameters.add("--tessdata-dir");
        listParameters.add(ConfigPath.getTesstDataDir());
        listParameters.add(ExtractTextParameter.LANG_COMMAND);
        listParameters.add(parameter.getLanguage());
        listParameters.add(parameter.getInputFile());
        listParameters.add(parameter.getOutputFile() + parameter.getFileName());
        expected.add(listParameters);
        List<List<String>> result = extractTextModel.createCommand(parameter);
        Assertions.assertEquals(expected, result);
    }
    @Test
    public void createCommandWithNullParameter() {
        Throwable exception = Assertions.assertThrows(
                ModelParameterException.class,
                () -> {
                    extractTextModel.createCommand(null);
                }
        );
    }
}