package org.fundacionjala.converter.model.command;

import org.fundacionjala.converter.model.commons.exception.InvalidDataException;
import org.fundacionjala.converter.model.configPath.ConfigPath;
import org.fundacionjala.converter.model.parameter.ModelParameter;
import org.fundacionjala.converter.model.parameter.metadata.MetadataParameter;

import java.util.*;

public class MetadataModel implements ICommand {
    private static final String DATA = "Metadata";
    private static final String DEFAULT_VAL = "d";
    private static final String MINUS = "-";
    private static final String LOW_BAR = "_";
    private static final String JSON = ".json";
    private static final String HTML = ".html";
    private static final String TXT = ".txt";
    private static final String CSV = ".csv";
    private static final String XMP = ".XMP";
    private static final Map<String, String> map = new HashMap<String, String>();

    public MetadataModel() {
        map.put("j", JSON);
        map.put("h", HTML);
        map.put("t", TXT);
        map.put("T", CSV);
        map.put("xmp", XMP);

    }

    /**
     *
     * @param modelParameter
     * @return A list of commands that will be executed by Executor class
     */
    @Override
    public List<List<String>> createCommand(final ModelParameter modelParameter) {
        MetadataParameter metadataParameter = (MetadataParameter) modelParameter;
        //metadataParameter.validate();
        List<String> parameterList = new ArrayList<>();
        List<List<String>> finalList = new ArrayList<>();
        parameterList.add(DATA);
        parameterList.add(ConfigPath.getMetaDataExtractorTool());
        parameterList.add(MINUS + metadataParameter.getFormat());
        if (!metadataParameter.getDetail().equals(DEFAULT_VAL)) {
            parameterList.add(MINUS + metadataParameter.getDetail());
        }
        parameterList.add(metadataParameter.getInputFile());
        parameterList.add(metadataParameter.getOutputFile()+ LOW_BAR + DATA + map.get(metadataParameter.getFormat()));
        finalList.add(parameterList);
        return finalList;
    }
}
