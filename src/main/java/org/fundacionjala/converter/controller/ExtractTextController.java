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
import org.fundacionjala.converter.controller.service.FileZipped;
import org.fundacionjala.converter.database.entity.File;
import org.fundacionjala.converter.controller.service.FileService;
import org.fundacionjala.converter.model.command.extractText.ExtractTextFacade;
import org.fundacionjala.converter.model.parameter.extractText.ExtractTextParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * @author Jhordan Soto
 * @version 1.0
 */
@RestController
public class ExtractTextController {
    @Autowired
    private FileService fileService;
    @Autowired
    private FileUploadService fileUploadService;

    /**
     * Extract text of a image
     * @param requestExtractTextParameter - the reference RequestExtractTextParameter that contains parameters of the file
     * @return ResponseEntity - the reference to OkResponse if file is converted successfully, ErrorResponse otherwise
     */
    @RequestMapping(method = RequestMethod.POST, value = "/convertExtractText")
    public ResponseEntity convertExtractText(final RequestExtractTextParameter requestExtractTextParameter) throws Exception {
        try {
            requestExtractTextParameter.validate();
            String md5 = requestExtractTextParameter.getMd5();
            String filePath = "";

            if (fileService.getFileByMd5(md5) == null) {
                filePath = fileUploadService.saveInputFile(requestExtractTextParameter.getFile());
                fileService.saveFile(new File(filePath, md5));
            } else {
                filePath = fileService.getFileByMd5(md5).getPath();
            }

            ExtractTextFacade extractor = new ExtractTextFacade();
            ExtractTextParameter parameter = new ExtractTextParameter();
            setRequestExtractTextParameter(parameter, requestExtractTextParameter, filePath);
            String result = FileZipped.zipper(parameter, extractor.extractText(parameter));
            return ResponseEntity.ok().body(
                new OkResponse<Integer>(HttpServletResponse.SC_OK, result));
        } catch (IOException | InterruptedException | ExecutionException e) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse<Integer>(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse<String>(Integer.toString(HttpServletResponse.SC_BAD_REQUEST), e.getMessage()));
        }
    }

    /**
     * Sets extractTextParameter value
     * @param extractTextParameter - the reference ExtractTextParameter to set parameters
     * @param requestExtractTextParameter - the reference RequestExtractTextParameter that contains parameters of the file
     * @param filePath - the reference String with path of the file
     * @throws IOException
     */
    private void setRequestExtractTextParameter(final ExtractTextParameter extractTextParameter,
            final RequestExtractTextParameter requestExtractTextParameter, final String filePath) throws IOException {
        extractTextParameter.setInputFile(filePath);
        extractTextParameter.setMd5(requestExtractTextParameter.getMd5());
        extractTextParameter.setLanguage(requestExtractTextParameter.getLanguage());
        extractTextParameter.setFormat(requestExtractTextParameter.getExportFormat());
    }
}
