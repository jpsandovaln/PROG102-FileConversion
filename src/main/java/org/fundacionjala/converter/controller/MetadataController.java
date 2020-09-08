/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller;
import org.fundacionjala.converter.controller.request.RequestMetadataParam;
import org.fundacionjala.converter.controller.request.RequestMetadataValidator;
import org.fundacionjala.converter.model.metadata.MetadataExtractor;
import org.fundacionjala.converter.model.metadata.MetadataParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Angela Martinez
 * @version 0.1
 */
@RestController
public class MetadataController {
    private MetadataExtractor metadataExtractor = new MetadataExtractor();
    private MetadataParam metadataParam = new MetadataParam();
    private RequestMetadataValidator requestMetadataValidator= new RequestMetadataValidator();
    @Value("${filesMetadataPath}")
    private String targetDir;
    @Value("${metadata.path}")
    private String toolPath;

    /**
     * Receives a request and extracts its metadata
     * @param request requestMetadataParam object
     * @return a message
     */
    @RequestMapping(value = "extractMetadata", method = RequestMethod.POST)
    public String getMetadata(final RequestMetadataParam request) {
        //if (request.getPathFile().isEmpty() || request.getExportFormat().isEmpty()) {
        //    return "The fields cannot be empty";
        if (!requestMetadataValidator.isValid(request)) {
            return "Error";
        } else {
            metadataParam.setFilePath(request.getPathFile());
            metadataParam.setFormatExport(metadataParam.getFormatExport(request.getExportFormat()));
            metadataParam.setFormatExportCommand(metadataParam.getFormatExportCommand(request.getExportFormat()));
            metadataParam.setDetail(metadataParam.getDetail(request.getDetail()));
            metadataParam.setTargetDir(targetDir);
            metadataParam.setToolPath(toolPath);
            try {
                metadataExtractor.extract(metadataParam);
            } catch (Exception e) {
                return e.getMessage();
            }
        }
        return "Extraction successful";
    }
}
