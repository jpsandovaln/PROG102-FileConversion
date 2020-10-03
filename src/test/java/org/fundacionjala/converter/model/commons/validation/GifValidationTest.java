package org.fundacionjala.converter.model.commons.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class GifValidationTest {

    String duration = "0:05:00";
    String start = "0:00:10";
    GifValidation gifValidation = new GifValidation(duration, start, "3");

    @Test
    public void testConvertDurationToSecondsFiveMinutes(){
        int expected = 300;
        gifValidation.convertToSeconds(duration);
        int actual = gifValidation.getDuration();
        assertEquals(expected, actual);
    }
    @Test
    public void testConvertStartToSecondsTenSeconds(){
        int expected = 10;
        gifValidation.convertToSeconds(start);
        int actual = gifValidation.getStart();
        assertEquals(expected, actual);
    }
    @Test
    public void testValidateThrowsExceptionWhenStartIsGreaterThanDuration() {
        
    }
}