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
    private static final String COMMAND = " -v error -show_entries format=duration -of default=noprint_wrappers=1:nokey=1 -sexagesimal ";
    @Autowired
    private FileUploadService fileUploadService;
    @Value("${duration.path}")
    private String toolPath;

    /**
     *
     * @param file
     * @return duration of the video input file
     */
    @RequestMapping(method = RequestMethod.POST, value = "calculateDuration")
    public String calculateDuration(final MultipartFile file) {
        try {
            File tool = new File(toolPath);
            toolPath = tool.getAbsolutePath();
            String filePath = fileUploadService.saveInputFile(file);
            File realFile = new File(filePath);
            filePath = realFile.getAbsolutePath();
            String completeCommand = toolPath + COMMAND + "\"" + filePath + "\"";
            Executor exec = new Executor();
            String result = exec.executeSingleStringCommand(completeCommand);
            result = result.substring(0, result.indexOf("."));
            return result;
        } catch (IOException | InterruptedException e) {
            return e.getMessage();
        }
    }
}
