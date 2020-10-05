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
import org.fundacionjala.converter.model.command.ICommand;
import org.fundacionjala.converter.model.command.MetadataModel;
import org.fundacionjala.converter.model.commons.exception.ModelParameterException;
import org.fundacionjala.converter.model.parameter.metadata.MetadataParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.fundacionjala.converter.controller.service.FileService;
import org.fundacionjala.converter.controller.service.FileUploadService;
import org.fundacionjala.converter.database.entity.File;
import org.fundacionjala.converter.controller.request.RequestMetadataParameter;
import org.fundacionjala.converter.controller.response.ErrorResponse;
import org.fundacionjala.converter.controller.response.OkResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
            String md5 = requestMetadataParameter.getMd5();
            String filePath = "";

            if (fileService.getFileByMd5(md5) == null) {
                filePath = fileUploadService.saveInputFile(requestMetadataParameter.getFile());
                fileService.saveFile(new File(filePath, md5));
            } else {
                filePath = fileService.getFileByMd5(md5).getPath();
            }

            String exportFormat = requestMetadataParameter.getExportFormat();
            String detail = requestMetadataParameter.getDetail();
            MetadataParameter metaDataParameter = new MetadataParameter(filePath, exportFormat, detail, output, md5);
            metaDataParameter.setOutputFile(output + md5 + requestMetadataParameter.getExportFormat());
            String result = execute(metaDataParameter).get(0);
            return ResponseEntity.ok().body(new OkResponse<Integer>(HttpServletResponse.SC_OK, result));
        } catch (IOException | InterruptedException | ExecutionException e) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse<Integer>(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse<String>(Integer.toString(HttpServletResponse.SC_BAD_REQUEST), e.getMessage()));
        }
    }

    /**
     * Executes the command list
     * @param parameter - the reference ImageParameter to set parameters
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    private List<String> execute(final MetadataParameter parameter)
            throws InterruptedException, ExecutionException, IOException, NoSuchAlgorithmException, ModelParameterException {
        Executor executor = new Executor();
        ICommand metaDataModel = new MetadataModel();
        return executor.executeCommandsList(metaDataModel.createCommand(parameter));
    }
}
