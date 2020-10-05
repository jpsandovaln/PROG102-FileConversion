package org.fundacionjala.converter.model;

import org.fundacionjala.converter.model.utility.ChecksumMD5;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;
class ChecksumMD5Test {

    @Test
    public void testValidateMD5() throws IOException, NoSuchAlgorithmException {
        String expected = "e1b3fab24c8af81c1aa13dbbb4e44ff0";
        String inputFile = "storage/inputFiles/test.mp3";
        String result = new ChecksumMD5().getMD5(inputFile);
        assertEquals(expected, result);
    }

    @Test
    public void testFaildMD5() throws IOException, NoSuchAlgorithmException {
        String expected = "e1b3fab24c8af81c1aa13dbbb4e44ff1";
        String inputFile = "storage/inputFiles/test.mp3";
        String result = new ChecksumMD5().getMD5(inputFile);
        assertNotEquals(expected, result);
    }
}

