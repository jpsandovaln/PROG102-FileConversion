package org.fundacionjala.converter.model.command;

import org.fundacionjala.converter.model.configPath.ConfigPath;
import org.fundacionjala.converter.model.parameter.ModelParameter;
import org.fundacionjala.converter.model.parameter.metadata.MetadataParameter;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

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
    private static final String INDEX_JSON = "j";
    private static final String INDEX_HTML = "h";
    private static final String INDEX_TXT = "t";
    private static final String INDEX_CSV = "T";
    private static final String INDEX_XMP = "xmp";
    private Map<String, String> map = new HashMap<String, String>();

    public MetadataModel() {
        map.put(INDEX_JSON, JSON);
        map.put(INDEX_HTML, HTML);
        map.put(INDEX_TXT, TXT);
        map.put(INDEX_CSV, CSV);
        map.put(INDEX_XMP, XMP);
    }

    /**
     * Create command
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
        parameterList.add(metadataParameter.getOutputFile() + LOW_BAR + DATA + map.get(metadataParameter.getFormat()));
        finalList.add(parameterList);
        return finalList;
    }
}
