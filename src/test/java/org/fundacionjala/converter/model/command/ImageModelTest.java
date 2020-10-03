package org.fundacionjala.converter.model.command;

import org.fundacionjala.converter.model.parameter.image.ImageParameter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImageModelTest {
    @Test
    public void testCreateCommandReturnsEmptyListIfParameterIsEmpty() {
        ImageModel imageModel = new ImageModel();
        ImageParameter imageParameter = new ImageParameter();
        int result = imageModel.createCommand(imageParameter).size();
        assertEquals(1, result);
    }

    @Test
    public void testIsResize() throws IOException {
        ImageModel imageModel = new ImageModel();
        ImageParameter imageParameter = new ImageParameter();
        String expected = "[[thirdParty/linux/imagemagick/magick, storage/inputFiles/1nar.jpg, -resize, 1000X1000!, storage/convertedFiles/95384efd0209233dde8003713cbdb9c3(resized).png]]";
        imageParameter.setInputFile("storage/inputFiles/1nar.jpg");
        imageParameter.setOutputFile("storage/convertedFiles/");
        imageParameter.setMd5("95384efd0209233dde8003713cbdb9c3");
        imageParameter.setFormat(".png");
        imageParameter.setIsResize(true);
        imageParameter.setHeight(1000);
        imageParameter.setWidth(1000);
        List<List<String>> actual = imageModel.createCommand(imageParameter);
        assertEquals(expected, actual.toString());
    }
    @Test
    public void testIsOnlyFormat() throws IOException {
        ImageModel imageModel = new ImageModel();
        ImageParameter imageParameter = new ImageParameter();
        String expected = "[[thirdParty/linux/imagemagick/magick, storage/inputFiles/1nar.jpg, storage/convertedFiles//95384efd0209233dde8003713cbdb9c3(FORMAT).png]]";
        imageParameter.setInputFile("storage/inputFiles/1nar.jpg");
        imageParameter.setOutputFile("storage/convertedFiles/");
        imageParameter.setMd5("95384efd0209233dde8003713cbdb9c3");
        imageParameter.setFormat(".png");
        List<List<String>> actual = imageModel.createCommand(imageParameter);
        assertEquals(expected, actual.toString());
    }

    @Test
    public void testGetGray() throws IOException {
        ImageModel imageModel = new ImageModel();
        ImageParameter imageParameter = new ImageParameter();
        String expected = "[[thirdParty/linux/imagemagick/magick, storage/inputFiles/1nar.jpg, -colorspace, gray, storage/convertedFiles/95384efd0209233dde8003713cbdb9c3(gray).png]]";
        imageParameter.setInputFile("storage/inputFiles/1nar.jpg");
        imageParameter.setOutputFile("storage/convertedFiles/");
        imageParameter.setMd5("95384efd0209233dde8003713cbdb9c3");
        imageParameter.setFormat(".png");
        imageParameter.setIsGray(true);
        List<List<String>> actual = imageModel.createCommand(imageParameter);
        assertEquals(expected, actual.toString());
    }

    @Test
    public void testGetCrop() throws IOException {
        ImageModel imageModel = new ImageModel();
        ImageParameter imageParameter = new ImageParameter();
        String expected = "[[thirdParty/linux/imagemagick/magick, storage/inputFiles/1nar.jpg, -crop, 0x0+78+40, storage/convertedFiles/95384efd0209233dde8003713cbdb9c3(imageregion).png]]";
        imageParameter.setInputFile("storage/inputFiles/1nar.jpg");
        imageParameter.setOutputFile("storage/convertedFiles/");
        imageParameter.setMd5("95384efd0209233dde8003713cbdb9c3");
        imageParameter.setFormat(".png");
        imageParameter.setPositionXAndPositionY("78,40");
        List<List<String>> actual = imageModel.createCommand(imageParameter);
        assertEquals(expected, actual.toString());
    }

    @Test
    public void testGetThumbnail() throws IOException    {
        ImageModel imageModel = new ImageModel();
        ImageParameter imageParameter = new ImageParameter();
        String expected = "[[thirdParty/linux/imagemagick/magick, storage/inputFiles/1nar.jpg, -resize, 128X128, storage/convertedFiles/95384efd0209233dde8003713cbdb9c3(thumbnail).png]]";
        imageParameter.setInputFile("storage/inputFiles/1nar.jpg");
        imageParameter.setOutputFile("storage/convertedFiles/");
        imageParameter.setMd5("95384efd0209233dde8003713cbdb9c3");
        imageParameter.setFormat(".png");
        imageParameter.setIsThumbnail(true);
        List<List<String>> actual = imageModel.createCommand(imageParameter);
        assertEquals(expected, actual.toString());
    }

    @Test
    public void testExtractMetadata() throws IOException    {
        ImageModel imageModel = new ImageModel();
        ImageParameter imageParameter = new ImageParameter();
        String expected = "[Metadata, /usr/bin/exiftool, -j, -v, storage/convertedFiles//95384efd0209233dde8003713cbdb9c3(thumbnail).png, 95384efd0209233dde8003713cbdb9c3(thumbnail)0png-meta_Metadata.json]";
        imageParameter.setInputFile("storage/inputFiles/1nar.jpg");
        imageParameter.setOutputFile("storage/convertedFiles/");
        imageParameter.setMd5("95384efd0209233dde8003713cbdb9c3");
        imageParameter.setFormat(".png");
        imageParameter.setMetadata(true);
        imageParameter.setIsThumbnail(true);
        List<String> actual = imageModel.extractMetadata(imageParameter,"(thumbnail)");
        assertEquals(expected, actual.toString());
    }
}

