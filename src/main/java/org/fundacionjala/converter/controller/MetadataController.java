/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller;
import org.fundacionjala.converter.controller.request.RequestExtractMetadataParam;
import org.fundacionjala.converter.controller.request.RequestMetadataParam;
import org.fundacionjala.converter.controller.request.RequestMetadataValidator;
import org.fundacionjala.converter.model.ChecksumMD5;
import org.fundacionjala.converter.model.entity.File;
import org.fundacionjala.converter.model.command.metadata.MetadataExtractor;
import org.fundacionjala.converter.model.command.metadata.MetadataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.fundacionjala.converter.model.service.FileService;
import org.fundacionjala.converter.model.service.FileUploadService;

/**
 * @author Angela Martinez
 * @version 0.1
 */
@RestController
public class MetadataController {
    private MetadataExtractor metadataExtractor = new MetadataExtractor();
    private MetadataParam metadataParam = new MetadataParam();
    private RequestMetadataValidator requestMetadataValidator = new RequestMetadataValidator();
    @Value("${filesMetadataPath}")
    private String targetDir;
    @Value("${metadata.path}")
    private String toolPath;
    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    private FileService fileService;
    /**
     * Receives a request and extracts its metadata
     * @param request requestMetadataParam object
     * @return a message
     */
    @RequestMapping(value = "getMetadata", method = RequestMethod.POST)
    public String getMetadata(final RequestMetadataParam request) {
        if (!requestMetadataValidator.isValid(request)) {
            return "Error: invalid values";
        } else {
            metadataParam.setFilePath(request.getPathFile());
            metadataParam.setFormatExport(metadataParam.getFormatExport(request.getExportFormat()));
            metadataParam.setFormatExportCommand(metadataParam.getFormatExportCommand(request.getExportFormat()));
            metadataParam.setDetail(metadataParam.getDetail(request.getDetail()));
            metadataParam.setTargetDir(targetDir);
            metadataParam.setToolPath(toolPath);
            try {
                return metadataExtractor.extract(metadataParam);
            } catch (Exception e) {
                return e.getMessage();
            }
        }
    }

    /**
     * Receives a request and extracts its metadata
     * @param request requestMetadataParam object
     * @return a message
     */
    @RequestMapping(value = "extractMetadata", method = RequestMethod.POST)
    public String extractMetadata(final RequestExtractMetadataParam request) {
        if (!requestMetadataValidator.isValid(request)) {
            return "Error: invalid values";
        } else {
            String filePath;
            try {
                if (fileService.getFileByMd5(request.getChecksumMD5()) == null) {
                    filePath = fileUploadService.saveInputFile(request.getFile());
                    String check = new ChecksumMD5().getMD5(filePath);
                    if (check.equals(request.getChecksumMD5())) {
                        fileService.saveFile(new File(filePath, check));
                    } else {
                        return "Invalid checksumMD5";
                    }

                } else {
                    filePath = fileService.getFileByMd5(request.getChecksumMD5()).getPath();
                }
                metadataParam.setFilePath(filePath);
                metadataParam.setFormatExport(metadataParam.getFormatExport(request.getExportFormat()));
                metadataParam.setFormatExportCommand(metadataParam.getFormatExportCommand(request.getExportFormat()));
                metadataParam.setDetail(metadataParam.getDetail(request.getDetail()));
                metadataParam.setTargetDir(targetDir);
                metadataParam.setToolPath(toolPath);
                return metadataExtractor.extract(metadataParam);
            } catch (Exception e) {
                return e.getMessage();
            }
        }
    }
}
