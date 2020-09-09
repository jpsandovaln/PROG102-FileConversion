package org.fundacionjala.converter.controller;
/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */

import org.fundacionjala.converter.model.ChecksumMD5;
import org.fundacionjala.converter.model.ExtractorModel;
import org.fundacionjala.converter.model.entity.File;
import org.fundacionjala.converter.model.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchAlgorithmException;
/**
 * @author Jhordan Soto
 * @version 1.0
 */
@RestController
public class ExtractTextController {
    @Value("${ocr.path}")
    private String tesseracInstallPath;
    @Value("${inputFiles.path}")
    private String input;
    @Value("${convertedFiles.path}")
    private String converted;
    @Autowired
    private FileService fileService;

    /**
     * extract the text  of the image saving the input and ouput in the dataBase using "SaveFile"
     * @param file the image's text that will be extracted
     * @param language the language that will be the text
     * @param type the type of document that will be transformed
     * @return the the path of the file with the text extracted
     */
    @RequestMapping(method = RequestMethod.POST, value = "/extractText")
    public String extractTextFromImage(final @RequestParam("file") MultipartFile file, final  @RequestParam(required = false) String language,
                                       final @RequestParam(required = false) String type) throws Exception {
        String fileName = file.getOriginalFilename();
        String source = input + fileName;
        String target = converted + fileName;
        Files.copy(file.getInputStream(), Paths.get(source), StandardCopyOption.REPLACE_EXISTING);
        ExtractorModel model = new ExtractorModel();
        String result = model.convertDocument(source, target, language, type, tesseracInstallPath);
        if (type.equals("word") || type.equals("pdf") || type.equals("text")) {
            saveFile(source);
            saveFile(result);
        }

        return result;
    }

    private String generateMD5(final String filePath) {
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
    private void saveFile(final String path) {
        String md5 = generateMD5(path);
        fileService.saveFile(new File(path, md5));
    }
}
