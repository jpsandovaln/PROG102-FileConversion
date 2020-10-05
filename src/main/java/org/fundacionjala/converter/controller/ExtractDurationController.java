/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller;

import org.fundacionjala.converter.controller.service.FileUploadService;
import org.fundacionjala.converter.executor.Executor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class ExtractDurationController {
    private static final String COMMAND = " -v error -show_entries format=duration -of default=noprint_wrappers=1:nokey=1 -sexagesimal ", DOUBLE_QUOTES = "\"";
    @Autowired
    private FileUploadService fileUploadService;
    @Value("${duration.path}")
    private String toolPath;

    /**
     * Calculates duration of a file
     * @param file - the reference MultipartFile of the file
     * @return duration - the reference String with path of the file
     */
    @RequestMapping(method = RequestMethod.POST, value = "calculateDuration")
    public String calculateDuration(final MultipartFile file) {
        try {
            File tool = new File(toolPath);
            toolPath = tool.getAbsolutePath();
            String filePath = fileUploadService.saveInputFile(file);
            File realFile = new File(filePath);
            filePath = realFile.getAbsolutePath();
            String completeCommand = toolPath + COMMAND + DOUBLE_QUOTES + filePath + DOUBLE_QUOTES;
            Executor exec = new Executor();
            String result = exec.executeSingleStringCommand(completeCommand);
            result = result.substring(0, result.indexOf("."));
            return result;
        } catch (IOException | InterruptedException e) {
            return e.getMessage();
        }
    }
}
