package org.fundacionjala.converter.model.parameter.multimedia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.fundacionjala.converter.model.command.multimedia.VideoFacade;
import org.fundacionjala.converter.model.commons.exception.InvalidDataException;
import org.junit.jupiter.api.Test;

public class VideoFacadeTest {
    VideoFacade facade;
    VideoParameter videoParameter;
    List<String> list;

    @Test
    public void testGetListConvertVideoContainsOneElementWhenExtractMetadataIsNotSelected()
            throws NoSuchAlgorithmException, IOException, InterruptedException, ExecutionException,
            InvalidDataException {
        facade = new VideoFacade();
        videoParameter = new VideoParameter();
        list = new ArrayList<String>();
        String outputFile = "storage/convertedFiles/";
        String path = "storage/inputFiles/calculadora.mp4";
        videoParameter.setInputFile(path);
        videoParameter.setOutputFile(outputFile);
        videoParameter.setMd5("d41d8cd98f00b204e9800998ecf8427e");
        videoParameter.setFormat(".mov");
        videoParameter.setExtractMetadata(true);
        list = facade.convertVideo(videoParameter);
        assertEquals(2, list.size());
    }
}