package org.fundacionjala.converter.model.command.multimedia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.ArrayList;

import org.fundacionjala.converter.model.commons.exception.InvalidDataException;
import org.fundacionjala.converter.model.configPath.ConfigPath;
import org.fundacionjala.converter.model.parameter.multimedia.VideoParameter;
import org.junit.After;
import org.junit.jupiter.api.Test;

public class VideoModelTest {

    private static final String duration = "0:00:44";
    private static final String start = "0:00:15";
    private static final String secondsToOutput = "5";
    private static final String controlLoop = "0";
    private static final String frames = "21";
    private static final boolean extractThumbnail = true;
    String md5 = "95635711ebd6ec96be366c0e20ddf2b8";
    ConfigPath ConfigPath;
    VideoParameter vParameter = new VideoParameter();
    VideoFacade vFacade = new VideoFacade();
    VideoModel vModel = new VideoModel();
    List<String> listParameters;
    List<List<String>> list;
    String audioCodec = "mp3";
    String videoCodec = "h264";

    @After
    public void clean() throws IOException {
        Files.deleteIfExists(Path.of("storage/convertedFiles/95635711ebd6ec96be366c0e20ddf2b8(mov).mov"));
        Files.deleteIfExists(Path.of("storage/convertedFiles/95635711ebd6ec96be366c0e20ddf2b8(mp4).mp4"));
        Files.deleteIfExists(Path.of("storage/convertedFiles/95635711ebd6ec96be366c0e20ddf2b8(gif).gif"));
        Files.deleteIfExists(Path.of("storage/convertedFiles/95635711ebd6ec96be366c0e20ddf2b8(thumbnail).gif"));
    }
    
    @Test
    public void testConvertMov() throws IOException, InvalidDataException, NoSuchAlgorithmException,
            InterruptedException, ExecutionException {
        String expected = "[" + ConfigPath.getVideoAudioTool() + ", -i, storage/inputFiles/calculadora.mp4, -vcodec, copy, -acodec, copy, storage/convertedFiles/95635711ebd6ec96be366c0e20ddf2b8(mov).mov]";
        listParameters = new ArrayList<String>();
        vParameter.setOutputFiles(new ArrayList <>());
        vParameter.setInputFile("storage/inputFiles/calculadora.mp4");
        vParameter.setOutputFile(ConfigPath.getConvertedFilesPath());
        vParameter.setFormat(".mov");
        vParameter.setCodec(audioCodec);
        vParameter.setVideoCodec(videoCodec);
        List<String> actual = vModel.createCommand(vParameter).get(0);
        assertEquals(expected, actual.toString());
    }
    @Test
    public void testCompressMp4() throws IOException, InvalidDataException, NoSuchAlgorithmException,
            InterruptedException, ExecutionException {
        String expected = "[" + ConfigPath.getVideoAudioTool() + ", -i, storage/inputFiles/calculadora.mp4, -vcodec, h264, -acodec, mp3, storage/convertedFiles/95635711ebd6ec96be366c0e20ddf2b8(mp4).mp4]";
        listParameters = new ArrayList<String>();
        vParameter.setOutputFiles(new ArrayList <>());
        vParameter.setInputFile("storage/inputFiles/calculadora.mp4");
        vParameter.setOutputFile(ConfigPath.getConvertedFilesPath());
        vParameter.setFormat(".mp4");
        vParameter.setCodec(audioCodec);
        vParameter.setVideoCodec(videoCodec);
        List<String> actual = vModel.createCommand(vParameter).get(0);
        assertEquals(expected, actual.toString());
    }
    @Test
    public void testConvertGif() throws IOException, InvalidDataException, NoSuchAlgorithmException,
            InterruptedException, ExecutionException {
        //String expected = "[" + ConfigPath.getVideoAudioTool() + ", -i, storage/inputFiles/calculadora.mp4, -ss, 0:00:15, -t, 5, -vf, \"fps=, 21, ,scale=320:-1:flags=lanczos,split[s0][s1];[s0]palettegen[p];[s1][p]paletteuse\", -loop, 1, storage/convertedFiles/95635711ebd6ec96be366c0e20ddf2b8(gif).gif]";
        listParameters = new ArrayList<String>();
        vParameter.setInputFile("storage/inputFiles/calculadora.mp4");
        vParameter.setOutputFile(ConfigPath.getConvertedFilesPath());
        vParameter.setMd5(md5);
        vParameter.setFormat(".gif");
        vParameter.setDuration(duration);
        vParameter.setStart(start);
        vParameter.setSecondsToOutput(secondsToOutput);
        vParameter.setFrames(frames);
        vParameter.setControlLoop(controlLoop);
        int actual = vFacade.convertVideo(vParameter).size();
        assertTrue(actual > 0);
    }
    @Test
    public void testExtractThumbnail() throws IOException, InvalidDataException, NoSuchAlgorithmException,
            InterruptedException, ExecutionException {
        VideoFacade vFacade = new VideoFacade();
        String expected = "storage/convertedFiles/95635711ebd6ec96be366c0e20ddf2b8(thumbnail).gif";
        list = new ArrayList<>();
        vParameter.setInputFile("storage/inputFiles/calculadora.mp4");
        vParameter.setOutputFile(ConfigPath.getConvertedFilesPath());
        vParameter.setFormat(".mp4");
        vParameter.setCodec(audioCodec);
        vParameter.setVideoCodec(videoCodec);
        vParameter.setExtractThumbnail(extractThumbnail);
        List<String> actual = vFacade.convertVideo(vParameter);
        assertEquals(expected, actual.get(1).toString());
    }
    @Test
    public void testConvertThrowsNullParameterExceptionWhenVideoParameterIsNull() {
        Throwable exception = assertThrows(
            NullPointerException.class, () -> vModel.convert(null));
        assertEquals(null, exception.getMessage());
    }
}