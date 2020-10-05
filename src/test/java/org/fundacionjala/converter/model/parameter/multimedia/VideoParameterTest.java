package org.fundacionjala.converter.model.parameter.multimedia;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import org.fundacionjala.converter.model.commons.exception.InvalidDataException;

public class VideoParameterTest {

    VideoParameter parameter;
    String namename = "calculadora";
    String inputFile = "storage/inputFiles/calculadora.mp4";;
    String md5 = "d41d8cd98f00b204e9800998ecf8427e";
    String duration = "00:00:44";
    String start = "00:00:15";
    String secondsToOutput = "5";
    String controlLoop = "0";

    @BeforeEach
    public void instantiate() throws NullPointerException, IOException {
        parameter =  new VideoParameter();
        parameter.setMd5(md5);
        parameter.setInputFile(inputFile);
    }
    @Test 
    public void testSetVideoCodecNullThrowsInvalidDataException() {
        parameter.setFormat(".mp4");
        parameter.setCodec("mp3");
        try{
            parameter.setVideoCodec(null);
            parameter.validate();
            fail();
        }
        catch (InvalidDataException e){
            assertThat(e.getMessage(), is("Invalid data on field = videoCodec"));
        }
    }
    @Test 
    public void testSetAudioCodecNullThrowsInvalidDataException() {
        parameter.setFormat(".mp4");
        parameter.setVideoCodec("h264");
        try{
            parameter.setCodec(null);
            parameter.validate();
            fail();
        }
        catch (InvalidDataException e){
            assertThat(e.getMessage(), is("Invalid data on field = audioCodec"));
        }
    }
    @Test 
    public void testSetFormatInvalidThrowsInvalidDataException() {
        parameter.setCodec("mp3");
        parameter.setVideoCodec("h264");
        try{
            
            parameter.setFormat("libx264");
            parameter.validate();
            fail();
        }
        catch (InvalidDataException e){
            assertThat(e.getMessage(), is("Invalid format"));
        }
    }
    @Test 
    public void testSetVideoCodecInvalidThrowsInvalidDataException() {
        parameter.setFormat(".mp4");
        try{
            parameter.setVideoCodec("dgsd");
            parameter.validate();
            fail();
        }
        catch (InvalidDataException e){
            assertThat(e.getMessage(), is("Invalid codec"));
        }
    }
    @Test 
    public void testSetCodecInvalidThrowsInvalidDataException() {
        parameter.setFormat(".mp4");
        parameter.setVideoCodec("h264");
        try{
            parameter.setCodec("mov");
            parameter.validate();
            fail();
        }
        catch (InvalidDataException e){
            assertThat(e.getMessage(), is("Invalid codec"));
        }
    }
    @Test 
    public void testSetFramesInvalidThrowsNotNullOrEmptyException() {
        parameter.setFormat(".gif");
        parameter.setVideoCodec("h264");
        parameter.setDuration(duration);
        parameter.setStart(start);
        parameter.setSecondsToOutput(secondsToOutput);
        parameter.setControlLoop(controlLoop);
        try{
            parameter.setFrames(null);
            parameter.validate();
        }
        catch (InvalidDataException e){
            assertThat(e.getMessage(), is("Invalid data on field = frames"));
        }
    }
    @Test 
    public void testSetControlLoopInvalidThrowsInvalidDataException() {
        parameter.setFormat(".gif");
        parameter.setVideoCodec("h264");
        parameter.setFrames("21");
        parameter.setDuration(duration);
        parameter.setStart(start);
        parameter.setSecondsToOutput(secondsToOutput);
        try{
            parameter.setControlLoop("2");
            parameter.validate();
        }
        catch (InvalidDataException e){
            assertThat(e.getMessage(), is("Invalid value of controlLoop"));
        }
    }
}
