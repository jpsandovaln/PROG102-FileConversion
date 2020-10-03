package org.fundacionjala.converter.controller.request;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class RequestMetadataParameterTest {
    RequestMetadataParameter requestMetadataParameter = new RequestMetadataParameter();
    File file = new File("storage/inputFiles/test.mp3");

    @Test
    public void setRequestMetadataParameterWithNullFormat() {
        Throwable exception = Assertions.assertThrows(
                Exception.class,
                () -> {
                    requestMetadataParameter.setExportFormat(null);
                    requestMetadataParameter.validate();
                }
        );
    }

    @Test
    public void setRequestMetadataParameterWithNullFileName() {
        Throwable exception = Assertions.assertThrows(
                Exception.class,
                () -> {
                    requestMetadataParameter.setExportFormat("t");
                    requestMetadataParameter.setFileName(null);
                    requestMetadataParameter.validate();
                }
        );
    }

    @Test
    public void setRequestMetadataParameterWithNullMD5() {
        Throwable exception = Assertions.assertThrows(
                Exception.class,
                () -> {
                    requestMetadataParameter.setExportFormat("t");
                    requestMetadataParameter.setFileName(null);
                    requestMetadataParameter.validate();
                }
        );
    }

    @Test
    public void setRequestMetadataParameterWithInvalidMD5() {
        Throwable exception = Assertions.assertThrows(
                Exception.class,
                () -> {
                    requestMetadataParameter.setExportFormat("t");
                    requestMetadataParameter.setFile((MultipartFile) file);
                    requestMetadataParameter.validate();
                }
        );
    }

    @Test
    public void setRequestMetadataParameterWith() {
        Throwable exception = Assertions.assertThrows(
                Exception.class,
                () -> {
                    requestMetadataParameter.setExportFormat("t");
                    requestMetadataParameter.setFile((MultipartFile) file);
                    requestMetadataParameter.setMd5("e1b3fab24c8af81c1aa13dbbb4e44ff0");
                    requestMetadataParameter.validate();
                }
        );
    }

    @Test
    public void getFileName() {
        requestMetadataParameter.setFileName("file.txt");
        String result = requestMetadataParameter.getFileName();
        String expected = "file.txt";
        assertEquals(result, expected);
    }

    @Test
    public void getExportFormat() {
        requestMetadataParameter.setExportFormat("h");
        String result = requestMetadataParameter.getExportFormat();
        String expected = "h";
        assertEquals(result, expected);
    }

    @Test
    public void getDetail() {
        requestMetadataParameter.setDetail("d");
        String result = requestMetadataParameter.getDetail();
        String expected = "d";
        assertEquals(result, expected);
    }
}