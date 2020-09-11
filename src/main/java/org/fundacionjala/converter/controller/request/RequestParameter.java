package org.fundacionjala.converter.controller.request;

import org.fundacionjala.converter.model.ChecksumMD5;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public abstract class  RequestParameter {

    private MultipartFile file;
    private String format;
    private String newName;

    /**
     *
     * @return
     */
    public String getNewName() {
        return newName;
    }

    /**
     *
     * @return
     */

    public String getFormat() {
        return format;
    }

    /**
     *
     * @return
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     *
     * @param filePath
     * @return
     */
    public String generateMD5(final String filePath) {
        try {
            String checksum = "";
            ChecksumMD5 checksumMD5 = new ChecksumMD5();
            checksum = checksumMD5.getMD5(filePath);
            return checksum;
        } catch (NoSuchAlgorithmException | IOException e) {
            e.getMessage();
            return "Could not get MD5 from input file.";
        }
    }
    public abstract boolean validate();
}
