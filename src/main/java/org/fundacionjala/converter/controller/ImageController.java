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
import org.fundacionjala.converter.controller.response.ErrorResponse;
import org.fundacionjala.converter.controller.response.OkResponse;
import org.fundacionjala.converter.controller.service.FileZipped;
import org.fundacionjala.converter.executor.Executor;
import org.fundacionjala.converter.model.command.ImageModel;
import org.fundacionjala.converter.model.parameter.image.ImageParameter;
import org.fundacionjala.converter.controller.service.FileService;
import org.fundacionjala.converter.controller.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletResponse;

@RestController
public class ImageController {


    @Autowired
    private FileService fileService;
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
    public ResponseEntity convertImage(final RequestImageParameter requestImageParameter) {
        try {
            requestImageParameter.validate();
            String filePath = fileUploadService.saveInputFile(requestImageParameter.getFile());
            String md5 = requestImageParameter.generateMD5(filePath);

            ImageParameter imageParameter = new ImageParameter();
            imageParameter.setInputFile(filePath);
            imageParameter.setIsGray(requestImageParameter.getGray());
            imageParameter.setIsThumbnail(requestImageParameter.getExtractThumbnail());
            imageParameter.setIsResize(requestImageParameter.getChangeSize());
            imageParameter.setPositionXAndPositionY(requestImageParameter.getPosition());
            imageParameter.setName(md5);
            imageParameter.setFormat(requestImageParameter.getExportFormat());
            imageParameter.setOutputFile(output + md5 + requestImageParameter.getExportFormat());

            Executor executor = new Executor();
            ImageModel imageModel = new ImageModel();
            String result = FileZipped.zipper(imageParameter, executor.executeCommandsList(imageModel.createCommand(imageParameter)));
            return ResponseEntity.ok().body(
                    new OkResponse<Integer>(HttpServletResponse.SC_OK, result.toString()));
        } catch (IOException | InterruptedException | ExecutionException e) {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse<Integer>(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse<String>(Integer.toString(HttpServletResponse.SC_BAD_REQUEST), e.getMessage()));
        }
    }
}
