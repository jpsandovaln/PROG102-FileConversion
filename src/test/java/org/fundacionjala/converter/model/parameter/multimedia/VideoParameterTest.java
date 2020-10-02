package org.fundacionjala.converter.model.parameter.multimedia;

import static org.junit.Assert.assertEquals;

import org.fundacionjala.converter.model.commons.validation.NotNullOrEmpty;
import org.junit.Assert;
import org.junit.Test;

public class VideoParameterTest {

    VideoParameter parameter =  new VideoParameter();

    @Test
    public void testVideoCodecIsSeted() {
        String expected = "h264";
        parameter.setVideoCodec("h264");
        String actual = parameter.getVideoCodec();
        assertEquals(expected, actual);
    }
    @Test
    public void getNullVideoCodecTest() {
        parameter.setVideoCodec(null);
        
            //parameter.getVideoCodec().validation();
        /*} catch (NotNullOrEmpty e) {
            String actual = e.getMessage();
            String expected = "The attribute: \"id\" is null";
            Assert.assertEquals(expected, actual);
        }*/
        Assert.assertNull(parameter.getVideoCodec());
    }
}