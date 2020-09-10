/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller;

import org.fundacionjala.converter.model.ChecksumMD5;
import org.fundacionjala.converter.model.entity.File;
import org.fundacionjala.converter.model.service.FileService;
import org.fundacionjala.converter.model.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Julia Escalante
 * @version 0.1
 */
@RestController
public class UploadFileController {

    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    private FileService fileService;

    /**
     * displays upload file without param
     * @return upload file
     */
    @GetMapping("/modifying")
    public String index() {
        return "upload file";
    }

    /**
     * displays file upload successfully with md5
     * @param file uploaded
     * @return file upload successfully with md5.
     * @throws Exception
     */
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") final MultipartFile file) throws Exception {
        if (file == null || file.isEmpty()) {
            return new ResponseEntity<Object>("Select a file", HttpStatus.OK);
        }
        ChecksumMD5 checksumMD5 = new ChecksumMD5();
        String checksum = "";
        String filePath = fileUploadService.saveInputFile(file);
        checksum = checksumMD5.getMD5(filePath);
        if (fileService.getFileByMd5(checksum) == null) {
            fileService.saveFile(new File(filePath, checksum));
            return new ResponseEntity<Object>("file upload successfully" + "\n" + checksum, HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>("the file already exists" + "\n" + checksum, HttpStatus.OK);
        }
    }
    /**
     * displays message about file loading, and inserted md5 is correct or not
     * @param file uploaded
     * @param md5 String
     * @return file upload successfully with message correct or incorrect md5
     * @throws Exception
     */
    @PostMapping("/upload-md5")
    public ResponseEntity<?> uploadFile(@RequestParam("file") final MultipartFile file, @RequestParam final String md5) throws Exception {
        if (file == null || file.isEmpty()) {
            return new ResponseEntity<Object>("Select a file", HttpStatus.OK);
        }
        ChecksumMD5 checksumMD5 = new ChecksumMD5();
        String checksum = "";
        boolean correctMd5;
        if (fileService.getFileByMd5(md5) == null) { //no existe
            String filePath = fileUploadService.saveInputFile(file);
            checksum = checksumMD5.getMD5(filePath);
            correctMd5 = checksum.equals(md5);
            fileService.saveFile(new File(filePath, checksum));
            return new ResponseEntity<Object>("file upload successfully" + "\n" + "checksum entered is" + correctMd5, HttpStatus.OK);
        } else {
            String filePath = fileService.getFileByMd5(md5).getPath();
            checksum = checksumMD5.getMD5(filePath);
            correctMd5 = checksum.equals(md5);
            return new ResponseEntity<Object>("the file already exists" + "\n" + "checksum entered is: " + correctMd5, HttpStatus.OK);
        }
    }

    /**
     * delete the file in data base
     * @param file to delete
     */
    @DeleteMapping("/delete")
    public ResponseEntity<?>  deleteFile(@RequestBody final File file) {
        if (fileService.getFileByMd5(file.getMd5()) != null) {
            fileService.deleteFile(file);
            return new ResponseEntity<Object>("file deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>("file doesn't exist", HttpStatus.OK);
        }
    }

    /**
     * delete the file according to your md5
     * @param md5's file to delete
     */
    @DeleteMapping("/delete-md5{md5}")
    public ResponseEntity<?>  deleteByMD5(@RequestParam("md5")  final String md5) {
        File file = fileService.getFileByMd5(md5);
        String filename = file.toString();
        if (file != null) {
            fileService.deleteFile(file);
            return new ResponseEntity<Object>("file deleted" + filename, HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>("file doesn't exist" + filename, HttpStatus.OK);
        }
    }

    /**
     * get List files of database
     * @return list files
     */
    @GetMapping("/files")
    public List<File> getFiles() {
        return fileService.getFileList();
    }

    /**
     * get file with id
     * @param id to find
     * @return file
     */

    @GetMapping("/file{id}")
    public File getFile(@RequestParam final int id) {
        return  fileService.getFileById(id);
    }

    /**
     * get File with md5
     * @param md5 to find
     * @return file
     */
    @GetMapping("/file-md5{md5}")
    public File getFileByMD5(@RequestParam final String md5) {
        fileService.getFileByMd5(md5);
        return  fileService.getFileByMd5(md5);
    }

    /**
     * update file in data base
     * @param file to update
     */
    @PostMapping("/update-file")
    public void updateFile(@RequestParam final File file) {
        fileService.updateFile(file);
    }
}
