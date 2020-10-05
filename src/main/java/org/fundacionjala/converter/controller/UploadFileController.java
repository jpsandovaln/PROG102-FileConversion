/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller;

import org.fundacionjala.converter.controller.exceptions.NonExistentException;
import org.fundacionjala.converter.database.exception.NullAttributeException;
import org.fundacionjala.converter.model.utility.ChecksumMD5;
import org.fundacionjala.converter.database.entity.File;
import org.fundacionjala.converter.controller.service.FileService;
import org.fundacionjala.converter.controller.service.FileUploadService;
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

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
     * Displays file upload successfully with md5
     * @param file - the reference file
     * @return ResponseEntity - the reference to Ok if file is uploaded successfully
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
     * Displays message about file loading, and inserted md5 if it is correct or not
     * @param file - the reference MultipartFile of the file
     * @param md5 - the reference String with md5 of the file
     * @return ResponseEntity - the reference to ok if the file was uploaded
     *          successfully with message of correct or incorrect md5
     * @throws Exception
     */
    @PostMapping("/upload-md5")
    public ResponseEntity<?> uploadFile(@RequestParam("file") final MultipartFile file,
            @RequestParam final String md5) throws IOException, NoSuchAlgorithmException, NullAttributeException {
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
     * Deletes file in database
     * @param file - the reference File to delete
     * @return ResponseEntity - the reference to OkResponse if file is deleted successfully
     */
    @DeleteMapping("/delete")
    public ResponseEntity<?>  deleteFile(@RequestBody final File file) {
        try {
            fileService.deleteFile(file);
            return new ResponseEntity<Object>("File deleted", HttpStatus.OK);
        } catch (NonExistentException e) {
            return new ResponseEntity<Object>("File doesn't exist", HttpStatus.OK);
        } catch (NullAttributeException e) {
            return new ResponseEntity<Object>("File error: " + e.getMessage(), HttpStatus.OK);
        }
    }

    /**
     * Deletes the file with corresponding md5
     * @param md5 - the reference String to md5 of file to delete
     * @return ResponseEntity - the reference to OkResponse if file is converted successfully
     */
    @DeleteMapping("/delete-md5{md5}")
    public ResponseEntity<?> deleteByMD5(@RequestParam("md5")  final String md5) {
        File file = fileService.getFileByMd5(md5);
        try {
            String filename = file.toString();
            fileService.deleteFileByMd5(md5);
            return new ResponseEntity<Object>("File " + filename + "deleted", HttpStatus.OK);
        } catch (NonExistentException e) {
            return new ResponseEntity<Object>("File doesn't exist", HttpStatus.OK);
        }
    }

    /**
     * Gets the List of files
     * @return list - the reference List<File> of files of database
     */
    @GetMapping("/files")
    public List<File> getFiles() {
        return fileService.getFileList();
    }

    /**
     * Gets File with corresponding id
     * @param id the reference int of id of the file
     * @return file
     */
    @GetMapping("/file{id}")
    public File getFile(@RequestParam final int id) {
        return  fileService.getFileById(id);
    }

    /**
     * Gets File with corresponding md5
     * @param md5 the reference String of md5 of the file
     * @return file
     */
    @GetMapping("/file-md5{md5}")
    public File getFileByMD5(@RequestParam final String md5) {
        fileService.getFileByMd5(md5);
        return  fileService.getFileByMd5(md5);
    }

    /**
     * Updates file in database
     * @param file the File to update
     */
    @PostMapping("/update-file")
    public ResponseEntity<?> updateFile(@RequestParam final File file) {
        try {
            fileService.updateFile(file);
            return new ResponseEntity<Object>("File updated", HttpStatus.OK);
        } catch (NonExistentException e) {
            return new ResponseEntity<Object>("File doesn't exist", HttpStatus.BAD_REQUEST);
        } catch (NullAttributeException e) {
            return new ResponseEntity<Object>("File error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
