/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.model.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Julia Escalante
 * @version 0.1
 */
public class ChecksumMD5 {
    public static final int SIZE_BYTE = 1024;

    /**
     * display the md5 of a file
     * @param file´s path
     * @return md5 of the file
     */
    public String getMD5(final String file) throws IOException, NoSuchAlgorithmException {
        File files = new File(file);
        String filePath = files.getAbsolutePath();
        MessageDigest md = MessageDigest.getInstance("MD5");
        String hex = checksum(filePath, md);
        return hex;
    }

    /**
     * execute md5
     * @param filepath
     * @param md messageDigest
     * @return toString result md5
     * @throws IOException
     */
    private String checksum(final String filepath, final MessageDigest md) throws IOException {
        try (InputStream fis = new FileInputStream(filepath)) {
            byte[] buffer = new byte[SIZE_BYTE];
            int nread;
            while ((nread = fis.read(buffer)) != -1) {
                md.update(buffer, 0, nread);
            }
        }

        StringBuilder result = new StringBuilder();
        for (byte b : md.digest()) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
