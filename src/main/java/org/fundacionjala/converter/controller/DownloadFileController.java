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
import org.springframework.web.bind.annotation.*;
/**
 * @author Elizabeth Bravo Flores
 * @version 0.1
 */
@RestController
public class DownloadFileController {
    /**
     * displays file upload successfully with md5
     * @param path of file
     * @return file download successfully.
     * @throws Exception
     */
    @RequestMapping(path = "/download", method = RequestMethod.GET)
    public ResponseEntity<Resource> download(@RequestParam("path") String path) throws IOException {
        File file = new File(path);
        HttpHeaders header = new HttpHeaders();
        String ext = FilenameUtils.getExtension(path);
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=metadata" + "." + ext);
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
