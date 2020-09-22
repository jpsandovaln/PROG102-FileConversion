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
import org.fundacionjala.converter.executor.Executor;
import org.fundacionjala.converter.model.ChecksumMD5;
import org.fundacionjala.converter.model.command.VideoModel;
import org.fundacionjala.converter.model.entity.File;
import org.fundacionjala.converter.model.parameter.ModelParameter;
import org.fundacionjala.converter.model.parameter.multimedia.VideoParameter;
import org.fundacionjala.converter.model.service.FileService;
import org.fundacionjala.converter.model.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoController {

 @Autowired
 private FileService fileService;
  @Autowired
  private FileUploadService fileUploadService;

  /**
   *
   * @param requestVideoParameter
   * @return
   */
  @RequestMapping(method = RequestMethod.POST, value = "convertVideo")
  public String convertVideo(final RequestVideoParameter requestVideoParameter) throws Exception {
    String result = "exist";
    if (requestVideoParameter.getFile() == null || requestVideoParameter.getFile().isEmpty()) {
        return "Select a file";
    }
    ChecksumMD5 checksumMD5 = new ChecksumMD5();
    String checksum = "";
    String filePath = fileUploadService.saveInputFile(requestVideoParameter.getFile());
    checksum = checksumMD5.getMD5(filePath);
    if (fileService.getFileByMd5(checksum) == null) {
        fileService.saveFile(new File(filePath, checksum));
        result = "saved in data base";
    }
    try {
    VideoModel video = new VideoModel();
    String outputFile = "storage/convertedFiles/";
    ModelParameter videoParameter = new VideoParameter();
    videoParameter.setOutputFile(outputFile);
    ((VideoParameter) videoParameter).setFrames(requestVideoParameter.getFrames());
    ((VideoParameter) videoParameter).setExtension(requestVideoParameter.getFormat());
    ((VideoParameter) videoParameter).setVideoCodec(requestVideoParameter.getVideoCodec());
    ((VideoParameter) videoParameter).setAudioCodec(requestVideoParameter.getAudioCodec());
    if (requestVideoParameter.getExtractThumbnail() == 1) {
        ((VideoParameter) videoParameter).setExtractThumbnail(true);
    }
    Executor executor = new Executor();
    executor.executeCommandsList(video.createCommand(videoParameter));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }
}
