/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller;

import org.fundacionjala.converter.controller.request.RequestVideoParameter;
import org.fundacionjala.converter.controller.response.ErrorResponse;
import org.fundacionjala.converter.controller.response.OkResponse;
import org.fundacionjala.converter.executor.Executor;
import org.fundacionjala.converter.model.command.ICommand;
import org.fundacionjala.converter.database.entity.File;
import org.fundacionjala.converter.model.command.multimedia.VideoModel;
import org.fundacionjala.converter.model.commons.exception.ModelParameterException;
import org.fundacionjala.converter.model.parameter.multimedia.VideoParameter;
import org.fundacionjala.converter.controller.service.FileService;
import org.fundacionjala.converter.controller.service.FileUploadService;
import org.fundacionjala.converter.controller.service.FileZipped;
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

@RestController
public class VideoController {
    @Autowired
    private FileService fileService;
    @Value("${convertedFiles.path}")
    private String output;
    @Autowired
    private FileUploadService fileUploadService;

    /**
     * Converts video
     * @param requestVideoParameter - the reference RequestVideoParameter that contains parameters of the file
     * @return ResponseEntity - the reference to OkResponse if file is converted successfully, ErrorResponse otherwise
     */
    @RequestMapping(method = RequestMethod.POST, value = "convertVideo")
    public ResponseEntity convertVideo(final RequestVideoParameter requestVideoParameter) {
        try {
            if (requestVideoParameter.getExportFormat().equals(VideoParameter.MP4)) {
                requestVideoParameter.validate();
            }
            String md5 = requestVideoParameter.getMd5();
            String filePath = "";
            if (fileService.getFileByMd5(md5) == null) {
                filePath = fileUploadService.saveInputFile(requestVideoParameter.getFile());
                fileService.saveFile(new File(filePath, md5));
            } else {
                filePath = fileService.getFileByMd5(md5).getPath();
            }
            VideoParameter videoParameter = new VideoParameter();
            setVideoParameter(videoParameter, requestVideoParameter, filePath);
            String result = FileZipped.zipper(videoParameter, execute(videoParameter));
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
     * Sets videoParameter value
     * @param videoParameter - the reference VideoParameter to set parameters
     * @param requestVideoParameter - the reference RequestVideoParameter that contains parameters of the file
     * @param filePath - the reference String with path of the file
     * @throws IOException
     */
    private void setVideoParameter(final VideoParameter videoParameter,
            final RequestVideoParameter requestVideoParameter, final String filePath) throws IOException {
        videoParameter.setName(requestVideoParameter.getMd5());
        videoParameter.setMd5(requestVideoParameter.getMd5());
        videoParameter.setInputFile(filePath);
        videoParameter.setFrames(requestVideoParameter.getFrames());
        videoParameter.setFormat(requestVideoParameter.getExportFormat());
        videoParameter.setCodec(requestVideoParameter.getCodec());
        videoParameter.setVideoCodec(requestVideoParameter.getVideoCodec());
        videoParameter.setStart(requestVideoParameter.getStart());
        videoParameter.setSecondsToOutput(requestVideoParameter.getSecondsToOutput());
        videoParameter.setControlLoop(requestVideoParameter.getControlLoop());
        videoParameter.setDuration(requestVideoParameter.getDuration());
        videoParameter.setExtractThumbnail(requestVideoParameter.isExtractThumbnail());
        videoParameter.setExtractMetadata(requestVideoParameter.isExtractMetadata());
        videoParameter.setOutputFile(output);
    }

    /**
     * Executes the command list
     * @param parameter - the reference VideoParameter to set parameters
     * @return list - the reference to the list<String> that contains the file paths of converted files
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    private List<String> execute(final VideoParameter parameter)
            throws InterruptedException, ExecutionException, IOException, NoSuchAlgorithmException, ModelParameterException {
        Executor executor = new Executor();
        ICommand videoModel = new VideoModel();
        return executor.executeCommandsList(videoModel.createCommand(parameter));
    }
}
