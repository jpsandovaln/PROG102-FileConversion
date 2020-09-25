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
import org.fundacionjala.converter.model.command.VideoModel;
import org.fundacionjala.converter.database.entity.File;
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
     *
     * @param requestVideoParameter
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "convertVideo")
    public ResponseEntity convertVideo(final RequestVideoParameter requestVideoParameter) {
        try {
            requestVideoParameter.validate();
            String filePath = fileUploadService.saveInputFile(requestVideoParameter.getFile());
            String md5 = requestVideoParameter.generateMD5(filePath);
            if (fileService.getFileByMd5(md5) == null) {
                fileService.saveFile(new File(filePath, md5));
            }

            VideoParameter videoParameter = new VideoParameter();
            videoParameter.setInputFile(filePath);
            videoParameter.setFrames(requestVideoParameter.getFrames());
            videoParameter.setExtension(requestVideoParameter.getExportFormat());
            videoParameter.setAudioCodec(requestVideoParameter.getAudioCodec());
            videoParameter.setVideoCodec(requestVideoParameter.getVideoCodec());
            videoParameter.setExtractThumbnail(requestVideoParameter.getExtractThumbnail());
            videoParameter.setExtractMetadata(requestVideoParameter.isExtractMetadata());
            videoParameter.setOutputFile(output);
            Executor executor = new Executor();
            VideoModel video = new VideoModel();
            System.out.println(video.createCommand(videoParameter));
            String result = FileZipped.zipper(videoParameter,
                    executor.executeCommandsList(video.createCommand(videoParameter)));
            return ResponseEntity.ok().body(
                    new OkResponse<Integer>(HttpServletResponse.SC_OK, result));
        } catch (IOException | InterruptedException | ExecutionException e) {
              return ResponseEntity.badRequest()
                      .body(new ErrorResponse<Integer>(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()));
        } catch (Exception e) {
              return ResponseEntity.badRequest().body(
                      new ErrorResponse<String>(Integer.toString(HttpServletResponse.SC_BAD_REQUEST), e.getMessage()));
    }
  }
}
