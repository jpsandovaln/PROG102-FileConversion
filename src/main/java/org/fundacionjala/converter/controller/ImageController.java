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
import org.fundacionjala.converter.database.entity.File;
import org.fundacionjala.converter.executor.Executor;
import org.fundacionjala.converter.model.command.ICommand;
import org.fundacionjala.converter.model.command.ImageModel;
import org.fundacionjala.converter.model.commons.exception.ModelParameterException;
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
import java.security.NoSuchAlgorithmException;
import java.util.List;
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
     * Converts image
     * @param requestImageParameter - the reference RequestImageParameter that contains parameters of the file
     * @return ResponseEntity - the reference to OkResponse if file is converted successfully, ErrorResponse otherwise
     */
    @RequestMapping(method = RequestMethod.POST, value = "convertImage")
    public ResponseEntity convertImage(final RequestImageParameter requestImageParameter) {
        try {
            requestImageParameter.validate();
            String md5 = requestImageParameter.getMd5();
            String filePath = "";
            if (fileService.getFileByMd5(md5) == null) {
                filePath = fileUploadService.saveInputFile(requestImageParameter.getFile());
                fileService.saveFile(new File(filePath, md5));
            } else {
                filePath = fileService.getFileByMd5(md5).getPath();
            }
            ImageParameter imageParameter = new ImageParameter();
            setImageParameter(imageParameter, requestImageParameter, filePath);
            String result = FileZipped.zipper(imageParameter, execute(imageParameter));
            return ResponseEntity.ok().body(new OkResponse<Integer>(HttpServletResponse.SC_OK, result.toString()));
        } catch (IOException | InterruptedException | ExecutionException e) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse<Integer>(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse<String>(Integer.toString(HttpServletResponse.SC_BAD_REQUEST), e.getMessage()));
        }
    }

    /**
     * Sets imageParameter value
     * @param parameter - the reference ImageParameter to set parameters
     * @param request - the reference RequestImageParameter that contains parameters of the file
     * @param filePath - the reference String with path of the file
     * @throws IOException
     */
    private void setImageParameter(final ImageParameter parameter,
            final RequestImageParameter request, final String filePath) throws IOException {
        parameter.setInputFile(filePath);
        parameter.setIsGray(request.getGray());
        parameter.setIsThumbnail(request.getExtractThumbnail());
        parameter.setIsResize(request.getChangeSize());
        parameter.setHeight(request.getHeight());
        parameter.setWidth(request.getWidth());
        parameter.setMd5(request.getMd5());
        if (!"".equals(request.getPosition())) {
            parameter.setPositionXAndPositionY(request.getPosition());
        }
        parameter.setMetadata(request.getExtractMetadata());
        parameter.setName(request.getMd5());
        parameter.setFormat(request.getExportFormat());
        parameter.setOutputFile(output);
    }

    /**
     * Executes the command list
     * @param parameter - the reference ImageParameter to set parameters
     * @return list - the reference to the list<String> that contains the file paths of converted files
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    private List<String> execute(final ImageParameter parameter)
            throws InterruptedException, ExecutionException, IOException, NoSuchAlgorithmException, ModelParameterException {
        Executor executor = new Executor();
        ICommand imageModel = new ImageModel();
        return executor.executeCommandsList(imageModel.createCommand(parameter));
    }
}
