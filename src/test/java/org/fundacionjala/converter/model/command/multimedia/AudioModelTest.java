package org.fundacionjala.converter.model.command.multimedia;

import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.fundacionjala.converter.model.configPath.ConfigPath;
import org.fundacionjala.converter.model.parameter.multimedia.AudioParameter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AudioModelTest {
    @After
    public void clean() throws IOException {
        Files.deleteIfExists(Path.of("storage/convertedFiles/5ab69eb2751ceeb3120b8c369f5589c9.wav"));
    }

    @Test
    public void testCreateCommandReturnsAListOfCommand() throws IOException {
        AudioModel audioModel = new AudioModel();
        AudioParameter audioParameter = new AudioParameter();
        audioParameter.setInputFile("storage/inputFiles/audio.mp3");
        audioParameter.setOutputFile("storage/convertedFiles/");
        audioParameter.setCodec("libmp3lame");
        audioParameter.setChannel("1");
        audioParameter.setBitRate("32k");
        audioParameter.setFormat(".wav");
        audioParameter.setSampleRate("22050");
        audioParameter.setMd5("5ab69eb2751ceeb3120b8c369f5589c9");
        String expected = "[[" + ConfigPath.getVideoAudioTool() + ", -y, -i, storage/inputFiles/audio.mp3, -codec:a, libmp3lame, -b:a, 32k, -ac, 1, -ar, 22050, storage/convertedFiles/5ab69eb2751ceeb3120b8c369f5589c9.wav]]";
        List<List<String>> list = audioModel.createCommand(audioParameter);
        assertEquals(expected, list.toString());
    }
    @Test
    public void testCreateCommandReturnsOutputNameWithMd5() throws IOException {
        AudioModel audioModel = new AudioModel();
        AudioParameter audioParameter = new AudioParameter();
        audioParameter.setInputFile("storage/inputFiles/audio.mp3");
        audioParameter.setOutputFile("storage/convertedFiles/");
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
        audioParameter.setInputFile("storage/inputFiles/audio.mp3");
        audioParameter.setOutputFile("storage/convertedFiles/");
        audioParameter.setFormat(".mp3");
        audioParameter.setCut(true);
        audioParameter.setStart("00:00:30");
        audioParameter.setDuration("10");
        audioParameter.setSecondsToOutput("00:01:30");
        audioParameter.setName("converted");
        List<List<String>> list = audioModel.createCommand(audioParameter);
        assertEquals(2, list.size());
    }

    @Test
    public void testCreateCommandReturnsCommandForExtractMetadata() throws IOException {
        AudioModel audioModel = new AudioModel();
        AudioParameter audioParameter = new AudioParameter();
        audioParameter.setInputFile("storage/inputFiles/audio.mp3");
        audioParameter.setOutputFile("storage/convertedFiles/");
        audioParameter.setCodec("libmp3lame");
        audioParameter.setChannel("1");
        audioParameter.setBitRate("32k");
        audioParameter.setFormat(".wav");
        audioParameter.setSampleRate("22050");
        audioParameter.setExtractMetadata(true);
        List<List<String>> list = audioModel.createCommand(audioParameter);
        assertEquals(6, list.get(1).size());
    }
    @Test
    public void testCreateCommandReturnsFourCommandListForExtractMetadataAndCut() throws IOException {
        AudioModel audioModel = new AudioModel();
        AudioParameter audioParameter = new AudioParameter();
        audioParameter.setInputFile("storage/inputFiles/audio.mp3");
        audioParameter.setOutputFile("storage/convertedFiles/");
        audioParameter.setCodec("libmp3lame");
        audioParameter.setChannel("1");
        audioParameter.setBitRate("32k");
        audioParameter.setFormat(".wav");
        audioParameter.setSampleRate("22050");
        audioParameter.setCut(true);
        audioParameter.setStart("00:00:30");
        audioParameter.setDuration("10");
        audioParameter.setSecondsToOutput("00:01:30");
        audioParameter.setExtractMetadata(true);
        List<List<String>> list = audioModel.createCommand(audioParameter);
        assertEquals(4, list.size());
    }
}