package org.fundacionjala.converter.model.command.multimedia;

import org.junit.jupiter.api.Test;

import org.fundacionjala.converter.model.parameter.multimedia.AudioParameter;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AudioModelTest {
   @Test
    public void testCreateCommandReturnsAListOfCommand() throws IOException {
        AudioModel audioModel = new AudioModel();
        AudioParameter audioParameter = new AudioParameter();
        audioParameter.setInputFile("storage/inputFiles/MiEternoAmorSecreto.mp3");
        audioParameter.setOutputFile("storage/convertedFiles");
        audioParameter.setCodec("libmp3lame");
        audioParameter.setChannel("1");
        audioParameter.setBitRate("32k");
        audioParameter.setFormat(".wav");
        audioParameter.setSampleRate("22050");
        audioParameter.setMd5("5ab69eb2751ceeb3120b8c369f5589c9");
        List<List<String>> list = audioModel.createCommand(audioParameter);
        assertEquals(13, list.get(0).size());
        System.out.println(list.get(0));
    }
    @Test
    public void testCreateCommandReturnsOutputNameWithMd5() throws IOException {
        AudioModel audioModel = new AudioModel();
        AudioParameter audioParameter = new AudioParameter();
        audioParameter.setInputFile("storage/inputFiles/MiEternoAmorSecreto.mp3");
        audioParameter.setOutputFile("storage/convertedFiles");
        audioParameter.setCodec("libmp3lame");
        audioParameter.setChannel("1");
        audioParameter.setBitRate("32k");
        audioParameter.setFormat(".wav");
        audioParameter.setSampleRate("22050");
        String expected = "storage/convertedFiles/5ab69eb2751ceeb3120b8c369f5589c9.wav";
        List<List<String>> list = audioModel.createCommand(audioParameter);
        int index = list.get(0).size();
        String result = list.get(0).get(index-1);
        assertEquals(expected, result);
    }

    @Test
    public void testCreateCommandReturnsCommandForCutIsTrue() throws IOException {
        AudioModel audioModel = new AudioModel();
        AudioParameter audioParameter = new AudioParameter();
        audioParameter.setInputFile("storage/inputFiles/MiEternoAmorSecreto.mp3");
        audioParameter.setFormat(".mp3");
        audioParameter.setCut(true);
        audioParameter.setStart("00:00:30");
        audioParameter.setDuration("00:00:10");
        String expected = "[thirdParty/ffmpeg/bin/ffmpeg.exe, -y, -i, storage/inputFiles/MiEternoAmorSecreto.mp3, -ss, 00:00:30, -t, 00:00:10, storage/convertedFiles/5ab69eb2751ceeb3120b8c369f5589c9_cut.mp3]";
        List<List<String>> list = audioModel.createCommand(audioParameter);
        assertEquals(expected, list.get(1).toString());
    }

    @Test
    public void testCreateCommandReturnsCommandForExtractMetadata() throws IOException {
        AudioModel audioModel = new AudioModel();
        AudioParameter audioParameter = new AudioParameter();
        audioParameter.setInputFile("storage/inputFiles/MiEternoAmorSecreto.mp3");
        audioParameter.setOutputFile("storage/convertedFiles");
        audioParameter.setCodec("libmp3lame");
        audioParameter.setChannel("1");
        audioParameter.setBitRate("32k");
        audioParameter.setFormat(".wav");
        audioParameter.setSampleRate("22050");
        audioParameter.setExtractMetadata(true);
        List<List<String>> list = audioModel.createCommand(audioParameter);
        System.out.println(list.get(1));
        assertEquals(6, list.get(1).size());
    }
    @Test
    public void testCreateCommandReturnsFourCommandListForExtractMetadataAndCut() throws IOException {
        AudioModel audioModel = new AudioModel();
        AudioParameter audioParameter = new AudioParameter();
        audioParameter.setInputFile("storage/inputFiles/MiEternoAmorSecreto.mp3");
        audioParameter.setOutputFile("storage/convertedFiles");
        audioParameter.setCodec("libmp3lame");
        audioParameter.setChannel("1");
        audioParameter.setBitRate("32k");
        audioParameter.setFormat(".wav");
        audioParameter.setSampleRate("22050");
        audioParameter.setCut(true);
        audioParameter.setStart("00:00:30");
        audioParameter.setDuration("00:00:10");
        audioParameter.setExtractMetadata(true);
        List<List<String>> list = audioModel.createCommand(audioParameter);
        System.out.println(list.size());
        assertEquals(4, list.size());
    }
}