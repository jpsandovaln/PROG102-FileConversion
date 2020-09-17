/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller;

import org.fundacionjala.converter.controller.request.RequestImageParameter;
import org.fundacionjala.converter.executor.Executor;
import org.fundacionjala.converter.model.command.ImageModel;
import org.fundacionjala.converter.model.parameter.image.ImageParameter;
import org.fundacionjala.converter.model.service.FileService;
import org.fundacionjala.converter.model.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RestController
public class ImageController {


    @Autowired
    private FileService fileService;
    @Value("${tempFiles.path}")
    private String temporal;
    @Value("${convertedFiles.path}")
    private String output;
    @Autowired
    private FileUploadService fileUploadService;
    /**
     *
     * @param requestImageParameter
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "convertImage")
    public String convertImage(final RequestImageParameter requestImageParameter) throws Exception {
        requestImageParameter.validate();
        String filePath = fileUploadService.saveInputFile(requestImageParameter.getFile());
        String md5 = requestImageParameter.generateMD5(filePath);
        ImageParameter imageParameter = new ImageParameter();
        imageParameter.setInputFile(filePath);
        imageParameter.setOutputFile("storage/convertedFiles");
        imageParameter.setIsGray(false);
        imageParameter.setIsThumbnail(requestImageParameter.getThumbnail());
        imageParameter.setOutputFile(output + md5 + ".jpg");

        Executor executor = new Executor();
        ImageModel imageModel = new ImageModel();
        List<String> response = executor.executeCommandsList(imageModel.createCommand(imageParameter));
        return response.toString();
    }
}
