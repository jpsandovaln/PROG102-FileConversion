/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.fundacionjala.converter.model.VideoModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class VideoController {

  /**
   * videoConverter
   */
  @RequestMapping(method = RequestMethod.POST, value = "/video-converter")
  public String videoConverter(@RequestParam("file") final MultipartFile file, @RequestParam("ext") final String ext) {
    VideoModel video;
    try {
      video = new VideoModel(file);
      if (ext.equals("mp4")) {
        video.compressToMp4();
      } else if (ext.equals("gif")) {
        video.gif();
      }
      return video.getOutputFileNmae();
    } catch (ExecutionException | InterruptedException | IOException e) {
      e.printStackTrace();
      return "Error";
    }
  }
}
