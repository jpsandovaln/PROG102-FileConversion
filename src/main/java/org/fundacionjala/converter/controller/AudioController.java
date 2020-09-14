package org.fundacionjala.converter.controller;

import org.fundacionjala.converter.controller.request.RequestAudioParameter;
import org.fundacionjala.converter.model.entity.File;
import org.fundacionjala.converter.model.service.FileService;
//import org.fundacionjala.converter.params.AudioParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "convertAudio")
    public String convertAudio(final RequestAudioParameter requestAudioParameter) throws IOException {

        //AudioParameter audioParameter;
        String result = "Error";
       // Executor exec;
        String path = temporal + requestAudioParameter.getFile().getOriginalFilename();
        Files.copy(requestAudioParameter.getFile().getInputStream(), Paths.get(path));
        if (requestAudioParameter.validate()) {
            fileService.saveFile(new File(path, requestAudioParameter.generateMD5(path)));
            //audioParameter = new AudioParameter();
       //     result = exec.executer(audioParameter);
        }
        Files.delete(Paths.get(path));
        return result;
    }
}
