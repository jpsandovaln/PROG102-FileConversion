package org.fundacionjala.converter.model.parameter.multimedia;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class MultimediaParameterTest {

    MultimediaParameter parameter;

    @Test
    public void testSetNameNullThrowsNullPointerException() {
        Throwable exception = Assertions.assertThrows(
            NullPointerException.class, () -> {
                parameter.setName(null);
            }
        );
    }
    @Test
    public void testSetCodecNullThrowsNullPointerException() {
        Throwable exception = Assertions.assertThrows(
            NullPointerException.class, () -> {
                parameter.setCodec(null);
            }
        );
    }
    @Test
    public void testSetMd5FileNullThrowsNullPointerException() {
        Throwable exception = Assertions.assertThrows(
            NullPointerException.class, () -> {
                parameter.setStart(null);
            }
        );
    }
    @Test
    public void testSetDurationFileNullThrowsNullPointerException() {
        Throwable exception = Assertions.assertThrows(
            NullPointerException.class, () -> {
                parameter.setDuration(null);
            }
        );
    }
}