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

    /**
     *
     * @param requestExtractTextParameter
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "convertExtractText")
    public String convertExtractText(final RequestExtractTextParameter requestExtractTextParameter) throws IOException {
        //ExtractTextParameter extractTextParameter;
        String result = "Error";
      //  Executor exec;
        String path = temporal + requestExtractTextParameter.getFile().getOriginalFilename();
        Files.copy(requestExtractTextParameter.getFile().getInputStream(), Paths.get(path));
        if (requestExtractTextParameter.validate()) {
            fileService.saveFile(new File(path, requestExtractTextParameter.generateMD5(path)));
            //extractTextParameter = new ExtractTextParameter();
            //result = exec.executer(extractTextParameter);
        }
        Files.delete(Paths.get(path));
        return result;
    }
}
