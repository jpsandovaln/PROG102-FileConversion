/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller;
import org.fundacionjala.converter.database.entity.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.fundacionjala.converter.controller.service.FileService;
import org.fundacionjala.converter.controller.request.RequestMetadataParameter;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @author Angela Martinez
 * @version 0.1
 */
@RestController
public class MetadataController {

   @Autowired
   private FileService fileService;
    @Value("${tempFiles.path}")
    private String temporal;

    /**
     *
     * @param requestMetadataParameter
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "extractMetadata")
    public String extractMetadata(final RequestMetadataParameter requestMetadataParameter) throws Exception {
        requestMetadataParameter.validate();
        String result = "exist";
        String path = temporal + requestMetadataParameter.getFile().getOriginalFilename();
        Files.copy(requestMetadataParameter.getFile().getInputStream(), Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
        String md5 = requestMetadataParameter.generateMD5(path);
        if (!requestMetadataParameter.isInDataBase(md5, fileService)) {
            fileService.saveFile(new File(path, md5));
            result = "saved in database";
        }
        Files.delete(Paths.get(path));
        return result;
    }
}
