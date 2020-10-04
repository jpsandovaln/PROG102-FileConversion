package org.fundacionjala.converter.controller.request;

import org.fundacionjala.converter.model.commons.exception.InvalidDataException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class RequestAudioParameterTest {
    RequestAudioParameter requestAudioParameter = new RequestAudioParameter();
    @Test
    public void testNullAudioCodec()  {
        requestAudioParameter.setCodec("libmp3lame");
        requestAudioParameter.setChannel("1");
        requestAudioParameter.setBitRate("32k");
        requestAudioParameter.setExportFormat(".wav");
        requestAudioParameter.setSampleRate("22050");
        requestAudioParameter.setStart("00:00:30");
        requestAudioParameter.setDuration("00:00:10");
        Exception exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    requestAudioParameter.setCodec(null);
                    requestAudioParameter.validate();
                }
        );
        assertEquals("Invalid audio codec", exception.getMessage());
    }
    @Test
    public void testEmptyAudioCodec() {
        requestAudioParameter.setCodec("libmp3lame");
        requestAudioParameter.setChannel("1");
        requestAudioParameter.setBitRate("32k");
        requestAudioParameter.setExportFormat(".wav");
        requestAudioParameter.setSampleRate("22050");
        requestAudioParameter.setStart("00:00:30");
        requestAudioParameter.setDuration("00:00:10");
        Exception exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    requestAudioParameter.setCodec("");
                    requestAudioParameter.validate();
                }
        );
        assertEquals("Invalid audio codec", exception.getMessage());
    }
    @Test
    public void testNotExistCodec() {
        requestAudioParameter.setCodec("libmp3lame");
        requestAudioParameter.setChannel("1");
        requestAudioParameter.setBitRate("32k");
        requestAudioParameter.setExportFormat(".wav");
        requestAudioParameter.setSampleRate("22050");
        requestAudioParameter.setStart("00:00:30");
        requestAudioParameter.setDuration("00:00:10");
        Exception exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    requestAudioParameter.setCodec("123");
                    requestAudioParameter.validate();
                }
        );
        assertEquals("Invalid audio codec", exception.getMessage());
    }
    @Test
    public void testNullAudioFormat() {
        requestAudioParameter.setCodec("libmp3lame");
        requestAudioParameter.setChannel("1");
        requestAudioParameter.setBitRate("32k");
        requestAudioParameter.setExportFormat(".wav");
        requestAudioParameter.setSampleRate("22050");
        requestAudioParameter.setStart("00:00:30");
        requestAudioParameter.setDuration("00:00:10");
        Exception exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    requestAudioParameter.setExportFormat(null);
                    requestAudioParameter.validate();
                }
        );
        assertEquals("Invalid audio format", exception.getMessage());
    }
    @Test
    public void testEmptyAudioFormat() {
        requestAudioParameter.setCodec("libmp3lame");
        requestAudioParameter.setChannel("1");
        requestAudioParameter.setBitRate("32k");
        requestAudioParameter.setExportFormat(".wav");
        requestAudioParameter.setSampleRate("22050");
        requestAudioParameter.setStart("00:00:30");
        requestAudioParameter.setDuration("00:00:10");
        Exception exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    requestAudioParameter.setExportFormat("");
                    requestAudioParameter.validate();
                }
        );
        assertEquals("Invalid audio format", exception.getMessage());
    }
    @Test
    public void testNotExistFormat()  {
        requestAudioParameter.setCodec("libmp3lame");
        requestAudioParameter.setChannel("1");
        requestAudioParameter.setBitRate("32k");
        requestAudioParameter.setExportFormat(".wav");
        requestAudioParameter.setSampleRate("22050");
        requestAudioParameter.setStart("00:00:30");
        requestAudioParameter.setDuration("00:00:10");
        Exception exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    requestAudioParameter.setExportFormat("rttyt");
                    requestAudioParameter.validate();
                }
        );
        assertEquals("Invalid audio format", exception.getMessage());
    }
    @Test
    public void testNullAudioBitRate()  {
        requestAudioParameter.setCodec("libmp3lame");
        requestAudioParameter.setChannel("1");
        requestAudioParameter.setBitRate("32k");
        requestAudioParameter.setExportFormat(".wav");
        requestAudioParameter.setSampleRate("22050");
        requestAudioParameter.setStart("00:00:30");
        requestAudioParameter.setDuration("00:00:10");
        Exception exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    requestAudioParameter.setBitRate(null);
                    requestAudioParameter.validate();
                }
        );
        assertEquals("Invalid audio bit rate", exception.getMessage());
    }
    @Test
    public void testEmptyAudioBitRate() {
        requestAudioParameter.setCodec("libmp3lame");
        requestAudioParameter.setChannel("1");
        requestAudioParameter.setBitRate("32k");
        requestAudioParameter.setExportFormat(".wav");
        requestAudioParameter.setSampleRate("22050");
        requestAudioParameter.setStart("00:00:30");
        requestAudioParameter.setDuration("00:00:10");
        Exception exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    requestAudioParameter.setBitRate(" ");
                    requestAudioParameter.validate();
                }
        );
        assertEquals("Invalid audio bit rate", exception.getMessage());
    }
    @Test
    public void testNotExistBitRate() {
        requestAudioParameter.setCodec("libmp3lame");
        requestAudioParameter.setChannel("1");
        requestAudioParameter.setBitRate("32k");
        requestAudioParameter.setExportFormat(".wav");
        requestAudioParameter.setSampleRate("22050");
        requestAudioParameter.setStart("00:00:30");
        requestAudioParameter.setDuration("00:00:10");
        Exception exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    requestAudioParameter.setBitRate("123k");
                    requestAudioParameter.validate();
                }
        );
        assertEquals("Invalid audio bit rate", exception.getMessage());
    }

    @Test
    public void testNullAudioChannel() {
        requestAudioParameter.setCodec("libmp3lame");
        requestAudioParameter.setChannel("1");
        requestAudioParameter.setBitRate("32k");
        requestAudioParameter.setExportFormat(".wav");
        requestAudioParameter.setSampleRate("22050");
        requestAudioParameter.setStart("00:00:30");
        requestAudioParameter.setDuration("00:00:10");
        Exception exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    requestAudioParameter.setChannel(null);
                    requestAudioParameter.validate();
                }
        );
        assertEquals("Invalid audio channel", exception.getMessage());
    }
    @Test
    public void testEmptyAudioChannel() {
        requestAudioParameter.setCodec("libmp3lame");
        requestAudioParameter.setChannel("1");
        requestAudioParameter.setBitRate("32k");
        requestAudioParameter.setExportFormat(".wav");
        requestAudioParameter.setSampleRate("22050");
        requestAudioParameter.setStart("00:00:30");
        requestAudioParameter.setDuration("00:00:10");
        Exception exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    requestAudioParameter.setChannel(" ");
                    requestAudioParameter.validate();
                }
        );
        assertEquals("Invalid audio channel", exception.getMessage());
    }
    @Test
    public void testNotExistChannel() {
        requestAudioParameter.setCodec("libmp3lame");
        requestAudioParameter.setChannel("1");
        requestAudioParameter.setBitRate("32k");
        requestAudioParameter.setExportFormat(".wav");
        requestAudioParameter.setSampleRate("22050");
        requestAudioParameter.setStart("00:00:30");
        requestAudioParameter.setDuration("00:00:10");
        Exception exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    requestAudioParameter.setChannel("100");
                    requestAudioParameter.validate();
                }
        );
        assertEquals("Invalid audio channel", exception.getMessage());
    }

    @Test
    public void testNullAudioSampleRate() {
        requestAudioParameter.setCodec("libmp3lame");
        requestAudioParameter.setChannel("1");
        requestAudioParameter.setBitRate("32k");
        requestAudioParameter.setExportFormat(".wav");
        requestAudioParameter.setSampleRate("22050");
        requestAudioParameter.setStart("00:00:30");
        requestAudioParameter.setDuration("00:00:10");
        Exception exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    requestAudioParameter.setSampleRate(null);
                    requestAudioParameter.validate();
                }
        );
        assertEquals("Invalid sample rate", exception.getMessage());
    }
    @Test
    public void testEmptyAudioSampleRate() {
        requestAudioParameter.setCodec("libmp3lame");
        requestAudioParameter.setChannel("1");
        requestAudioParameter.setBitRate("32k");
        requestAudioParameter.setExportFormat(".wav");
        requestAudioParameter.setSampleRate("22050");
        requestAudioParameter.setStart("00:00:30");
        requestAudioParameter.setDuration("00:00:10");
        Exception exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    requestAudioParameter.setSampleRate(" ");
                    requestAudioParameter.validate();
                }
        );
        assertEquals("Invalid sample rate", exception.getMessage());
    }
    @Test
    public void testNotExistSampleRate() {
        requestAudioParameter.setCodec("libmp3lame");
        requestAudioParameter.setChannel("1");
        requestAudioParameter.setBitRate("32k");
        requestAudioParameter.setExportFormat(".wav");
        requestAudioParameter.setSampleRate("22050");
        requestAudioParameter.setStart("00:00:30");
        requestAudioParameter.setDuration("00:00:10");
        Exception exception = Assertions.assertThrows(
                InvalidDataException.class,
                () -> {
                    requestAudioParameter.setSampleRate("100");
                    requestAudioParameter.validate();
                }
        );
        assertEquals("Invalid sample rate", exception.getMessage());
    }
    @Test
    public void testGetFields() {
        requestAudioParameter.setName("name");
        assertEquals("name", requestAudioParameter.getName());
        requestAudioParameter.setCodec("libmp3lame");
        assertEquals("libmp3lame", requestAudioParameter.getCodec());
        requestAudioParameter.setChannel("1");
        assertEquals("1", requestAudioParameter.getChannel());
        requestAudioParameter.setBitRate("32k");
        assertEquals("32k", requestAudioParameter.getBitRate());
        requestAudioParameter.setExportFormat(".wav");
        assertEquals(".wav", requestAudioParameter.getExportFormat());
        requestAudioParameter.setSampleRate("22050");
        assertEquals("22050", requestAudioParameter.getSampleRate());
        requestAudioParameter.setStart("00:00:30");
        assertEquals("00:00:30", requestAudioParameter.getStart());
        requestAudioParameter.setDuration("00:00:10");
        assertEquals("00:00:10", requestAudioParameter.getDuration());
        requestAudioParameter.setCut(true);
        assertTrue(requestAudioParameter.isCut());
        requestAudioParameter.setExtractMetadata(true);
        assertTrue(requestAudioParameter.isExtractMetadata());
    }
}