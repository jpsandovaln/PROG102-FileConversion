package org.fundacionjala.converter.model.parameter.multimedia;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.containsString;
import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.fundacionjala.converter.model.commons.exception.InvalidDataException;

public class VideoParameterTest {

    VideoParameter parameter =  new VideoParameter();

    @Test 
    public void testSetVideoCodecFileNullThrowsInvalidDataException() {
        try{
            parameter.setVideoCodec(null);
            parameter.validate();
            fail();
        }
        catch (InvalidDataException e){
            assertThat(e.getMessage(), is("Invalid codec"));
        }
    }
    @Test 
    public void testSetAudioCodecNullThrowsInvalidDataException() {
        try{
            parameter.setCodec(null);
            parameter.validate();
            fail();
        }
        catch (InvalidDataException e){
            assertThat(e.getMessage(), is("Invalid codec"));
        }
    }
    @Test 
    public void testSetFormatNullThrowsInvalidDataException() {
        try{
            parameter.setCodec("mp3");
            parameter.setVideoCodec("h264");
            parameter.setFormat(null);
            parameter.validate();
            fail();
        }
        catch (InvalidDataException e){
            assertThat(e.getMessage(), is("Invalid data on field = format"));
        }
    }
    @Test 
    public void testSetFormatInvalidThrowsInvalidDataException() {
        try{
            parameter.setCodec("mp3");
            parameter.setVideoCodec("h264");
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
        try{
            parameter.setCodec("libmp3");
            parameter.validate();
            fail();
        }
        catch (InvalidDataException e){
            assertThat(e.getMessage(), is("Invalid codec"));
        }
    }
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testSetFramesNullThrowsNullPointerException() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(containsString("is null"));
        parameter.setSecondsToOutput(null);
    }
}