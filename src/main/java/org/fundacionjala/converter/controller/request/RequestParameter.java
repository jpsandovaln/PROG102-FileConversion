/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller.request;

import org.fundacionjala.converter.model.ChecksumMD5;
import org.fundacionjala.converter.database.entity.File;
import org.fundacionjala.converter.controller.service.FileService;
import org.fundacionjala.converter.controller.service.FileUploadService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public abstract class RequestParameter {

    private MultipartFile file;
    private String exportFormat;
    private String md5;
    /**
     *
     * @param file
     */
    public void setFile(final MultipartFile file) {
        this.file = file;
    }

    /**
     *
     * @param exportFormat
     */
    public void setExportFormat(final String exportFormat) {
        this.exportFormat = exportFormat;
    }

    /**
     * @return
     */

    public String getExportFormat() {
        return exportFormat;
    }

    /**
     * @return
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     *
     * @param exportFormat
     */
    public void setMd5(final String md5) {
        this.md5 = md5;
    }

    /**
     * @return
     */

    public String getMd5() {
        return md5;
    }

    /**
     * @param filePath
     * @return
     */
    public String generateMD5(final String filePath) {
        try {
            ChecksumMD5 checksumMD5 = new ChecksumMD5();
            String checksum = checksumMD5.getMD5(filePath);
            return checksum;
        } catch (NoSuchAlgorithmException | IOException e) {
            e.getMessage();
            return "Could not get MD5 from input file.";
        }
    }

    /**
     *
     * @throws Exception
     */
    public void validate() throws Exception {

        if (this.getExportFormat() == null || "".equals(this.getExportFormat())) {
            throw new Exception("Failed format empty");
        }

        if (this.getFile().getOriginalFilename().contains("..") || this.getFile() == null) {
            throw new Exception("Failed file null");
        }

        if (!this.generateMD5(new FileUploadService().saveTmpFile(file)).equals(md5)) {
            throw new Exception("Faild in the md5");
        }
        if (this.getMd5() == null || "".equals(this.getMd5())) {
            throw new Exception("failed md5 null");
        }
    }

    /**
     *
     * @param md5ToCompare
     * @param fileService
     * @return
     */
    public boolean isInDataBase(final String md5ToCompare, final FileService fileService) {
        File temp = fileService.getFileByMd5(md5ToCompare);
        if (temp != null) {
            return true;
        }
        return false;
    }
}

