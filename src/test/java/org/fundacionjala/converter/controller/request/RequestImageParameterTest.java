package org.fundacionjala.converter.controller.request;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class RequestImageParameterTest {

    RequestImageParameter request = new RequestImageParameter();
    @Test
    void getHeight() {
        request.setHeight(200);
        int expected = 200;
        int actual = request.getHeight();
        assertEquals(expected, actual);
    }

    @Test
    void getWidth() {
        request.setWidth(200);
        int expected = 200;
        int actual = request.getWidth();
        assertEquals(expected, actual);
    }

    @Test
    void getExtractThumbnail() {
        request.setExtractThumbnail(true);
        boolean actual = request.getExtractThumbnail();
        assertTrue(actual);
    }

    @Test
    void getExtractMetadata() {
        request.setExtractMetadata(true);
        boolean actual = request.getExtractMetadata();
        assertTrue(actual);
    }

    @Test
    void getFalseExtractMetadata() {
        request.setExtractMetadata(false);
        boolean actual = request.getExtractMetadata();
        assertFalse(actual);
    }

    @Test
    void getPosition() {
        request.setPosition("100");
        String expected = "100";
        String actual = request.getPosition();
        assertEquals(expected, actual);
    }

    @Test
    void getChangeSize() {
        request.setChangeSize(true);
        boolean actual = request.getChangeSize();
        assertTrue(actual);
    }

    @Test
    void getFalseChangeSize() {
        request.setChangeSize(false);
        boolean actual = request.getChangeSize();
        assertFalse(actual);
    }

    @Test
    void getGray() {
        request.setGray(true);
        boolean actual = request.getGray();
        assertTrue(actual);
    }
    @Test
    void getFalseGray() {
        request.setGray(false);
        boolean actual = request.getGray();
        assertFalse(actual);
    }
    @Test
    void emptyExportFormat() {
        Throwable exception = Assertions.assertThrows(
                Exception.class, () -> {
                    request.setExportFormat(" ");
                    request.validate();
                }
        );
    }
    @Test
    void nullExportFormat() {
        Throwable exception = Assertions.assertThrows(
                Exception.class, () -> {
                    request.setExportFormat(null);
                    request.validate();
                }
        );
    }
    @Test
    void nullFile() {
        Throwable exception = Assertions.assertThrows(
                Exception.class, () -> {
                    request.setExportFormat(".png");
                    request.setFile(null);
                    request.validate();
                }
        );
    }
    @Test
    void md5Empty() throws IOException {
        MockMultipartFile file = new MockMultipartFile("imagen7", "imagen..7.jpg",
                "image/", "video data".getBytes());
        MockMultipartFile multipartFile = new MockMultipartFile(
                "attachments", file.getName(),
                MediaType.MULTIPART_FORM_DATA_VALUE,
                file.getInputStream());
        Throwable exception = Assertions.assertThrows(
                Exception.class, () -> {
                    request.setExportFormat(".png");
                    request.setFile(multipartFile);
                    request.setMd5("");
                    request.validate();
                }
        );
    }
    @Test
    void md5Invalid() throws IOException {
        MockMultipartFile file = new MockMultipartFile("imagen7", "imagen.jpg",
                "image/", "imagen7 data".getBytes());
        MockMultipartFile multipartFile = new MockMultipartFile(
                "attachments", file.getName(),
                MediaType.MULTIPART_FORM_DATA_VALUE,
                file.getInputStream());
        Throwable exception = Assertions.assertThrows(
                Exception.class, () -> {
                    request.setExportFormat(".png");
                    request.setFile(multipartFile);
                    request.setMd5("a1cbf54e11273fb97da300ec3dc57a87");
                    request.validate();
                }
        );
    }
    @Test
    void emptyHeight() throws IOException {
        MockMultipartFile file = new MockMultipartFile("imagen7", "imagen..7.jpg",
                "image/", "video data".getBytes());
        MockMultipartFile multipartFile = new MockMultipartFile(
                "attachments", file.getName(),
                MediaType.MULTIPART_FORM_DATA_VALUE,
                file.getInputStream());
        Throwable exception = Assertions.assertThrows(
                Exception.class, () -> {
                    request.setExportFormat(".png");
                    request.setFile(multipartFile);
                    request.setMd5("a1cbf54e11273fb97da300ec3dc57a87");
                    request.validate();
                }
        );
    }
    @Test
    void emptyWidth() throws IOException {
        MockMultipartFile file = new MockMultipartFile("imagen7", "imagen..7.jpg",
                "image/", "video data".getBytes());
        MockMultipartFile multipartFile = new MockMultipartFile(
                "attachments", file.getName(),
                MediaType.MULTIPART_FORM_DATA_VALUE,
                file.getInputStream());
        Throwable exception = Assertions.assertThrows(
                Exception.class, () -> {
                    request.setExportFormat(".png");
                    request.setHeight(100);
                    request.setFile(multipartFile);
                    request.setMd5("a1cbf54e11273fb97da300ec3dc57a87");
                    request.validate();
                }
        );
    }
    @Test
    void invalidWidth() throws IOException {
        MockMultipartFile file = new MockMultipartFile("imagen7", "imagen..7.jpg",
                "image/", "video data".getBytes());
        MockMultipartFile multipartFile = new MockMultipartFile(
                "attachments", file.getName(),
                MediaType.MULTIPART_FORM_DATA_VALUE,
                file.getInputStream());
        Throwable exception = Assertions.assertThrows(
                Exception.class, () -> {
                    request.setExportFormat(".png");
                    request.setHeight(100);
                    request.setWidth(-1);
                    request.setFile(multipartFile);
                    request.setMd5("a1cbf54e11273fb97da300ec3dc57a87");
                    request.validate();
                }
        );
    }
    @Test
    void invalidHeight() throws IOException {
        MockMultipartFile file = new MockMultipartFile("imagen7", "imagen..7.jpg",
                "image/", "video data".getBytes());
        MockMultipartFile multipartFile = new MockMultipartFile(
                "attachments", file.getName(),
                MediaType.MULTIPART_FORM_DATA_VALUE,
                file.getInputStream());
        Throwable exception = Assertions.assertThrows(
                Exception.class, () -> {
                    request.setExportFormat(".png");
                    request.setHeight(-1);
                    request.setFile(multipartFile);
                    request.setMd5("a1cbf54e11273fb97da300ec3dc57a87");
                    request.validate();
                }
        );
    }
}