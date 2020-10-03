package org.fundacionjala.converter.model.parameter.multimedia;

import org.fundacionjala.converter.model.commons.exception.InvalidDataException;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class AudioParameterTest {
    AudioParameter audioParameter = new AudioParameter();
    @Test
    public void testNullAudioCodec() throws IOException {
        audioParameter.setInputFile("storage/inputFiles/audio.mp3");
        audioParameter.setCodec("libmp3lame");
        audioParameter.setChannel("1");
        audioParameter.setBitRate("32k");
        audioParameter.setFormat(".wav");
        audioParameter.setSampleRate("22050");
        Exception exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    audioParameter.setCodec(null);
                    audioParameter.validate();
                }
        );
        assertEquals("Invalid audio codec", exception.getMessage());
    }
    @Test
    public void testEmptyAudioCodec() throws IOException {
        audioParameter.setInputFile("storage/inputFiles/audio.mp3");
        audioParameter.setCodec("libmp3lame");
        audioParameter.setChannel("1");
        audioParameter.setBitRate("32k");
        audioParameter.setFormat(".wav");
        audioParameter.setSampleRate("22050");
        Exception exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    audioParameter.setCodec("");
                    audioParameter.validate();
                }
        );
        assertEquals("Invalid audio codec", exception.getMessage());
    }
    @Test
    public void testNotExistCodec() throws IOException {
        audioParameter.setInputFile("storage/inputFiles/audio.mp3");
        audioParameter.setCodec("libmp3lame");
        audioParameter.setChannel("1");
        audioParameter.setBitRate("32k");
        audioParameter.setFormat(".wav");
        audioParameter.setSampleRate("22050");
        Exception exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    audioParameter.setCodec("123");
                    audioParameter.validate();
                }
        );
        assertEquals("Invalid audio codec", exception.getMessage());
    }
    @Test
    public void testNullAudioFormat() throws IOException {
        audioParameter.setInputFile("storage/inputFiles/audio.mp3");
        audioParameter.setCodec("libmp3lame");
        audioParameter.setChannel("1");
        audioParameter.setBitRate("32k");
        audioParameter.setFormat(".wav");
        audioParameter.setSampleRate("22050");
        Exception exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    audioParameter.setFormat(null);
                    audioParameter.validate();
                }
        );
        assertEquals("Invalid audio format", exception.getMessage());
    }
    @Test
    public void testEmptyAudioFormat() throws IOException {
        audioParameter.setInputFile("storage/inputFiles/audio.mp3");
        audioParameter.setCodec("libmp3lame");
        audioParameter.setChannel("1");
        audioParameter.setBitRate("32k");
        audioParameter.setFormat(".wav");
        audioParameter.setSampleRate("22050");
        Exception exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    audioParameter.setFormat("");
                    audioParameter.validate();
                }
        );
        assertEquals("Invalid audio format", exception.getMessage());
    }
    @Test
    public void testNotExistFormat() throws IOException {
        audioParameter.setInputFile("storage/inputFiles/audio.mp3");
        audioParameter.setCodec("libmp3lame");
        audioParameter.setChannel("1");
        audioParameter.setBitRate("32k");
        audioParameter.setFormat(".wav");
        audioParameter.setSampleRate("22050");
        Exception exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    audioParameter.setFormat("rttyt");
                    audioParameter.validate();
                }
        );
        assertEquals("Invalid audio format", exception.getMessage());
    }
    @Test
    public void testNullAudioBitRate() throws IOException {
        audioParameter.setInputFile("storage/inputFiles/audio.mp3");
        audioParameter.setCodec("libmp3lame");
        audioParameter.setChannel("1");
        audioParameter.setBitRate("32k");
        audioParameter.setFormat(".wav");
        audioParameter.setSampleRate("22050");
        Exception exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    audioParameter.setBitRate(null);
                    audioParameter.validate();
                }
        );
        assertEquals("Invalid audio bit rate", exception.getMessage());
    }
    @Test
    public void testEmptyAudioBitRate() throws IOException {
        audioParameter.setInputFile("storage/inputFiles/audio.mp3");
        audioParameter.setCodec("libmp3lame");
        audioParameter.setChannel("1");
        audioParameter.setBitRate("32k");
        audioParameter.setFormat(".wav");
        audioParameter.setSampleRate("22050");
        Exception exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    audioParameter.setBitRate(" ");
                    audioParameter.validate();
                }
        );
        assertEquals("Invalid audio bit rate", exception.getMessage());
    }
    @Test
    public void testNotExistBitRate() throws IOException {
        audioParameter.setInputFile("storage/inputFiles/audio.mp3");
        audioParameter.setCodec("libmp3lame");
        audioParameter.setChannel("1");
        audioParameter.setBitRate("32k");
        audioParameter.setFormat(".wav");
        audioParameter.setSampleRate("22050");
        Exception exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    audioParameter.setBitRate("123k");
                    audioParameter.validate();
                }
        );
        assertEquals("Invalid audio bit rate", exception.getMessage());
    }

    @Test
    public void testNullAudioChannel() throws IOException {
        audioParameter.setInputFile("storage/inputFiles/audio.mp3");
        audioParameter.setCodec("libmp3lame");
        audioParameter.setChannel("1");
        audioParameter.setBitRate("32k");
        audioParameter.setFormat(".wav");
        audioParameter.setSampleRate("22050");
        Exception exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    audioParameter.setChannel(null);
                    audioParameter.validate();
                }
        );
        assertEquals("Invalid audio channel", exception.getMessage());
    }
    @Test
    public void testEmptyAudioChannel() throws IOException {
        audioParameter.setInputFile("storage/inputFiles/audio.mp3");
        audioParameter.setCodec("libmp3lame");
        audioParameter.setChannel("1");
        audioParameter.setBitRate("32k");
        audioParameter.setFormat(".wav");
        audioParameter.setSampleRate("22050");
        Exception exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    audioParameter.setChannel(" ");
                    audioParameter.validate();
                }
        );
        assertEquals("Invalid audio channel", exception.getMessage());
    }
    @Test
    public void testNotExistChannel() throws IOException {
        audioParameter.setInputFile("storage/inputFiles/audio.mp3");
        audioParameter.setCodec("libmp3lame");
        audioParameter.setChannel("1");
        audioParameter.setBitRate("32k");
        audioParameter.setFormat(".wav");
        audioParameter.setSampleRate("22050");
        Exception exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    audioParameter.setChannel("100");
                    audioParameter.validate();
                }
        );
        assertEquals("Invalid audio channel", exception.getMessage());
    }

    @Test
    public void testNullAudioSampleRate() throws IOException {
        audioParameter.setInputFile("storage/inputFiles/audio.mp3");
        audioParameter.setCodec("libmp3lame");
        audioParameter.setChannel("1");
        audioParameter.setBitRate("32k");
        audioParameter.setFormat(".wav");
        audioParameter.setSampleRate("22050");
        Exception exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    audioParameter.setSampleRate(null);
                    audioParameter.validate();
                }
        );
        assertEquals("Invalid sample rate", exception.getMessage());
    }
    @Test
    public void testEmptyAudioSampleRate() throws IOException {
        audioParameter.setInputFile("storage/inputFiles/audio.mp3");
        audioParameter.setCodec("libmp3lame");
        audioParameter.setChannel("1");
        audioParameter.setBitRate("32k");
        audioParameter.setFormat(".wav");
        audioParameter.setSampleRate("22050");
        Exception exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    audioParameter.setSampleRate(" ");
                    audioParameter.validate();
                }
        );
        assertEquals("Invalid sample rate", exception.getMessage());
    }
    @Test
    public void testNotExistSampleRate() throws IOException {
        audioParameter.setInputFile("storage/inputFiles/audio.mp3");
        audioParameter.setCodec("libmp3lame");
        audioParameter.setChannel("1");
        audioParameter.setBitRate("32k");
        audioParameter.setFormat(".wav");
        audioParameter.setSampleRate("22050");
        Exception exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    audioParameter.setSampleRate("100");
                    audioParameter.validate();
                }
        );
        assertEquals("Invalid sample rate", exception.getMessage());
    }

    @Test
    public void testNullAudioInputFile() throws IOException {
        audioParameter.setInputFile("storage/inputFiles/audio.mp3");
        audioParameter.setCodec("libmp3lame");
        audioParameter.setChannel("1");
        audioParameter.setBitRate("32k");
        audioParameter.setFormat(".wav");
        audioParameter.setSampleRate("22050");
        Exception exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    audioParameter.setInputFile(null);
                    audioParameter.validate();
                }
        );
        assertEquals("the file is mull", exception.getMessage());
    }
    @Test
    public void testEmptyAudioInputFile() throws IOException {
        audioParameter.setInputFile("storage/inputFiles/audio.mp3");
        audioParameter.setCodec("libmp3lame");
        audioParameter.setChannel("1");
        audioParameter.setBitRate("32k");
        audioParameter.setFormat(".wav");
        audioParameter.setSampleRate("22050");
        Exception exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    audioParameter.setInputFile("");
                    audioParameter.validate();
                }
        );
        assertEquals("the file is mull", exception.getMessage());
    }
    @Test
    public void testNotExistFile() throws IOException {
        audioParameter.setInputFile("storage/inputFiles/audio.mp3");
        audioParameter.setCodec("libmp3lame");
        audioParameter.setChannel("1");
        audioParameter.setBitRate("32k");
        audioParameter.setFormat(".wav");
        audioParameter.setSampleRate("22050");
        Exception exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    audioParameter.setInputFile("home/audio.mp3");
                    audioParameter.validate();
                }
        );
        assertEquals("the file or path does not exist", exception.getMessage());
    }
    @Test
    public void testGetFields() throws IOException {
        audioParameter.setInputFile("storage/inputFiles/audio.mp3");
        assertEquals("storage/inputFiles/audio.mp3", audioParameter.getInputFile());
        audioParameter.setCodec("libmp3lame");
        assertEquals("libmp3lame", audioParameter.getCodec());
        audioParameter.setChannel("1");
        assertEquals("1", audioParameter.getChannel());
        audioParameter.setBitRate("32k");
        assertEquals("32k", audioParameter.getBitRate());
        audioParameter.setFormat(".wav");
        assertEquals(".wav", audioParameter.getFormat());
        audioParameter.setSampleRate("22050");
        assertEquals("22050", audioParameter.getSampleRate());
        audioParameter.setOutputFile("storage/outputFiles/");
        assertEquals("storage/outputFiles/", audioParameter.getOutputFile());
        audioParameter.setStart("00:00:30");
        assertEquals("00:00:30", audioParameter.getStart());
        audioParameter.setDuration("00:00:10");
        assertEquals("00:00:10", audioParameter.getDuration());
        audioParameter.setCut(true);
        assertTrue(audioParameter.getIsCut());
        audioParameter.setExtractMetadata(true);
        assertTrue(audioParameter.isExtractMetadata());
    }
}