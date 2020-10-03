package org.fundacionjala.converter.controller.request;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.fundacionjala.converter.Application;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

class RequestExtractTextParameterTest {
    @Autowired
    private Application application;

    RequestExtractTextParameter request = new RequestExtractTextParameter();
    @Test
    void getLanguage() {
        request.setLanguage("spa");
        String expected = "spa";
        String actual = request.getLanguage();
        assertEquals(expected, actual);
    }
    @Test
    public void emptyLanguageTest() {
        Throwable exception = Assertions.assertThrows(
                Exception.class, () -> {
                    request.setLanguage(" ");
                    request.validate();
                }
        );
    }
    @Test
    public void nullLanguageTest() {
        Throwable exception = Assertions.assertThrows(
                Exception.class, () -> {
                    request.setLanguage(null);
                    request.validate();
                }
        );
    }
    @Test
    public void nullFormatTest() {
        Throwable exception = Assertions.assertThrows(
                Exception.class, () -> {
                    request.setExportFormat(null);
                    request.validate();
                }
        );
    }
    @Test
    public void emptyFormatTest() {
        Throwable exception = Assertions.assertThrows(
                Exception.class, () -> {
                    request.setExportFormat(" ");
                    request.validate();
                }
        );
    }
    @Test
    public void nullFileTest() {
        Throwable exception = Assertions.assertThrows(
                Exception.class, () -> {
                    request.setFile(null);
                    request.validate();
                }
        );
    }
    @Test
    public void getMd5Test() {
        request.setMd5("ffa5f5433efe74bc99530e84798b2ffd");
        String expected = "ffa5f5433efe74bc99530e84798b2ffd";
        String actual = request.getMd5();
        assertEquals(expected, actual);
    }
    @Test
    public void generateMd5Test() {
        String expected = "ffa5f5433efe74bc99530e84798b2ffd";
        String actual = request.generateMD5("storage/inputFiles/imagen7.jpg");
        assertEquals(expected, actual);
    }
    @Test
    public void generateMd5WithInvalidFileTest() {
        String expected = "Could not get MD5 from input file.";
        String actual = request.generateMD5("invalidPath");
        assertEquals(expected, actual);
    }
    @Test
    public void nullFileValidationTest() {
        Throwable exception = Assertions.assertThrows(
                Exception.class, () -> {
                    request.setExportFormat(".txt");
                    request.setFile(null);
                    request.validate();
                }
        );
    }
    @Test
    public void emptyFileNameValidationTest() {
        File file = new File(" ");
        Throwable exception = Assertions.assertThrows(
                Exception.class, () -> {
                    request.setExportFormat(".txt");
                    request.setFile((MultipartFile) file);
                    request.validate();
                }
        );
        System.out.println(exception.getMessage());
    }
    @Test
    public void md5ValidationTest() {
        Throwable exception = Assertions.assertThrows(
                Exception.class, () -> {
                    Resource fileResource = new ClassPathResource(
                            "storage/inputFiles/imagen7.jpg");
                    //assertNotNull(fileResource);
                    MockMultipartFile multipartFile = new MockMultipartFile(
                            "attachments",fileResource.getFilename(),
                            MediaType.MULTIPART_FORM_DATA_VALUE,
                            fileResource.getInputStream());
                    request.setExportFormat(".txt");
                    request.setLanguage("spa");
                    request.setFile(multipartFile);
                    request.setMd5("ffa5f5433efe74bc99530e84798b2ffd");
                    request.validate();

                }

        );
        System.out.println(exception.getMessage());
    }
}