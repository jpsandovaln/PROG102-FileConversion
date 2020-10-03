package org.fundacionjala.converter.model.parameter.multimedia;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.containsString;

import java.io.IOException;

public class MultimediaParameterTest {

    MultimediaParameter parameter;
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testSetNameNullThrowsNullPointerException() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(containsString("is null"));
        parameter.setName(null);
    }
    @Test
    public void testSetCodecNullThrowsNullPointerException() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(containsString("is null"));
        parameter.setCodec(null);
    }
    @Test
    public void testSetStartNullThrowsNullPointerException() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(containsString("is null"));
        parameter.setStart(null);
    }
    @Test
    public void testSetDurationNullThrowsNullPointerException() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(containsString("is null"));
        parameter.setDuration(null);
    }
}