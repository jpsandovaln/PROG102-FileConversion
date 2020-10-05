package org.fundacionjala.converter.model.command.multimedia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.fundacionjala.converter.model.commons.exception.InvalidDataException;
import org.fundacionjala.converter.model.configPath.ConfigPath;
import org.fundacionjala.converter.model.parameter.multimedia.VideoParameter;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

public class VideoFacadeTest {

    static VideoFacade facade;

    @BeforeAll
    public static void testBeforeAllTests() {
        facade = new VideoFacade();
    }

    @Test
    public void testNullArgumentIsPassedToSetVideoModel() {
        try {
            facade.setVideoModel(null);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Invalid Model."));
        }
    }

    @Test
    public void testGetListMetadataCommands() throws NullPointerException, IOException, NoSuchAlgorithmException,
            InterruptedException, ExecutionException, InvalidDataException {
        int expected = 1;
        VideoParameter parameter = new VideoParameter();
        parameter.setInputFile("storage/inputFiles/calculadora.mp4");
        parameter.setOutputFile(ConfigPath.getConvertedFilesPath());
        parameter.setFormat(".mp4");
        parameter.setCodec("mp3");
        parameter.setVideoCodec("h264");
        parameter.setExtractMetadata(true);
        facade.convertVideo(parameter);
        assertEquals(expected, parameter.getOutputFiles().size());
        Files.deleteIfExists(Path.of("storage/convertedFiles/95635711ebd6ec96be366c0e20ddf2b8(mp4).mp4"));
        Files.deleteIfExists(Path.of("storage/convertedFiles/95635711ebd6ec96be366c0e20ddf2b8(mp4)_METADATA.json"));
        Files.deleteIfExists(Path.of("storage/convertedFiles/95635711ebd6ec96be366c0e20ddf2b8(mov).mov"));
        Files.deleteIfExists(Path.of("storage/convertedFiles/95635711ebd6ec96be366c0e20ddf2b8(mov)_METADATA.json"));
    }
}