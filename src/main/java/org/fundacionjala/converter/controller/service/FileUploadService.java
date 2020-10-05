/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller.service;

import org.fundacionjala.converter.model.configPath.ConfigPath;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @author Julia Escalante
 * @version 0.1
 */
@Service
public class FileUploadService {

    /**
     * save a file in the directory "input files"
     * @param file to save
     * @return
     * @throws IOException
     */
    public String saveInputFile(final MultipartFile file) throws IOException {
        String pathFile = ConfigPath.getInputFile() + file.getOriginalFilename();
        Files.copy(file.getInputStream(), Paths.get(pathFile), StandardCopyOption.REPLACE_EXISTING);
        return pathFile;
    }

    /**
     * save a file in the directory "input files"
     * @param file to save
     * @return
     * @throws IOException
     */
    public String saveTmpFile(final MultipartFile file) throws IOException {
        String pathFile = ConfigPath.getTmpFilePath() + file.getOriginalFilename();
        Files.copy(file.getInputStream(), Paths.get(pathFile), StandardCopyOption.REPLACE_EXISTING);
        return pathFile;
    }
}
