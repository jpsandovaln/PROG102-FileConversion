package org.fundacionjala.converter.controller;

import org.fundacionjala.converter.model.ChecksumMD5;
import org.fundacionjala.converter.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class UploadFileController {

    @Autowired
    private FileUploadService fileUploadService;

    /**
     * displays upload file without param
     * @return upload file
     */
    @GetMapping("/")
    public String displayUploadView() {
        return "upload file";
    }

    /**
     * displays file upload successfully with md5
     * @param file uploaded
     * @return file upload successfully with md5.
     * @throws Exception
     */
    @PostMapping("upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") final MultipartFile file) throws Exception {
        if (file == null || file.isEmpty()) {
            return new ResponseEntity<Object>("Select a file", HttpStatus.OK);
        }
        String checksum = "";
        try {
            String filePath = fileUploadService.saveInputFile(file);
            ChecksumMD5 checksumMD5 = new ChecksumMD5();
            checksum = checksumMD5.getMD5(filePath);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Object>("file upload successfully" + "\n" + checksum, HttpStatus.OK);
    }
    /**
     * displays file upload successfully with message correct or incorrect md5
     * @param file uploaded
     * @param md5 String
     * @return file upload successfully with message correct or incorrect md5
     * @throws Exception
     */
    @PostMapping("upload-md5")
    public ResponseEntity<?> uploadFile(@RequestParam("file") final MultipartFile file, @RequestParam final String md5) throws Exception {
        if (file == null || file.isEmpty()) {
            return new ResponseEntity<Object>("Select a file", HttpStatus.OK);
        }
        //md5 verificar si hay bd si existe -> path de data base,
        //sino validar
        String checksum = "";
        try {
            String filePath = fileUploadService.saveInputFile(file);
            ChecksumMD5 checksumMD5 = new ChecksumMD5();
            checksum = checksumMD5.getMD5(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String message = "";
        //checksum.equals(md5);
        if (checksum.equals(md5)) {
            message = "correct MD5";
            //data-base
        } else {
            message = "Incorrect MD5";
        }
        return new ResponseEntity<Object>("file upload successfully" + "\n" + message + "\n" + checksum, HttpStatus.OK);
    }
}
