package org.fundacionjala.converter.controller.request;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import java.io.File;

class RequestMultimediaParameterTest {

    RequestMultimediaParameter requestMultimediaParameter = new RequestMultimediaParameter();

    @Test
    void getNameTest() {
        requestMultimediaParameter.setName("name");
        String expected = "name";
        String actual = requestMultimediaParameter.getName();
        assertEquals(expected, actual);
    }

    @Test
    void getCodecTest() {
        requestMultimediaParameter.setCodec("mp3");
        String expected = "mp3";
        String actual = requestMultimediaParameter.getCodec();
        assertEquals(expected, actual);
    }

    @Test
    void getStartTest() {
        requestMultimediaParameter.setStart("00:03:00");
        String expected = "00:03:00";
        String actual = requestMultimediaParameter.getStart();
        assertEquals(expected, actual);
    }

    @Test
    void getDurationTest() {
        requestMultimediaParameter.setDuration("5");
        String expected = "5";
        String actual = requestMultimediaParameter.getDuration();
        assertEquals(expected, actual);
    }

    @Test
    void isExtractMetadataTest() {
        requestMultimediaParameter.setExtractMetadata(true);
        boolean actual = requestMultimediaParameter.isExtractMetadata();
        assertTrue(actual);
    }

    @Test
    public void nullFormatTest() {
        Throwable exception = Assertions.assertThrows(
                Exception.class, () -> {
                    requestMultimediaParameter.setExportFormat(null);
                    requestMultimediaParameter.validate();
                }
        );
    }
    @Test
    public void emptyFormatTest() {
        Throwable exception = Assertions.assertThrows(
                Exception.class, () -> {
                    requestMultimediaParameter.setExportFormat(" ");
                    requestMultimediaParameter.validate();
                }
        );
    }
    @Test
    public void nullFileTest() {
        Throwable exception = Assertions.assertThrows(
                Exception.class, () -> {
                    requestMultimediaParameter.setExportFormat("mp4");
                    requestMultimediaParameter.setFile(null);
                    requestMultimediaParameter.validate();
                }
        );
    }
    @Test
    public void getMd5Test() {
        requestMultimediaParameter.setMd5("ffa5f5433efe74bc99530e84798b2ffd");
        String expected = "ffa5f5433efe74bc99530e84798b2ffd";
        String actual = requestMultimediaParameter.getMd5();
        assertEquals(expected, actual);
    }
    @Test
    public void generateMd5Test() {
        String expected = "e1b3fab24c8af81c1aa13dbbb4e44ff0";
        String actual = requestMultimediaParameter.generateMD5("storage/inputFiles/test.mp3");
        assertEquals(expected, actual);
    }
    @Test
    public void generateMd5WithInvalidFileTest() {
        String expected = "Could not get MD5 from input file.";
        String actual = requestMultimediaParameter.generateMD5("invalidPath");
        assertEquals(expected, actual);
    }
    @Test
    public void nullFileValidationTest() {
        Throwable exception = Assertions.assertThrows(
                NullPointerException.class, () -> {
                    requestMultimediaParameter.setExportFormat(".mp3");
                    requestMultimediaParameter.setFile(null);
                    requestMultimediaParameter.validate();
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
                    requestMultimediaParameter.setExportFormat(".mp3");
                    requestMultimediaParameter.setFile(mockFile);
                    requestMultimediaParameter.validate();
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
                    requestMultimediaParameter.setExportFormat(".mp3");
                    requestMultimediaParameter.setCodec("mp3");
                    requestMultimediaParameter.setFile(multipartFile);
                    requestMultimediaParameter.setMd5("a1cbf54e11273fb97da300ec3dc57a86");
                    requestMultimediaParameter.validate();
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
                    requestMultimediaParameter.setExportFormat(".mp3");
                    requestMultimediaParameter.setCodec("mp3");
                    requestMultimediaParameter.setFile(multipartFile);
                    requestMultimediaParameter.setMd5("a1cbf54e11273fb97da300ec3dc57a87");
                    requestMultimediaParameter.setCodec(null);
                    requestMultimediaParameter.validate();
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
        requestMultimediaParameter.setExportFormat(".mp3");
        requestMultimediaParameter.setCodec("mp3");
        requestMultimediaParameter.setFile(multipartFile);
        requestMultimediaParameter.setMd5("a1cbf54e11273fb97da300ec3dc57a87");
        requestMultimediaParameter.setCodec("mp3");
        requestMultimediaParameter.validate();
    }
}