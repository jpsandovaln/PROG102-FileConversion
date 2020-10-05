/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller;

import org.fundacionjala.converter.controller.request.RequestAudioParameter;
import org.fundacionjala.converter.controller.response.ErrorResponse;
import org.fundacionjala.converter.controller.response.OkResponse;
import org.fundacionjala.converter.controller.service.FileZipped;
import org.fundacionjala.converter.executor.Executor;
import org.fundacionjala.converter.model.command.multimedia.AudioModel;
import org.fundacionjala.converter.model.commons.exception.ModelParameterException;
import org.fundacionjala.converter.model.command.ICommand;
import org.fundacionjala.converter.database.entity.File;
import org.fundacionjala.converter.model.parameter.multimedia.AudioParameter;
import org.fundacionjala.converter.controller.service.FileService;
import org.fundacionjala.converter.controller.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Mirko Romay
 * @version 0.1
 */
@RestController
public class AudioController {

    @Autowired
    private FileService fileService;
    @Value("${convertedFiles.path}")
    private String output;
    @Autowired
    private FileUploadService fileUploadService;

    /**
     * Converts audio
     * @param requestAudioParameter - the reference RequestAudioParameter that contains parameters of the file
     * @return ResponseEntity - the reference to OkResponse if file is converted successfully, ErrorResponse otherwise
     */
    @RequestMapping(value = "convertAudio", method = RequestMethod.POST)
    public ResponseEntity audio(final RequestAudioParameter requestAudioParameter) {
        try {
            requestAudioParameter.validate();
            String md5 = requestAudioParameter.getMd5();
            String filePath = "";

            if (fileService.getFileByMd5(md5) == null) {
                filePath = fileUploadService.saveInputFile(requestAudioParameter.getFile());
                fileService.saveFile(new File(filePath, md5));
            } else {
                filePath = fileService.getFileByMd5(md5).getPath();
            }
            AudioParameter audioParameter = new AudioParameter();
            setAudioParameterValues(audioParameter, requestAudioParameter, filePath);
            String result = FileZipped.zipper(audioParameter, execute(audioParameter));
            return ResponseEntity.ok().body(new OkResponse<Integer>(HttpServletResponse.SC_OK, result));
        } catch (IOException | InterruptedException | ExecutionException e) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse<Integer>(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse<String>(Integer.toString(HttpServletResponse.SC_BAD_REQUEST), e.getMessage()));
        }
    }

    /**
     * Sets audioParameter value
     * @param audioParameter - the reference VideoParameter to set parameters
     * @param requestAudioParameter - the reference RequestVideoParameter that contains parameters of the file
     * @param filePath - the reference String with path of the file
     * @throws IOException
     */
    private void setAudioParameterValues(final AudioParameter audioParameter,
            final RequestAudioParameter requestAudioParameter, final String filePath) throws IOException {
        audioParameter.setInputFile(filePath);
        audioParameter.setOutputFile(output);
        audioParameter.setMd5(requestAudioParameter.generateMD5(filePath));
        audioParameter.setName(requestAudioParameter.getName());
        audioParameter.setFormat(requestAudioParameter.getExportFormat());
        audioParameter.setCodec(requestAudioParameter.getCodec());
        audioParameter.setBitRate(requestAudioParameter.getBitRate()); // -b:a
        audioParameter.setChannel(requestAudioParameter.getChannel()); // stereo
        audioParameter.setSampleRate(requestAudioParameter.getSampleRate()); //-ar
        audioParameter.setStart(requestAudioParameter.getStart()); //-ss
        audioParameter.setDuration(requestAudioParameter.getDuration()); //-t
        audioParameter.setSecondsToOutput(requestAudioParameter.getSecondsToOutput());
        audioParameter.setCut(requestAudioParameter.isCut());
        audioParameter.setExtractMetadata(requestAudioParameter.isExtractMetadata());
    }

    /**
     * Executes the command list
     * @param audioParameter - the reference AudioParameter to set parameters
     * @return list - the reference to the list<String> that contains the file paths of converted files
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    private List<String> execute(final AudioParameter audioParameter) throws ModelParameterException,
            InterruptedException, ExecutionException, IOException, NoSuchAlgorithmException {
        Executor executor = new Executor();
        ICommand audioModel = new AudioModel();
        return executor.executeCommandsList(audioModel.createCommand(audioParameter));
    }
}
