/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.model.service;

import org.springframework.beans.factory.annotation.Value;
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

    @Value("${inputFiles.path}")
    private String inputFiles;

    /**
     * save a file in the directory "input files"
     * @param file to save
     * @return
     * @throws IOException
     */
    public String saveInputFile(final MultipartFile file) {
        String pathFile = inputFiles + file.getOriginalFilename();
        try {
            Files.copy(file.getInputStream(), Paths.get(pathFile), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pathFile;
    }
}
