package org.fundacionjala.converter.controller.request;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

class RequestExtractTextParameterTest {

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
                    MockMultipartFile mockFile = new MockMultipartFile("", "",
                            "image/", "imagen7 data".getBytes());
                    MockMultipartFile multipartFile = new MockMultipartFile(
                            "attachments",file.getName(),
                            MediaType.MULTIPART_FORM_DATA_VALUE,
                            mockFile.getInputStream());
                    request.setExportFormat(".txt");
                    request.setFile(mockFile);
                    request.validate();
                }
        );
    }
    @Test
    public void md5ValidationTest() {
        Throwable exception = Assertions.assertThrows(
                Exception.class, () -> {
                    MockMultipartFile file = new MockMultipartFile("imagen7", "imagen7.jpg",
                            "image/", "imagen7 data".getBytes());
                    MockMultipartFile multipartFile = new MockMultipartFile(
                            "attachments",file.getName(),
                            MediaType.MULTIPART_FORM_DATA_VALUE,
                            file.getInputStream());
                    request.setExportFormat(".txt");
                    request.setLanguage("spa");
                    request.setFile(multipartFile);
                    request.setMd5("ffa5f5433efe74bc99530e84798b2ffd");
                    request.validate();
                }
        );
    }
    @Test
    public void languageNullValidationTest() throws Exception {
        Throwable exception = Assertions.assertThrows(
                Exception.class, () -> {
                    MockMultipartFile file = new MockMultipartFile("imagen7", "imagen7.jpg",
                            "image/", "video data".getBytes());
                    MockMultipartFile multipartFile = new MockMultipartFile(
                            "attachments", file.getName(),
                            MediaType.MULTIPART_FORM_DATA_VALUE,
                            file.getInputStream());
                    request.setExportFormat(".txt");
                    request.setFile(multipartFile);
                    request.setLanguage(null);
                    request.setMd5("a1cbf54e11273fb97da300ec3dc57a87");
                    request.validate();

                }
        );
    }
    @Test
    public void languageEmptyValidationTest() throws Exception {
        Throwable exception = Assertions.assertThrows(
                Exception.class, () -> {
                    MockMultipartFile file = new MockMultipartFile("imagen7", "imagen7.jpg",
                            "image/", "video data".getBytes());
                    MockMultipartFile multipartFile = new MockMultipartFile(
                            "attachments", file.getName(),
                            MediaType.MULTIPART_FORM_DATA_VALUE,
                            file.getInputStream());
                    request.setExportFormat(".txt");
                    request.setFile(multipartFile);
                    request.setLanguage("");
                    request.setMd5("a1cbf54e11273fb97da300ec3dc57a87");
                    request.validate();

                }
        );
    }
}