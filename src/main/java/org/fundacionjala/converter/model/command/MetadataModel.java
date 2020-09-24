package org.fundacionjala.converter.model.command;

import org.fundacionjala.converter.executor.Executor;
import org.fundacionjala.converter.model.configPath.ConfigPath;
import org.fundacionjala.converter.model.parameter.ModelParameter;
import org.fundacionjala.converter.model.parameter.metadata.MetadataParameter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MetadataModel implements ICommand {
    //static final String REDIRECTION = ">";

    /**
     *
     * @param modelParameter
     * @return A list of commands that will be executed by Executor class
     */
    @Override
    public List<List<String>> createCommand(final ModelParameter modelParameter) {
        MetadataParameter metadataParameter = (MetadataParameter) modelParameter;
        ConfigPath configPath = new ConfigPath();
        List<String> parameterList = new ArrayList<>();
        List<List<String>> finalList = new ArrayList<>();
        System.out.println(configPath.getMetaDataExtractorTool());
        parameterList.add("Metadata");
        parameterList.add(configPath.getMetaDataExtractorTool());
        parameterList.add("-" + metadataParameter.getFormat());
        if (!metadataParameter.getDetail().equals(" ") && !metadataParameter.getDetail().equals("d")) {
            parameterList.add("-" + metadataParameter.getDetail());
        }
        parameterList.add(metadataParameter.getInputFile());
        parameterList.add(metadataParameter.getOutputFile() +"_"+ new Date().getTime() + formatSuffix(metadataParameter.getFormat()));
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

    public static void main(String[] args) throws InterruptedException, IOException, ExecutionException {
        MetadataParameter mp = new MetadataParameter("storage\\inputFiles\\aud.mp3", "t", "d", "storage\\convertedFiles\\meta", "abcabcabc");
        Executor e = new Executor();
        MetadataModel mm = new MetadataModel();
        String result = e.executeCommandsList(mm.createCommand(mp)).toString();
        System.out.println(result);
    }
}
