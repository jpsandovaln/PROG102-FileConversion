package org.fundacionjala.converter.model.command;

import org.fundacionjala.converter.model.configPath.ConfigPath;
import org.fundacionjala.converter.model.parameter.ModelParameter;
import org.fundacionjala.converter.model.parameter.metadata.MetadataParameter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MetadataModel implements ICommand {

    /**
     *
     * @param modelParameter
     * @return A list of commands that will be executed by Executor class
     */
    @Override
    public List<List<String>> createCommand(final ModelParameter modelParameter) {
        MetadataParameter metadataParameter = (MetadataParameter) modelParameter;
        List<String> parameterList = new ArrayList<>();
        List<List<String>> finalList = new ArrayList<>();
        System.out.println(ConfigPath.getMetaDataExtractorTool());
        parameterList.add("Metadata");
        parameterList.add(ConfigPath.getMetaDataExtractorTool());
        parameterList.add("-" + metadataParameter.getFormat());
        if (!metadataParameter.getDetail().equals(" ") && !metadataParameter.getDetail().equals("d")) {
            parameterList.add("-" + metadataParameter.getDetail());
        }
        parameterList.add(metadataParameter.getInputFile());
        parameterList.add(metadataParameter.getOutputFile() + "_" + new Date().getTime() + formatSuffix(metadataParameter.getFormat()));
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
