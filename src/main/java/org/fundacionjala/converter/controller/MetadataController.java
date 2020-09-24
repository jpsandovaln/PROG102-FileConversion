/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller;

import org.fundacionjala.converter.executor.Executor;
import org.fundacionjala.converter.model.command.MetadataModel;
import org.fundacionjala.converter.model.parameter.metadata.MetadataParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.fundacionjala.converter.controller.service.FileService;
import org.fundacionjala.converter.controller.service.FileUploadService;
import org.fundacionjala.converter.controller.request.RequestMetadataParameter;
import org.fundacionjala.converter.controller.response.ErrorResponse;
import org.fundacionjala.converter.controller.response.OkResponse;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletResponse;

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
    @Value("${convertedFiles.path}")
    private String output;
    @Autowired
    private FileUploadService fileUploadService;

    /**
     *
     * @param requestMetadataParameter
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "extractMetadata")
    public ResponseEntity extractMetadata(final RequestMetadataParameter requestMetadataParameter) {
        try {
            requestMetadataParameter.validate();
            String filePath = fileUploadService.saveInputFile(requestMetadataParameter.getFile());
            String md5 = requestMetadataParameter.generateMD5(filePath);
            String exportFormat = requestMetadataParameter.getExportFormat();
            String detail = requestMetadataParameter.getDetail();
            MetadataParameter metaDataParameter = new MetadataParameter(filePath, exportFormat, detail, output, md5);
            metaDataParameter.setOutputFile(output + md5 + requestMetadataParameter.getExportFormat());

            Executor executor = new Executor();
            MetadataModel metaDataModel = new MetadataModel();
            List<String> result = executor.executeCommandsList(metaDataModel.createCommand(metaDataParameter));
            return ResponseEntity.ok().body(new OkResponse<Integer>(HttpServletResponse.SC_OK, result.toString()));
        } catch (IOException | InterruptedException | ExecutionException e) {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse<Integer>(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse<String>(Integer.toString(HttpServletResponse.SC_BAD_REQUEST), e.getMessage()));
        }
    }
}
