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
import org.fundacionjala.converter.model.entity.File;
import org.fundacionjala.converter.model.service.FileService;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public abstract class  RequestParameter {

    private MultipartFile file;
    private String format;

    public RequestParameter(final MultipartFile file, final String format) {
        this.file = file;
        this.format = format;
    }

    /**
     *
     * @param file
     */
    public void setFile(final MultipartFile file) {
        this.file = file;
    }

    /**
     *
     * @param format
     */
    public void setFormat(final String format) {
        this.format = format;
    }

    /**
     * @return
     */

    public String getFormat() {
        return format;
    }

    /**
     * @return
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param filePath
     * @return
     */
    public String generateMD5(final String filePath) {
        try {
            String checksum = "";
            ChecksumMD5 checksumMD5 = new ChecksumMD5();
            checksum = checksumMD5.getMD5(filePath);
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

        if (this.getFormat() == null || "".equals(this.getFormat())) {
            throw new Exception("failed format empty");
        }

        if (this.getFile().getOriginalFilename().contains("..") || this.getFile() == null) {
            throw new Exception("failed file null");
        }

        if (this.getFile().getOriginalFilename().contains("..") || this.getFile() == null) {
            throw new Exception("failed file null");
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

