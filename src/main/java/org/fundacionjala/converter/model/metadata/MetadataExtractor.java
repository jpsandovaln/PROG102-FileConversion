/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.model.metadata;

import org.fundacionjala.converter.model.CommandBuilder;
import org.fundacionjala.converter.model.IExtractor;
import org.fundacionjala.converter.model.Param;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Angela Martinez
 * @version 0.1
 */
public class MetadataExtractor implements IExtractor {
    private Process process;
    private List<String> listParams;
    private CommandBuilder commandBuilder;

    public MetadataExtractor() {
        listParams = new ArrayList<String>();
        commandBuilder = new CommandBuilder();
    }

    /**
     * Returns a list with the parameters
     * @param param object with the parameters
     * @return List<String>
     */
    public List<String> getListParams(final Param param) {
        listParams.clear();
        MetadataParam metadataParam = (MetadataParam) param;
        listParams.add(metadataParam.getToolPath());
        listParams.add(metadataParam.getFormatExportCommand());
        listParams.add(metadataParam.getDetail());
        listParams.add(metadataParam.getFilePath());
        listParams.add(MetadataParam.REDIRECTION);
        listParams.add(metadataParam.getTargetDir());
        listParams.add(metadataParam.getFileName());
        listParams.add(metadataParam.getFormatExport());
        return listParams;
    }

    /**
     * Extract file's metadata
     * @param param
     * @throws Exception
     */
    public void extract(final Param param) throws Exception {
        commandBuilder.execute(getListParams(param));
    }
}

