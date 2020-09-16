package org.fundacionjala.converter.model.command;

import org.fundacionjala.converter.model.configPath.ConfigPath;
import org.fundacionjala.converter.model.parameter.ModelParameter;
import org.fundacionjala.converter.model.parameter.metadata.MetadataParameter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MetadataModel implements ICommand {
    static final String REDIRECTION = ">";

    /**
     *
     * @param modelParameter
     * @return A list of commands that will be executed by Executor class
     */
    @Override
    public List<List<String>> createCommand(final ModelParameter modelParameter) {
        MetadataParameter metadataParameter = (MetadataParameter) modelParameter;
        ConfigPath configPath = new ConfigPath("");
        List<String> parameterList = new ArrayList<>();
        String projectPath = System.getProperty("user.dir");
        /*parameterList.add("cmd"); //You have to uncomment these lines for execute command in WINDOWS
        parameterList.add("/c");*/
        parameterList.add(new File(configPath.getMetaDataExtractorTool()).getAbsolutePath());
        if (!" ".equals(metadataParameter.getFormat())) {
            parameterList.add("-" + metadataParameter.getFormat());
        }
        parameterList.add("-" + metadataParameter.getDetail());
        parameterList.add(projectPath + "\\" + metadataParameter.getInputFile());
        parameterList.add(REDIRECTION);
        parameterList.add(new File(configPath.getConvertedFilesPath()).getAbsolutePath() + "\\" + metadataParameter.getOutputFile() + formatSuffix(metadataParameter.getFormat()));
        List<List<String>> finalList = new ArrayList<>();
        finalList.add(parameterList);
        return finalList;
    }

    private String formatSuffix(final String format) {
        switch (format) {
            case "j":
                return ".json";
            case "h":
                return ".html";
            case "t":
                return ".txt";
            case "T":
                return ".csv";
            default:
                return ".XMP";
        }
    }
}
