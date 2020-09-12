/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller;

import org.fundacionjala.converter.model.ChecksumMD5;
import org.fundacionjala.converter.model.command.VideoModel;
import org.fundacionjala.converter.model.entity.File;
import org.fundacionjala.converter.model.service.FileUploadService;
import org.fundacionjala.converter.model.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class VideoController {

  @Autowired
  private FileUploadService fileUploadService;
  @Autowired
  private FileService fileService;

  /**
   * videoConverter
   *
   * @throws InterruptedException
   * @throws IOException
   * @throws ExecutionException
   * @throws NoSuchAlgorithmException
   */
  @RequestMapping(method = RequestMethod.POST, value = "/video-converter")
  public ResponseEntity<?> videoConverter(@RequestParam("file") final MultipartFile file,
      @RequestParam("ext") final String ext) {
    try {
      VideoModel video = new VideoModel();
      String filePath = fileUploadService.saveInputFile(file);
      video.setInputFileName(filePath);
      String checksum = new ChecksumMD5().getMD5(video.getFileName());
      String path = "";
      if (fileService.getFileByMd5(checksum) == null) {
        // save in the database the filename and md5
        video.videoConverter(ext);
        fileService.saveFile(new File(video.getOutputFileName(), checksum));
        path = video.getOutputFileName();
      } else {
        path = fileService.getFileByMd5(checksum).getPath();
      }
      return new ResponseEntity<Object>(checksum + "\n" + path, HttpStatus.OK);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return new ResponseEntity<Object>("Error" + "\n" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
