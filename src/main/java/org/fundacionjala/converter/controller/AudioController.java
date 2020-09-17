package org.fundacionjala.converter.controller;

import org.fundacionjala.converter.controller.request.RequestAudioParameter;
import org.fundacionjala.converter.executor.Executor;
import org.fundacionjala.converter.model.ChecksumMD5;
import org.fundacionjala.converter.model.command.AudioModel;
import org.fundacionjala.converter.model.command.ICommand;
import org.fundacionjala.converter.model.entity.File;
import org.fundacionjala.converter.model.parameter.ModelParameter;
import org.fundacionjala.converter.model.parameter.multimedia.AudioParameter;
import org.fundacionjala.converter.model.service.FileService;
import org.fundacionjala.converter.model.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


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

   @RequestMapping(value = "/convertAudio", method = RequestMethod.POST)
   public String audio(final RequestAudioParameter requestAudioParameter) throws Exception {
       System.out.println(requestAudioParameter.isCut());
       if (requestAudioParameter.getFile() == null || requestAudioParameter.getFile().isEmpty()) {
           return "Select a file";
       }
       ChecksumMD5 checksumMD5 = new ChecksumMD5();
       String checksum = "";
       String filePath = fileUploadService.saveInputFile(requestAudioParameter.getFile());
       checksum = checksumMD5.getMD5(filePath);
       if (fileService.getFileByMd5(checksum) == null) {
           fileService.saveFile(new File(filePath, checksum));
           ModelParameter modelParameter = new AudioParameter();
           modelParameter.setInputFile(filePath);
           setAudioParameterValues(modelParameter, requestAudioParameter);
           Executor executor = new Executor();
           ICommand audioModel = new AudioModel();
           List<List<String>> list = audioModel.createCommand(modelParameter);
           executor.executeList(list);

           return "successfully";
       } else {
           ModelParameter modelParameter = new AudioParameter();
           modelParameter.setInputFile(filePath);
           setAudioParameterValues(modelParameter, requestAudioParameter);
           Executor executor = new Executor();
           ICommand audioModel = new AudioModel();
           List<List<String>> list = audioModel.createCommand(modelParameter);
           executor.executeList(list);
           return "the file already exists";
       }
   }

    private void setAudioParameterValues(final ModelParameter modelParameter, final RequestAudioParameter requestAudioParameter) {
        boolean cut = true;
        modelParameter.setOutputFile("storage/convertedFiles");
        ((AudioParameter) modelParameter).setName(requestAudioParameter.getName());
        ((AudioParameter) modelParameter).setFormat(requestAudioParameter.getFormat());
        ((AudioParameter) modelParameter).setCodec(requestAudioParameter.getCodec());
        ((AudioParameter) modelParameter).setBitRate(requestAudioParameter.getBitRate()); // -b:a
        ((AudioParameter) modelParameter).setChannel(requestAudioParameter.getChannel()); // stereo
        ((AudioParameter) modelParameter).setSampleRate(requestAudioParameter.getSampleRate()); //-ar
        ((AudioParameter) modelParameter).setStart(requestAudioParameter.getStart()); //-ss
        ((AudioParameter) modelParameter).setDuration(requestAudioParameter.getDuration()); //-t
        ((AudioParameter) modelParameter).setCut(cut); //
    }
}
