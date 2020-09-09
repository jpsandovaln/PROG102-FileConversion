/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller.request;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Angela Martinez
 * @version 0.1
 */
public class RequestMetadataValidator {
    private List<String> validTypes;
    private List<String> validExportFormat;
    private List<String> validDetailFormat;

    public RequestMetadataValidator() {
        validTypes = new ArrayList<String>();
        validTypes.add("mp4");
        validTypes.add("MP4");
        validTypes.add("avi");
        validTypes.add("AVI");
        validTypes.add("jpeg");
        validTypes.add("JPEG");
        validTypes.add("png");
        validTypes.add("PNG");
        validTypes.add("jpg");
        validTypes.add("JPG");
        validTypes.add("wav");
        validTypes.add("ogg");
        validTypes.add("mkv");
        validExportFormat = new ArrayList<String>();
        validExportFormat.add("j");
        validExportFormat.add("t");
        validExportFormat.add("T");
        validExportFormat.add("h");
        validExportFormat.add("x");
        validDetailFormat = new ArrayList<String>();
        validDetailFormat.add("c");
        validDetailFormat.add("v");
        validDetailFormat.add("d");
    }

    /**
     * Returns if the RequestMetadataParam's fields are valid
     * @param param
     * @return
     */
    public boolean isValid(final RequestMetadataParam param) {
        return isValidPathFile(param.getPathFile()) && isValidExportFormat(param.getExportFormat()) && isValidDetail(param.getDetail());
    }

    /**
     * Returns if the RequestExtractMetadataParam's fields are valid
     * @param param
     * @return
     */
    public boolean isValid(final RequestExtractMetadataParam param) {
        return isValidFile(param.getFile()) && isValidExportFormat(param.getExportFormat()) && isValidDetail(param.getDetail());
    }

    /**
     * Returns if the file is valid
     * @param file
     * @return
     */
    public boolean isValidFile(final MultipartFile file) {
        return (!file.isEmpty() && !file.equals(null));
    }

    /**
     * Returns if the filePath is valid
     * @param filePath
     * @return
     */
    public boolean isValidPathFile(final String filePath) {
        String extFile = FilenameUtils.getExtension(filePath);
        return validTypes.contains(extFile);
    }

    /**
     * Returns if the export Format is valid
     * @param exportFormat
     * @return
     */
    public boolean isValidExportFormat(final String exportFormat) {
        return validExportFormat.contains(exportFormat);
    }

    /**
     * Returns if the detail is valid
     * @param detail
     * @return
     */
    public boolean isValidDetail(final String detail) {
        return validDetailFormat.contains(detail);
    }
}
