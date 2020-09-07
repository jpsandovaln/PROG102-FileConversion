/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller;
import org.fundacionjala.converter.model.metadata.MetadataExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetadataController {
    @Autowired
    private MetadataExtractor extractor;
    @RequestMapping(value = "extractMetadata", method = RequestMethod.POST, params = {"pathFile", "exportFormat", "detail"})
    public String getMetadata(@RequestParam(required = true) String pathFile, @RequestParam(required = true) String exportFormat, @RequestParam String detail) {
        if (pathFile.isEmpty() || exportFormat.isEmpty()) {
            return "error empty";
        } else {
            return extractor.getMetadata(pathFile, exportFormat, detail);
        }
    }
}
