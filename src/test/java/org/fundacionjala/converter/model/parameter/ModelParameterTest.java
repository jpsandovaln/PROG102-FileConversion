package org.fundacionjala.converter.model.parameter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.containsString;

import java.io.IOException;

public class ModelParameterTest {

    ModelParameter parameter;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testSetInputFileNullThrowsNullPointerException() throws IOException{
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(containsString("is null"));
        parameter.setInputFile(null);
    }
    @Test
    public void testSetOutputFileNullThrowsNullPointerException() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(containsString("is null"));
        parameter.setOutputFile(null);
    }
    @Test
    public void testSetMd5NullThrowsNullPointerException() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(containsString("is null"));
        parameter.setMd5(null);
    }
    @Test
    public void testSetFormatNullThrowsNullPointerException() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(containsString("is null"));
        parameter.setFormat(null);
    }
}