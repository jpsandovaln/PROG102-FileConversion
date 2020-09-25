/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller;

import org.fundacionjala.converter.controller.request.RequestExtractTextParameter;
import org.fundacionjala.converter.controller.response.ErrorResponse;
import org.fundacionjala.converter.controller.response.OkResponse;
import org.fundacionjala.converter.controller.service.FileUploadService;
import org.fundacionjala.converter.database.entity.File;
import org.fundacionjala.converter.controller.service.FileService;
import org.fundacionjala.converter.model.command.extractText.ExtractTextFacade;
import org.fundacionjala.converter.model.parameter.extractText.ExtractTextParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author Jhordan Soto
 * @version 1.0
 */
@RestController
public class ExtractTextController {
    @Autowired
    private FileService fileService;
    @Value("${tempFiles.path}")
    private String temporal;
    @Autowired
    private FileUploadService fileUploadService;
    /**
     *
     * @param requestExtractTextParameter
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/convertExtractText")
    public ResponseEntity convertExtractText(final RequestExtractTextParameter requestExtractTextParameter) throws Exception {
        requestExtractTextParameter.validate();
        String filePath = fileUploadService.saveInputFile(requestExtractTextParameter.getFile());
        String md5 = requestExtractTextParameter.generateMD5(filePath);
        if (fileService.getFileByMd5(md5) == null) {
            fileService.saveFile(new File(filePath, md5));
        }
        try {
            ExtractTextFacade extractor = new ExtractTextFacade();
            ExtractTextParameter parameter = new ExtractTextParameter();
            parameter.setInputFile(filePath);
            parameter.setLanguage(requestExtractTextParameter.getLanguage());
            parameter.setFormat(requestExtractTextParameter.getExportFormat());
            List<String> result = extractor.extractText(parameter);
            return ResponseEntity.ok().body(
                new OkResponse<Integer>(HttpServletResponse.SC_OK, result.get(0)));
        } catch (IOException | InterruptedException | ExecutionException e) {
            return ResponseEntity.badRequest()
                .body(new ErrorResponse<Integer>(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                new ErrorResponse<String>(Integer.toString(HttpServletResponse.SC_BAD_REQUEST), e.getMessage()));
        }
    }
}
