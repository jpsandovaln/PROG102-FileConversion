package org.fundacionjala.converter.controller;

import org.fundacionjala.converter.controller.request.RequestAudioParameter;
import org.fundacionjala.converter.model.entity.File;
import org.fundacionjala.converter.model.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @author Mirko Romay
 * @version 0.1
 */
@RestController
public class AudioController {

    @Autowired
    private FileService fileService;
    @Value("${tempFiles.path}")
    private String temporal;

    /**
     * Controller of audio
     * @param requestAudioParameter
     * @return
     */
    @RequestMapping(value = "/convertAudio", method = RequestMethod.POST)
    public String convertAudio(final RequestAudioParameter requestAudioParameter) throws Exception {
        requestAudioParameter.validate();
        String result = "exist";
        String path = temporal + requestAudioParameter.getFile().getOriginalFilename();
        Files.copy(requestAudioParameter.getFile().getInputStream(), Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
        String md5 = requestAudioParameter.generateMD5(path);
        if (!requestAudioParameter.isInDataBase(md5, fileService)) {
            fileService.saveFile(new File(path, md5));
            result = "saved in database";
        }
        Files.delete(Paths.get(path));
        return result;
    }
}
