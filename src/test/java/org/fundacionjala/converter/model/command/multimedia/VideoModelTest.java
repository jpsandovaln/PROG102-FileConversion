package org.fundacionjala.converter.model.command.multimedia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.ArrayList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.fundacionjala.converter.model.command.multimedia.VideoModel;
import org.fundacionjala.converter.model.commons.exception.InvalidDataException;
import org.fundacionjala.converter.model.parameter.multimedia.VideoParameter;
import org.junit.Test;

public class VideoModelTest {

    private static final String duration = "0:04:00";
    private static final String timeToSkip = "0:00:15";
    private static final String secondsToOutput = "5";
    private static final String controlLoop = "0";
    private static final String frames = "21";
    private static final boolean extractThumbnail = true;
    VideoParameter vParameter = new VideoParameter();
    VideoModel vModel = new VideoModel();
    List<String> listParameters;
    List<List<String>> list;
    String audioCodec = "mp3";
    String videoCodec = "h264";

    @Test
    public void testConvertMov() throws IOException, InvalidDataException, NoSuchAlgorithmException,
            InterruptedException, ExecutionException {
        String expected = "[thirdParty/ffmpeg/bin/ffmpeg.exe, -i, inputFile.mp4, -vcodec, copy, -acodec, copy, storage/convertedFiles/inputFile.mov]";
        listParameters = new ArrayList<String>();
        vModel.createCommand(vParameter);
        vParameter.setInputFile("inputFile.mp4");
        vParameter.setOutputFile("storage/convertedFiles/");
        vParameter.setName("inputFile");
        vParameter.setFormat(".mov");
        vParameter.setCodec(audioCodec);
        vParameter.setVideoCodec(videoCodec);
        List<String> actual = vModel.convert(vParameter);
        assertEquals(expected, actual.toString());
    }
    @Test
    public void testCompressMp4() throws IOException, InvalidDataException, NoSuchAlgorithmException,
            InterruptedException, ExecutionException {
        String expected = "[thirdParty/ffmpeg/bin/ffmpeg.exe, -i, inputFile.mp4, -vcodec, h264, -acodec, mp3, storage/convertedFiles/inputFile.mp4]";
        listParameters = new ArrayList<String>();
        vModel.createCommand(vParameter);
        vParameter.setInputFile("inputFile.mp4");
        vParameter.setOutputFile("storage/convertedFiles/");
        vParameter.setName("inputFile");
        vParameter.setFormat(".mp4");
        vParameter.setCodec(audioCodec);
        vParameter.setVideoCodec(videoCodec);
        List<String> actual = vModel.convert(vParameter);
        assertEquals(expected, actual.toString());
    }
    @Test
    public void testConvertGif() throws IOException, InvalidDataException, NoSuchAlgorithmException,
            InterruptedException, ExecutionException {
        String expected = "[thirdParty/ffmpeg/bin/ffmpeg.exe, -i, inputFile.mp4, -ss, 0:00:15, -t, 5, -vf, \"fps=, 21, ,scale=320:-1:flags=lanczos,split[s0][s1];[s0]palettegen[p];[s1][p]paletteuse\", -loop, 0, storage/convertedFiles/inputFile.gif]";
        listParameters = new ArrayList<String>();
        vParameter.setInputFile("inputFile.mp4");
        vParameter.setOutputFile("storage/convertedFiles/");
        vParameter.setName("inputFile");
        vParameter.setFormat(".gif");
        vParameter.setCodec(audioCodec);
        vParameter.setVideoCodec(videoCodec);
        vParameter.setDuration(duration);
        vParameter.setStart(timeToSkip);
        vParameter.setSecondsToOutput(secondsToOutput);
        vParameter.setFrames(frames);
        vParameter.setControlLoop(controlLoop);
        vModel.createCommand(vParameter);
        List<String> actual = vModel.convert(vParameter);
        assertEquals(expected, actual.toString());
    }
    @Test
    public void testExtractThumbnail() throws IOException, InvalidDataException, NoSuchAlgorithmException,
            InterruptedException, ExecutionException {
        String expected = "[thirdParty/ffmpeg/bin/ffmpeg.exe, -i, inputFile.mp4, -ss, 10, -t, 5, -vf, \"fps=10,scale=320:-1:flags=lanczos,split[s0][s1];[s0]palettegen[p];[s1][p]paletteuse\", -loop, 0, storage/convertedFiles/inputFile.gif]";
        listParameters = new ArrayList<String>();
        vParameter.setInputFile("inputFile.mp4");
        vParameter.setOutputFile("storage/convertedFiles/");
        vParameter.setName("inputFile");
        vParameter.setFormat(".mp4");
        vParameter.setCodec(audioCodec);
        vParameter.setVideoCodec(videoCodec);
        vParameter.setExtractThumbnail(extractThumbnail);
        list = vModel.createCommand(vParameter);
        List<String> actual = list.get(1);
        assertEquals(expected, actual.toString());
    }
}