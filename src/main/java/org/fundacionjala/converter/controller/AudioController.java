package org.fundacionjala.converter.controller;

import org.fundacionjala.converter.controller.request.RequestAudioParameter;
import org.fundacionjala.converter.executor.Executor;
import org.fundacionjala.converter.model.ChecksumMD5;
import org.fundacionjala.converter.model.command.AudioModel;
import org.fundacionjala.converter.model.command.ICommand;
import org.fundacionjala.converter.model.entity.File;
import org.fundacionjala.converter.model.parameter.multimedia.AudioParameter;
import org.fundacionjala.converter.model.service.FileService;
import org.fundacionjala.converter.model.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

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
    @Autowired
    private FileUploadService fileUploadService;

    /**
     * Controller of audio
     * @param requestAudioParameter
     * @return
     */
   /** @RequestMapping(value = "/convertAudio", method = RequestMethod.POST)
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
    }**/

   @PostMapping("/audiosss")
   public String audio(RequestAudioParameter requestAudioParameter) throws Exception {
       if (requestAudioParameter.getFile() == null || requestAudioParameter.getFile().isEmpty()) {
           return "Select a file";
       }
       ChecksumMD5 checksumMD5 = new ChecksumMD5();
       String checksum = "";
       String filePath = fileUploadService.saveInputFile(requestAudioParameter.getFile());
       checksum = checksumMD5.getMD5(filePath);
       if (fileService.getFileByMd5(checksum) == null) {
           fileService.saveFile(new File(filePath, checksum));
           AudioParameter audioParameter = new AudioParameter();
           audioParameter.setInputFile(filePath);
           audioParameter.setOutputFile("storage/convertedFiles");
           audioParameter.setName(requestAudioParameter.getName());
           audioParameter.setFormat(requestAudioParameter.getFormat());
           audioParameter.setCodec(requestAudioParameter.getCodec());
           audioParameter.setBitRate(requestAudioParameter.getBitRate());
           audioParameter.setChannel(requestAudioParameter.getChannel());
           audioParameter.setSampleRate(requestAudioParameter.getSampleRate());
           audioParameter.setStart(requestAudioParameter.getStart());
           audioParameter.setDuration(requestAudioParameter.getDuration());
           audioParameter.setCut(requestAudioParameter.isCut());
           Executor executor = new Executor();
           ICommand audioModel = new AudioModel();
           executor.executeList(audioModel.createCommand(audioParameter));

           return "successfully";
       } else {
           AudioParameter audioParameter = new AudioParameter();
           audioParameter.setInputFile(filePath);
           audioParameter.setOutputFile("storage/convertedFiles");
           audioParameter.setName(requestAudioParameter.getName());
           audioParameter.setFormat(requestAudioParameter.getFormat());
           audioParameter.setCodec(requestAudioParameter.getCodec());
           audioParameter.setBitRate(requestAudioParameter.getBitRate());
           audioParameter.setChannel(requestAudioParameter.getChannel());
           audioParameter.setSampleRate(requestAudioParameter.getSampleRate());
           audioParameter.setStart(requestAudioParameter.getStart());
           audioParameter.setDuration(requestAudioParameter.getDuration());
           audioParameter.setCut(requestAudioParameter.isCut());
           Executor executor = new Executor();
           ICommand audioModel = new AudioModel();
           executor.executeList(audioModel.createCommand(audioParameter));
           return "the file already exists";
       }
   }
}
