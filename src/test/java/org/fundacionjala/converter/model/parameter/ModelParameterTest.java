package org.fundacionjala.converter.model.parameter;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ModelParameterTest {

    ModelParameter parameter;

    @Test
    public void testSetInputFileNullThrowsNullPointerException() {
        Throwable exception = Assertions.assertThrows(
            NullPointerException.class, () -> {
                parameter.setInputFile(null);
            }
        );
    }
    @Test
    public void testSetOutputFileNullThrowsNullPointerException() {
        Throwable exception = Assertions.assertThrows(
            NullPointerException.class, () -> {
                parameter.setOutputFile(null);
            }
        );
    }
    @Test
    public void testSetMd5NullThrowsNullPointerException() {
        Throwable exception = Assertions.assertThrows(
            NullPointerException.class, () -> {
                parameter.setMd5(null);
            }
        );
    }
    @Test
    public void testSetFormatNullThrowsNullPointerException() {
        Throwable exception = Assertions.assertThrows(
            NullPointerException.class, () -> {
                parameter.setFormat(null);
            }
        );
    }
}