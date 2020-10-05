package org.fundacionjala.converter.controller.request;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import org.fundacionjala.converter.model.commons.exception.InvalidDataException;
import org.springframework.web.multipart.MultipartFile;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.InputStream;


public class RequestVideoParameterTest {

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
    @Test
    public void testSetVideoCodecNullThrowsInvalidDataException() throws Exception {
        String name = "calculadora";
        String path = "storage/inputFiles/calculadora.mp4";
        InputStream is = getClass().getResourceAsStream(path);
        MultipartFile multipartFile = new MockMultipartFile(name, name + ".mp4", "", is);
        RequestVideoParameter vRequest = new RequestVideoParameter();
        vRequest.setMd5("d41d8cd98f00b204e9800998ecf8427e");
        vRequest.setFile(multipartFile);
        vRequest.setExportFormat(".mp4");
        vRequest.setCodec("mp3");
        try {
            vRequest.setVideoCodec(null);
            vRequest.validate();
        }
        catch (InvalidDataException e){
            assertThat(e.getMessage(), is("Invalid data on field = videoCodec"));
        }
    }
    @Test 
    public void testSetAudioCodecNullThrowsInvalidDataException() throws Exception {
        String name = "calculadora";
        String path = "storage/inputFiles/calculadora.mp4";
        InputStream is = getClass().getResourceAsStream(path);
        MultipartFile multipartFile = new MockMultipartFile(name, name + ".mp4", "", is);
        RequestVideoParameter vRequest = new RequestVideoParameter();
        vRequest.setMd5("d41d8cd98f00b204e9800998ecf8427e");
        vRequest.setFile(multipartFile);
        vRequest.setExportFormat(".mp4");
        vRequest.setExportFormat(".mp4");
        vRequest.setVideoCodec("h264");
        try{
            vRequest.setCodec(null);
            vRequest.validate();
            fail();
        }
        catch (Exception e){
            assertThat(e.getMessage(), is("Failed, empty audioCodec"));
        }
    }
    @Test 
    public void testSetFormatInvalidThrowsInvalidDataException() throws Exception {
        String name = "calculadora";
        String path = "storage/inputFiles/calculadora.mp4";
        InputStream is = getClass().getResourceAsStream(path);
        MultipartFile multipartFile = new MockMultipartFile(name, name + ".mp4", "", is);
        RequestVideoParameter vRequest = new RequestVideoParameter();
        vRequest.setMd5("d41d8cd98f00b204e9800998ecf8427e");
        vRequest.setFile(multipartFile);
        vRequest.setExportFormat(".mp4");
        vRequest.setCodec("mp3");
        vRequest.setVideoCodec("h264");
        try{
            
            vRequest.setExportFormat("libx264");
            vRequest.validate();
        }
        catch (InvalidDataException e){
            assertThat(e.getMessage(), is("Invalid format"));
        }
    }
    @Test 
    public void testSetVideoCodecInvalidThrowsInvalidDataException() throws Exception {
        String name = "calculadora";
        String path = "storage/inputFiles/calculadora.mp4";
        InputStream is = getClass().getResourceAsStream(path);
        MultipartFile multipartFile = new MockMultipartFile(name, name + ".mp4", "", is);
        RequestVideoParameter vRequest = new RequestVideoParameter();
        vRequest.setMd5("d41d8cd98f00b204e9800998ecf8427e");
        vRequest.setFile(multipartFile);
        vRequest.setExportFormat(".mp4");
        try{
            vRequest.setVideoCodec("dgsd");
            vRequest.validate();
            fail();
        }
        catch (InvalidDataException e){
            assertThat(e.getMessage(), is("Invalid codec"));
        }
    }
    @Test 
    public void testSetCodecInvalidThrowsInvalidDataException() throws Exception {
        String name = "calculadora";
        String path = "storage/inputFiles/calculadora.mp4";
        InputStream is = getClass().getResourceAsStream(path);
        MultipartFile multipartFile = new MockMultipartFile(name, name + ".mp4", "", is);
        RequestVideoParameter vRequest = new RequestVideoParameter();
        vRequest.setMd5("d41d8cd98f00b204e9800998ecf8427e");
        vRequest.setFile(multipartFile);
        vRequest.setExportFormat(".mp4");
        vRequest.setVideoCodec("h264");
        try{
            vRequest.setCodec("mov");
            vRequest.validate();
        }
        catch (InvalidDataException e){
            assertThat(e.getMessage(), is("Invalid codec"));
        }
    }
    @Test 
    public void testSetFramesNullThrowsException() throws Exception {
        String start = "00:00:15";
        String secondsToOutput = "5";
        String controlLoop = "0";
        String duration = "00:00:44";
        String name = "calculadora";
        String path = "storage/inputFiles/calculadora.mp4";
        InputStream is = getClass().getResourceAsStream(path);
        MultipartFile multipartFile = new MockMultipartFile(name, name + ".mp4", "", is);
        RequestVideoParameter vRequest = new RequestVideoParameter();
        vRequest.setMd5("d41d8cd98f00b204e9800998ecf8427e");
        vRequest.setFile(multipartFile);
        vRequest.setExportFormat(".gif");
        vRequest.setVideoCodec("h264");
        vRequest.setDuration(duration);
        vRequest.setStart(start);
        vRequest.setSecondsToOutput(secondsToOutput);
        vRequest.setControlLoop(controlLoop);
        try{
            vRequest.setFrames(null);
            vRequest.validate();
            fail();
        }
        catch (Exception e){
            assertThat(e.getMessage(), is("Invalid data on field = frames"));
        }
    }
    @Test 
    public void testSetControlLoopInvalidThrowsInvalidDataException() throws Exception {
        String name = "calculadora";
        String path = "storage/inputFiles/calculadora.mp4";
        String start = "00:00:15";
        String secondsToOutput = "5";
        String duration = "00:00:44";
        InputStream is = getClass().getResourceAsStream(path);
        MultipartFile multipartFile = new MockMultipartFile(name, name + ".mp4", "", is);
        RequestVideoParameter vRequest = new RequestVideoParameter();
        vRequest.setMd5("d41d8cd98f00b204e9800998ecf8427e");
        vRequest.setFile(multipartFile);
        vRequest.setExportFormat(".mp4");
        vRequest.setExportFormat(".gif");
        vRequest.setVideoCodec("h264");
        vRequest.setCodec("mp3");
        vRequest.setFrames("21");
        vRequest.setDuration(duration);
        vRequest.setStart(start);
        vRequest.setSecondsToOutput(secondsToOutput);
        try{
            vRequest.setControlLoop("2");
            vRequest.validate();
        }
        catch (InvalidDataException e){
            assertThat(e.getMessage(), is("Invalid value of controlLoop"));
        }
    }
    @Test
    public void testWhenFormatIsMovCodecMp3IsSetted() throws InvalidDataException, Exception {
        String name = "calculadora";
        String path = "storage/inputFiles/calculadora.mp4";
        InputStream is = getClass().getResourceAsStream(path);
        MultipartFile multipartFile = new MockMultipartFile(name, name + ".mp4", "", is);
        RequestVideoParameter vRequest = new RequestVideoParameter();
        vRequest.setMd5("d41d8cd98f00b204e9800998ecf8427e");
        vRequest.setFile(multipartFile);
        vRequest.setExportFormat(".mp4");
        vRequest.setExportFormat(".mov");
        vRequest.validate();
        assertEquals("mp3", vRequest.getCodec());
    }
}