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

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
public class VideoController {

 @Autowired
 private FileService fileService;
  @Value("${tempFiles.path}")
  private String temporal;

    /**
     *
     * @param requestVideoParameter
     * @return
     */
  @RequestMapping(method = RequestMethod.POST, value = "convertVideo")
  public String convertVideo(final RequestVideoParameter requestVideoParameter) throws Exception {
      requestVideoParameter.validate();
      String result = "exist";
      String path = temporal + requestVideoParameter.getFile().getOriginalFilename();
      Files.copy(requestVideoParameter.getFile().getInputStream(), Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
      String md5 = requestVideoParameter.generateMD5(path);
      if (!requestVideoParameter.isInDataBase(md5, fileService)) {
          fileService.saveFile(new File(path, md5));
          result = "saved in database";
      }
      Files.delete(Paths.get(path));
      return result;
  }
}
