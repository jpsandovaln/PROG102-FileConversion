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
import org.fundacionjala.converter.database.entity.File;
import org.fundacionjala.converter.model.parameter.multimedia.VideoParameter;
import org.fundacionjala.converter.controller.service.FileService;
import org.fundacionjala.converter.controller.service.FileUploadService;
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
    String outputFile = "storage/convertedFiles/";
    VideoModel videoModel = new VideoModel();
    VideoParameter videoParameter = new VideoParameter();
        videoParameter.setOutputFile(outputFile);
        videoParameter.setFrames(requestVideoParameter.getFrames());
        videoParameter.setExtension(requestVideoParameter.getFormat());
        videoParameter.setVideoCodec(requestVideoParameter.getVideoCodec());
        videoParameter.setAudioCodec(requestVideoParameter.getAudioCodec());
    if (requestVideoParameter.getExtractThumbnail() == 1) {
        videoParameter.setExtractThumbnail(true);
    }
    Executor executor = new Executor();
    executor.executeCommandsList(videoModel.createCommand(videoParameter));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }
}
