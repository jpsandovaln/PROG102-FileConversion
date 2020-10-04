package org.fundacionjala.converter.controller.request;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class RequestMetadataParameterTest {
    RequestMetadataParameter requestMetadataParameter = new RequestMetadataParameter();
    File file = new File("storage/inputFiles/test.mp3");

    @Test
    public void setRequestMetadataParameterWithNullFormat() {
        Throwable exception = Assertions.assertThrows(
                Exception.class,
                () -> {
                    requestMetadataParameter.setExportFormat(null);
                    requestMetadataParameter.validate();
                }
        );
    }

    @Test
    public void setRequestMetadataParameterWithNullFileName() {
        Throwable exception = Assertions.assertThrows(
                Exception.class,
                () -> {
                    requestMetadataParameter.setExportFormat("t");
                    requestMetadataParameter.setFileName(null);
                    requestMetadataParameter.validate();
                }
        );
    }

    @Test
    public void setRequestMetadataParameterWithNullMD5() {
        Throwable exception = Assertions.assertThrows(
                Exception.class,
                () -> {
                    requestMetadataParameter.setExportFormat("t");
                    requestMetadataParameter.setFileName(null);
                    requestMetadataParameter.validate();
                }
        );
    }

    @Test
    public void setRequestMetadataParameterWithInvalidMD5() {
        Throwable exception = Assertions.assertThrows(
                Exception.class,
                () -> {
                    requestMetadataParameter.setExportFormat("t");
                    requestMetadataParameter.setFile((MultipartFile) file);
                    requestMetadataParameter.validate();
                }
        );
    }

    @Test
    public void setRequestMetadataParameterWith() {
        Throwable exception = Assertions.assertThrows(
                Exception.class,
                () -> {
                    requestMetadataParameter.setExportFormat("t");
                    requestMetadataParameter.setFile((MultipartFile) file);
                    requestMetadataParameter.setMd5("e1b3fab24c8af81c1aa13dbbb4e44ff0");
                    requestMetadataParameter.validate();
                }
        );
    }

    @Test
    public void getFileName() {
        requestMetadataParameter.setFileName("file.txt");
        String result = requestMetadataParameter.getFileName();
        String expected = "file.txt";
        assertEquals(result, expected);
    }

    @Test
    public void getExportFormat() {
        requestMetadataParameter.setExportFormat("h");
        String result = requestMetadataParameter.getExportFormat();
        String expected = "h";
        assertEquals(result, expected);
    }

    @Test
    public void getDetail() {
        requestMetadataParameter.setDetail("d");
        String result = requestMetadataParameter.getDetail();
        String expected = "d";
        assertEquals(result, expected);
    }

    @Test
    public void nullFileTest() {
        Throwable exception = Assertions.assertThrows(
                Exception.class, () -> {
                    requestMetadataParameter.setExportFormat("mp4");
                    requestMetadataParameter.setFile(null);
                    requestMetadataParameter.validate();
                }
        );
    }

    @Test
    public void getMd5Test() {
        requestMetadataParameter.setMd5("ffa5f5433efe74bc99530e84798b2ffd");
        String expected = "ffa5f5433efe74bc99530e84798b2ffd";
        String actual = requestMetadataParameter.getMd5();
        assertEquals(expected, actual);
    }

    @Test
    public void generateMd5Test() {
        String expected = "e1b3fab24c8af81c1aa13dbbb4e44ff0";
        String actual = requestMetadataParameter.generateMD5("storage/inputFiles/test.mp3");
        assertEquals(expected, actual);
    }

    @Test
    public void generateMd5WithInvalidFileTest() {
        String expected = "Could not get MD5 from input file.";
        String actual = requestMetadataParameter.generateMD5("invalidPath");
        assertEquals(expected, actual);
    }

    @Test
    public void nullFileValidationTest() {
        Throwable exception = Assertions.assertThrows(
                NullPointerException.class, () -> {
                    requestMetadataParameter.setExportFormat(".mp3");
                    requestMetadataParameter.setFile(null);
                    requestMetadataParameter.validate();
                }
        );
    }

    @Test
    public void emptyFileNameValidationTest() {
        File file = new File(" ");
        Throwable exception = Assertions.assertThrows(
                Exception.class, () -> {
                    MockMultipartFile mockFile = new MockMultipartFile("", "",
                            "audio/", "audio data".getBytes());
                    MockMultipartFile multipartFile = new MockMultipartFile(
                            "attachments",file.getName(),
                            MediaType.MULTIPART_FORM_DATA_VALUE,
                            mockFile.getInputStream());
                    requestMetadataParameter.setExportFormat(".mp3");
                    requestMetadataParameter.setFile(mockFile);
                    requestMetadataParameter.validate();
                }
        );
    }

    @Test
    public void md5ValidationTest() {
        Throwable exception = Assertions.assertThrows(
                Exception.class, () -> {
                    MockMultipartFile file = new MockMultipartFile("videoTest", "videoTest.mp4",
                            "video/", "video data".getBytes());
                    MockMultipartFile multipartFile = new MockMultipartFile(
                            "attachments",file.getName(),
                            MediaType.MULTIPART_FORM_DATA_VALUE,
                            file.getInputStream());
                    requestMetadataParameter.setExportFormat("t");
                    requestMetadataParameter.setDetail("d");
                    requestMetadataParameter.setFile(multipartFile);
                    requestMetadataParameter.setMd5("a1cbf54e11273fb97da300ec3dc57a86");
                    requestMetadataParameter.validate();
                }
        );
    }

    @Test
    public void CodecNullValidationTest() {
        Throwable exception = Assertions.assertThrows(
                Exception.class, () -> {
                    MockMultipartFile file = new MockMultipartFile("videoTest", "videoTest.mp4",
                            "video/", "video data".getBytes());
                    MockMultipartFile multipartFile = new MockMultipartFile(
                            "attachments",file.getName(),
                            MediaType.MULTIPART_FORM_DATA_VALUE,
                            file.getInputStream());
                    requestMetadataParameter.setExportFormat("j");
                    requestMetadataParameter.setDetail("v");
                    requestMetadataParameter.setFile(multipartFile);
                    requestMetadataParameter.setMd5("a1cbf54e11273fb97da300ec3dc57a87");
                    requestMetadataParameter.setFileName(null);
                    requestMetadataParameter.validate();
                }
        );
    }

    @Test
    public void validationTest() throws Exception {
        MockMultipartFile file = new MockMultipartFile("videoTest", "videoTest.mp4",
                "video/", "video data".getBytes());
        MockMultipartFile multipartFile = new MockMultipartFile(
                "attachments",file.getName(),
                MediaType.MULTIPART_FORM_DATA_VALUE,
                file.getInputStream());
        requestMetadataParameter.setExportFormat("t");
        requestMetadataParameter.setDetail("common");
        requestMetadataParameter.setFile(multipartFile);
        requestMetadataParameter.setMd5("a1cbf54e11273fb97da300ec3dc57a87");
        requestMetadataParameter.setFileName("file");
        requestMetadataParameter.validate();
    }
}