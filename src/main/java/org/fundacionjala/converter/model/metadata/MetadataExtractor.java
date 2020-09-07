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
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Angela Martinez
 * @version 0.1
 */
@Service
public class MetadataExtractor implements IExtractor {
    private Process process;
    private List<String> listParams = new ArrayList<String>();
    private CommandBuilder commandBuilder = new CommandBuilder();

    public List<String> getListParams(final Param param) {
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
    public String extract(final Param param) {
        String sSistemaOperativo = System.getProperty("os.name");
        return commandBuilder.execute(getListParams(param));
    }
}

