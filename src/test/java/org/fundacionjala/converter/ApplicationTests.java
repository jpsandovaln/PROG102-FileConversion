package org.fundacionjala.converter;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

class ApplicationTests {

    @Test
    public void MetadataConverterTest() throws InterruptedException, ExecutionException, IOException {
        //This method was commented because exiftool is not present at GitHub repository, so Test failed for that reason.
        /*MetadataParameter mp = new MetadataParameter("storage\\inputFiles\\aud.mp3", "j", "v", "meta", "abcabcabc");
        Executor e = new Executor();
        MetadataModel mm = new MetadataModel();
        String result = e.executeCommandsList(mm.createCommand(mp)).toString();
        String expected = "[E:\\Workspace\\PROG102\\Project\\PROG102-FileConversion\\storage\\convertedFiles\\meta.json]";
        Assert.assertEquals(result, expected);*/
    }

    @Test
    public void AudioTest() throws InterruptedException, ExecutionException, IOException {
        /*AudioParameter ap = new AudioParameter();
        Executor e = new Executor();
        AudioModel am = new AudioModel();
        ap.setInputFile("storage\\inputFiles\\aud.mp3");
        ap.setOutputFile("storage\\convertedFiles\\");
        ap.setName("convertido");
        ap.setFormat(".wav");
        ap.setCodec("libmp3lame");
        ap.setBitRate("128k"); // -b:a
        ap.setChannel("1"); // stereo
        ap.setSampleRate("22050"); //-ar
        ap.setStart("00:01:50"); //-ss
        ap.setDuration("10"); //-t
        ap.setCut(true);
        ap.setMetadata(true);
        System.out.println(e.executeCommandsList(am.createCommand(ap)));*/
    }
}
