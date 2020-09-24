/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller;

import org.fundacionjala.converter.controller.request.RequestExtractTextParameter;
import org.fundacionjala.converter.controller.service.FileUploadService;
import org.fundacionjala.converter.database.entity.File;
import org.fundacionjala.converter.controller.service.FileService;
import org.fundacionjala.converter.executor.Executor;
import org.fundacionjala.converter.model.ChecksumMD5;
import org.fundacionjala.converter.model.command.extractText.ExtractTextFacade;
import org.fundacionjala.converter.model.parameter.extractText.ExtractTextParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @author Jhordan Soto
 * @version 1.0
 */
@RestController
public class ExtractTextController {
    @Autowired
    private FileService fileService;
    @Value("${tempFiles.path}")
    private String temporal;
    @Autowired
    private FileUploadService fileUploadService;
    /**
     *
     * @param requestExtractTextParameter
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/convertExtractText")
    public String convertExtractText(final RequestExtractTextParameter requestExtractTextParameter) throws Exception {
        //requestExtractTextParameter.validate();
        String result = "exist";
        if (requestExtractTextParameter.getFile() == null || requestExtractTextParameter.getFile().isEmpty()) {
            return "Select a file";
        }
        ChecksumMD5 checksumMD5 = new ChecksumMD5();
        String checksum = "";
        String filePath = fileUploadService.saveInputFile(requestExtractTextParameter.getFile());
        checksum = checksumMD5.getMD5(filePath);
        if (fileService.getFileByMd5(checksum) == null) {
            fileService.saveFile(new File(filePath, checksum));
            result = "saved en data base";
        }
        try {
            ExtractTextFacade extractor = new ExtractTextFacade();
            ExtractTextParameter parameter = new ExtractTextParameter();
            parameter.setInputFile(filePath);
            parameter.setLanguage(requestExtractTextParameter.getLanguage());
            parameter.setFormat(requestExtractTextParameter.getFormat());
            result = extractor.extractText(parameter).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
