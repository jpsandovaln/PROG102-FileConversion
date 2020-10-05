package org.fundacionjala.converter.controller.request;

import org.fundacionjala.converter.model.commons.exception.InvalidDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;


public class RequestVideoParameterTest {
    RequestVideoParameter vRequest;
    String duration = "00:00:44";
    String start = "00:00:15";
    String secondsToOutput = "5";
    String controlLoop = "0";

    @BeforeEach
    public  void instantiate() throws IOException, NoSuchAlgorithmException {
        String name = "calculadora";
        String path = "storage/inputFiles/calculadora.mp4";
        InputStream is = getClass().getResourceAsStream(path);
        MultipartFile multipartFile = new MockMultipartFile(name, name + ".mp4", "", is);
        vRequest = new RequestVideoParameter();
        vRequest.setMd5("d41d8cd98f00b204e9800998ecf8427e");
        vRequest.setFile(multipartFile);
        vRequest.setExportFormat(".mp4");
    }
    @Test
    public void testSetVideoCodecNullThrowsInvalidDataException() throws Exception {
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
        vRequest.setExportFormat(".mov");
        vRequest.validate();
        assertEquals("mp3", vRequest.getCodec());
    }
}