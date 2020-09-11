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
import org.fundacionjala.converter.model.entity.File;
import org.fundacionjala.converter.model.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class VideoController {

 /* @Autowired
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
/*  @RequestMapping(method = RequestMethod.POST, value = "/video-converter")
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
  }*/
 @Autowired
 private FileService fileService;
  @Value("${tempFiles.path}")
  private String temporal;

    /**
     *
     * @param requestVideoParameter
     * @return
     * @throws IOException
     */
  @RequestMapping(method = RequestMethod.POST, value = "convertVideo")
  public String convertVideo(final RequestVideoParameter requestVideoParameter) throws IOException {

   // VideoParameter videoParameter;
    String result = "Error";
   // Executor exec;
    String path = temporal + requestVideoParameter.getFile().getOriginalFilename();
    Files.copy(requestVideoParameter.getFile().getInputStream(), Paths.get(path));
    if (requestVideoParameter.validate()) {
      fileService.saveFile(new File(path, requestVideoParameter.generateMD5(path)));
    //  videoParameter = new VideoParameter();
     // result = exec.executer(videoParameter);
    }
    Files.delete(Paths.get(path));
    return result;
  }
}
