package org.fundacionjala.converter.model.commons.validation;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.is;

import org.fundacionjala.converter.model.commons.exception.InvalidDataException;

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
        GifValidation gValidation = new GifValidation();
        gValidation.setDuration(300);
        try {
            gifValidation.setStart(350);
            gifValidation.validate();
        }
        catch (InvalidDataException e){
            assertThat(e.getMessage(), is("Invalid arguments to convert a Gif"));
        }
    }
    @Test
    public void testValidateThrowsExceptionWhenTimeIsGreaterThanStart() {
        GifValidation gValidation = new GifValidation(duration, start, "400");
        try {
            gValidation.validate();
        }
        catch (InvalidDataException e){
            assertThat(e.getMessage(), is("Invalid arguments to convert a Gif"));
        }
    }
    @Test
    public void testValidateThrowsExceptionWhenStartIsEqualsThanDuration() {
        GifValidation gValidation = new GifValidation(duration, duration, "5");
        try {
            gValidation.validate();
        }
        catch (InvalidDataException e){
            assertThat(e.getMessage(), is("Invalid arguments to convert a Gif"));
        }
    }
    @Test
    public void testValidateThrowsExceptionWhenStartPlusTimeIsGreaterThanDuration() {
        GifValidation gValidation = new GifValidation(duration, start, "500");
        try {
            gValidation.validate();
        }
        catch (InvalidDataException e){
            assertThat(e.getMessage(), is("Invalid arguments to convert a Gif"));
        }
    }
    @Test
    public void testValidateThrowsException() {
        GifValidation gValidation = new GifValidation(duration, start, "500");
        try {
            gValidation.validate();
        }
        catch (InvalidDataException e){
            assertThat(e.getMessage(), is("Invalid arguments to convert a Gif"));
        }
    }
}