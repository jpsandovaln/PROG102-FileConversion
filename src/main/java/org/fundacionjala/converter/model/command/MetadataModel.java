package org.fundacionjala.converter.model.command;

import org.fundacionjala.converter.model.configPath.ConfigPath;
import org.fundacionjala.converter.model.parameter.ModelParameter;
import org.fundacionjala.converter.model.parameter.metadata.MetadataParameter;

import java.util.*;

public class MetadataModel implements ICommand {
    private static final String DATA = "Metadata";
    private static final String DEFAULT_VAL = "d";
    private static final String MINUS = "-";
    private static final String LOW_BAR = "_";
    private static final Map<String, String> map = new HashMap<String, String>();

    public MetadataModel() {
        map.put("j",".json");
        map.put("h",".html");
        map.put("t",".txt");
        map.put("T",".csv");
        map.put("xmp",".XMP");

    }

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
        parameterList.add(DATA);
        parameterList.add(ConfigPath.getMetaDataExtractorTool());
        parameterList.add("-" + metadataParameter.getFormat());
        if (!metadataParameter.getDetail().equals(" ") && !metadataParameter.getDetail().equals(DEFAULT_VAL)) {
            parameterList.add("-" + metadataParameter.getDetail());
        }
        parameterList.add(metadataParameter.getInputFile());
        parameterList.add(metadataParameter.getMd5() + "_" + DATA + map.get(metadataParameter.getFormat()));
        finalList.add(parameterList);
        return finalList;
    }
//
//    private String formatSuffix(final String format) {
//        switch (format) {
//            case "j":
//                return JSON;
//            case "h":
//                return HTML;
//            case "t":
//                return TXT;
//            case "T":
//                return CSV;
//            default:
//                return XMP;
//        }
//    }
}
