package org.fundacionjala.converter.controller.request;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class RequestVideoParameterTest {

    RequestVideoParameter requestVideoParameter = new RequestVideoParameter();

    @Test
    void getVideoCodecTest() {
        requestVideoParameter.setVideoCodec("h264");
        String expected = "h264";
        String actual = requestVideoParameter.getVideoCodec();
        assertEquals(expected, actual);
    }

    @Test
    void getFramesTest() {
        requestVideoParameter.setFrames("21");
        String expected = "21";
        String actual = requestVideoParameter.getFrames();
        assertEquals(expected, actual);
    }

    @Test
    void getSecondsToOutputTest() {
        requestVideoParameter.setSecondsToOutput("10");
        String expected = "10";
        String actual = requestVideoParameter.getSecondsToOutput();
        assertEquals(expected, actual);
    }

    @Test
    void getControlLoopTest() {
        requestVideoParameter.setControlLoop("0");
        String expected = "0";
        String actual = requestVideoParameter.getControlLoop();
        assertEquals(expected, actual);
    }

    @Test
    void isExtractThumbnailTest() {
        requestVideoParameter.setExtractThumbnail(true);
        boolean actual = requestVideoParameter.isExtractThumbnail();
        assertTrue(actual);
    }

    @Test
    void getNameTest() {
        requestVideoParameter.setName("name");
        String expected = "name";
        String actual = requestVideoParameter.getName();
        assertEquals(expected, actual);
    }

    @Test
    void getCodecTest() {
        requestVideoParameter.setCodec("mp3");
        String expected = "mp3";
        String actual = requestVideoParameter.getCodec();
        assertEquals(expected, actual);
    }

    @Test
    void getStartTest() {
        requestVideoParameter.setStart("00:03:00");
        String expected = "00:03:00";
        String actual = requestVideoParameter.getStart();
        assertEquals(expected, actual);
    }

    @Test
    void getDurationTest() {
        requestVideoParameter.setDuration("5");
        String expected = "5";
        String actual = requestVideoParameter.getDuration();
        assertEquals(expected, actual);
    }

    @Test
    void isExtractMetadataTest() {
        requestVideoParameter.setExtractMetadata(true);
        boolean actual = requestVideoParameter.isExtractMetadata();
        assertTrue(actual);
    }

    @Test
    public void nullFormatTest() {
        Throwable exception = Assertions.assertThrows(
                Exception.class, () -> {
                    requestVideoParameter.setExportFormat(null);
                    requestVideoParameter.validate();
                }
        );
    }
    @Test
    public void emptyFormatTest() {
        Throwable exception = Assertions.assertThrows(
                Exception.class, () -> {
                    requestVideoParameter.setExportFormat(" ");
                    requestVideoParameter.validate();
                }
        );
    }
    @Test
    public void nullFileTest() {
        Throwable exception = Assertions.assertThrows(
                Exception.class, () -> {
                    requestVideoParameter.setExportFormat("mp4");
                    requestVideoParameter.setFile(null);
                    requestVideoParameter.validate();
                }
        );
    }
    @Test
    public void getMd5Test() {
        requestVideoParameter.setMd5("ffa5f5433efe74bc99530e84798b2ffd");
        String expected = "ffa5f5433efe74bc99530e84798b2ffd";
        String actual = requestVideoParameter.getMd5();
        assertEquals(expected, actual);
    }
    @Test
    public void generateMd5Test() {
        String expected = "e1b3fab24c8af81c1aa13dbbb4e44ff0";
        String actual = requestVideoParameter.generateMD5("storage/inputFiles/test.mp3");
        assertEquals(expected, actual);
    }
    @Test
    public void generateMd5WithInvalidFileTest() {
        String expected = "Could not get MD5 from input file.";
        String actual = requestVideoParameter.generateMD5("invalidPath");
        assertEquals(expected, actual);
    }
    @Test
    public void nullFileValidationTest() {
        Throwable exception = Assertions.assertThrows(
                NullPointerException.class, () -> {
                    requestVideoParameter.setExportFormat(".mp3");
                    requestVideoParameter.setFile(null);
                    requestVideoParameter.validate();
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
                    requestVideoParameter.setExportFormat(".mp3");
                    requestVideoParameter.setFile(mockFile);
                    requestVideoParameter.validate();
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
                    requestVideoParameter.setExportFormat(".mp3");
                    requestVideoParameter.setCodec("mp3");
                    requestVideoParameter.setFile(multipartFile);
                    requestVideoParameter.setMd5("a1cbf54e11273fb97da300ec3dc57a86");
                    requestVideoParameter.validate();
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
                    requestVideoParameter.setExportFormat(".mp3");
                    requestVideoParameter.setCodec("mp3");
                    requestVideoParameter.setFile(multipartFile);
                    requestVideoParameter.setMd5("a1cbf54e11273fb97da300ec3dc57a87");
                    requestVideoParameter.setCodec(null);
                    requestVideoParameter.validate();
                }
        );
    }

    @Test
    public void CodecEmptyValidationTest() {
        Throwable exception = Assertions.assertThrows(
                Exception.class, () -> {
                    MockMultipartFile file = new MockMultipartFile("videoTest", "videoTest.mp4",
                            "video/", "video data".getBytes());
                    MockMultipartFile multipartFile = new MockMultipartFile(
                            "attachments",file.getName(),
                            MediaType.MULTIPART_FORM_DATA_VALUE,
                            file.getInputStream());
                    requestVideoParameter.setExportFormat(".mp3");
                    requestVideoParameter.setCodec("mp3");
                    requestVideoParameter.setFile(multipartFile);
                    requestVideoParameter.setMd5("a1cbf54e11273fb97da300ec3dc57a87");
                    requestVideoParameter.setCodec("");
                    requestVideoParameter.validate();
                }
        );
    }

    @Test
    public void VideoCodecNullValidationTest() {
        Throwable exception = Assertions.assertThrows(
                Exception.class, () -> {
                    MockMultipartFile file = new MockMultipartFile("videoTest", "videoTest.mp4",
                            "video/", "video data".getBytes());
                    MockMultipartFile multipartFile = new MockMultipartFile(
                            "attachments",file.getName(),
                            MediaType.MULTIPART_FORM_DATA_VALUE,
                            file.getInputStream());
                    requestVideoParameter.setExportFormat(".mp3");
                    requestVideoParameter.setCodec("mp3");
                    requestVideoParameter.setFile(multipartFile);
                    requestVideoParameter.setMd5("a1cbf54e11273fb97da300ec3dc57a87");
                    requestVideoParameter.setCodec("mp3");
                    requestVideoParameter.setVideoCodec(null);
                    requestVideoParameter.validate();
                }
        );
    }

    @Test
    public void VideoCodecEmptyValidationTest() {
        Throwable exception = Assertions.assertThrows(
                Exception.class, () -> {
                    MockMultipartFile file = new MockMultipartFile("videoTest", "videoTest.mp4",
                            "video/", "video data".getBytes());
                    MockMultipartFile multipartFile = new MockMultipartFile(
                            "attachments",file.getName(),
                            MediaType.MULTIPART_FORM_DATA_VALUE,
                            file.getInputStream());
                    requestVideoParameter.setExportFormat(".mp3");
                    requestVideoParameter.setCodec("mp3");
                    requestVideoParameter.setFile(multipartFile);
                    requestVideoParameter.setMd5("a1cbf54e11273fb97da300ec3dc57a87");
                    requestVideoParameter.setCodec("mp3");
                    requestVideoParameter.setVideoCodec("");
                    requestVideoParameter.validate();
                }
        );
    }

    @Test
    public void VideoCodecInvalidValidationTest() {
        Throwable exception = Assertions.assertThrows(
                Exception.class, () -> {
                    MockMultipartFile file = new MockMultipartFile("videoTest", "videoTest.mp4",
                            "video/", "video data".getBytes());
                    MockMultipartFile multipartFile = new MockMultipartFile(
                            "attachments",file.getName(),
                            MediaType.MULTIPART_FORM_DATA_VALUE,
                            file.getInputStream());
                    requestVideoParameter.setExportFormat(".mp3");
                    requestVideoParameter.setCodec("mp3");
                    requestVideoParameter.setFile(multipartFile);
                    requestVideoParameter.setMd5("a1cbf54e11273fb97da300ec3dc57a87");
                    requestVideoParameter.setCodec("mp3");
                    requestVideoParameter.setVideoCodec("h26");
                    requestVideoParameter.validate();
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
        requestVideoParameter.setExportFormat(".mp3");
        requestVideoParameter.setCodec("mp3");
        requestVideoParameter.setFile(multipartFile);
        requestVideoParameter.setMd5("a1cbf54e11273fb97da300ec3dc57a87");
        requestVideoParameter.setCodec("mp3");
        requestVideoParameter.setVideoCodec("h264");
        requestVideoParameter.validate();
    }
}