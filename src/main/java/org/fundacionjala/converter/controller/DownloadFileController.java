/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Elizabeth Bravo Flores
 * @version 0.1
 */
@RestController
public class DownloadFileController {

    /**
     * Displays file to download
     * @param path - the reference String with path of the file
     * @param filenameDownload - the reference String with the name of the file
     * @return ResponseEntity - the reference to Ok if file is downloaded successfully
     * @throws IOException
     */
    @RequestMapping(path = "/download", method = RequestMethod.GET)
    public ResponseEntity<Resource> download(final @RequestParam("path") String path,
            final @RequestParam("filenameDownload") String filenameDownload) throws IOException {
        File file = new File(path);
        HttpHeaders header = new HttpHeaders();
        String ext = FilenameUtils.getExtension(path);
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filenameDownload + "." + ext);
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        Path pathfile = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(pathfile));
        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
}
