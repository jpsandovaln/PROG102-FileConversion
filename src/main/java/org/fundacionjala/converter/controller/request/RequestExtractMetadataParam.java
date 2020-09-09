/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller.request;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Angela Martinez
 * @version 0.1
 */
public class RequestExtractMetadataParam {
    private MultipartFile file;
    private String exportFormat;
    private String detail;
    private String checksumMD5;

    /**
     * Sets file
     * @param file
     */
    public void setFile(final MultipartFile file) {
        this.file = file;
    }

    /**
     * Returns path of file
     * @return path
     */
    public MultipartFile getFile() {
            return file;
        }

    /**
     * Returns checksumMD5
     * @return checksumMD5
     */
    public String getChecksumMD5() {
        return checksumMD5;
    }

    /**
     * Sets md5 parameter
     * @param checksumMD5
     */
    public void setChecksumMD5(final String checksumMD5) {
        this.checksumMD5 = checksumMD5;
    }

    /**
     * Sets format to exports metadata
     * @param exportFormat
     */
    public void setExportFormat(final String exportFormat) {
        this.exportFormat = exportFormat;
    }

    /**
     * Set amount of detail of metadata
     * @param detail
     */
    public void setDetail(final String detail) {
        this.detail = detail;
    }

    /**
     * Returns format to export metadata
     * @return format
     */
    public String getExportFormat() {
        return exportFormat;
    }

    /**
     * Returns details
     * @return detail
     */
    public String getDetail() {
        return detail;
    }
}
