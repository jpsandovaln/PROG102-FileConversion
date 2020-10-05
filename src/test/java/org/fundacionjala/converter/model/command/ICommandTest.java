package org.fundacionjala.converter.model.command;

import org.fundacionjala.converter.model.command.multimedia.VideoModel;
import org.fundacionjala.converter.model.parameter.ModelParameter;
import org.fundacionjala.converter.model.parameter.multimedia.VideoParameter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

public class ICommandTest {
    VideoModel model = new VideoModel();
    ModelParameter parameter = new VideoParameter();;
    String name = "calculadora";
    String path = "storage/inputFiles/";

    @Test
    public void testChangeNameWhenFileIsInGivenDirectory() throws IOException {
        String expected = "calculadora(1)";
        String actual = model.changeName(path, name, ".mp4");
        assertEquals(expected, actual);
    }
    @Test
    public void testChangeNameOfNullFileNameThrowsException() {
        try {
            model.changeName(null, name, ".mp4");
        } catch (Exception e) {
            assertEquals("NullPointerException", e.getMessage());
        }
    }
    @Test
    public void testChangeNameOfNullFilePathThrowsException() {
        try {
            model.changeName(path, null, ".mp4");
        } catch (Exception e) {
            assertEquals("NullPointerException", e.getMessage());
        }
    }
}