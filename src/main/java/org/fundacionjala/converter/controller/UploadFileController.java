package org.fundacionjala.converter.controller;

import org.fundacionjala.converter.model.ChecksumMD5;
import org.fundacionjala.converter.model.entity.File;
import org.fundacionjala.converter.model.service.FileService;
import org.fundacionjala.converter.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
     * displays file upload successfully with message correct or incorrect md5
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
    @DeleteMapping("/file")
    public void addFile(@RequestParam final File file) {
        fileService.deleteFile(file);
    }

    /**
     * delete the file according to your md5
     * @param md5's file to delete
     */
    @DeleteMapping("/file{md5}")
    public void addFile(@RequestParam final String md5) {
        fileService.deleteFileByMd5(md5);
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
    @GetMapping("/file{md5}")
    public File getFile(@RequestParam final String md5) {
        return  fileService.getFileByMd5(md5);
    }

    /**
     * update file in data base
     * @param file to update
     */
    @PostMapping("/file")
    public void updateFile(@RequestParam final File file) {
        fileService.updateFile(file);
    }
}
