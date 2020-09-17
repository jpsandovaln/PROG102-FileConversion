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


import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
    @Value("${convertedFiles.path}")
    private String output;
    @Autowired
    private FileUploadService fileUploadService;

    /**
     *
     * @param requestAudioParameter
     * @return
     * @throws Exception
     */

   @RequestMapping(value = "/convertAudio", method = RequestMethod.POST)
   public String audio(final RequestAudioParameter requestAudioParameter) throws Exception {
       if (requestAudioParameter.getFile() == null || requestAudioParameter.getFile().isEmpty()) {
           return "Select a file";
       }
       ChecksumMD5 checksumMD5 = new ChecksumMD5();
       String checksum = "";
       String filePath = fileUploadService.saveInputFile(requestAudioParameter.getFile());
       checksum = checksumMD5.getMD5(filePath);
       if (fileService.getFileByMd5(checksum) == null) {
           fileService.saveFile(new File(filePath, checksum));

       }
       ModelParameter modelParameter = new AudioParameter();
       setAudioParameterValues(modelParameter, requestAudioParameter, filePath);
       execute(modelParameter);
       return "successfully";
   }

    /**
     *
     * @param modelParameter
     * @param requestAudioParameter
     * @param filePath
     * @throws IOException
     */
    private void setAudioParameterValues(final ModelParameter modelParameter, final RequestAudioParameter requestAudioParameter, final String filePath) throws IOException {
        boolean cut = true;
        modelParameter.setInputFile(filePath);
        modelParameter.setOutputFile(output);
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

    /**
     *
     * @param modelParameter
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    private void execute(final ModelParameter modelParameter) throws InterruptedException, ExecutionException, IOException {
        Executor executor = new Executor();
        ICommand audioModel = new AudioModel();
        List<List<String>> list = audioModel.createCommand(modelParameter);
        executor.executeCommandsList(list);
    }
}
