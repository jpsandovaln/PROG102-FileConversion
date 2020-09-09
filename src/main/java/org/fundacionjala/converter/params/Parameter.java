package org.fundacionjala.converter.params;

/**
 * @author Mirko Romay
 * @version 0.1
 */
import org.springframework.web.multipart.MultipartFile;

public class Parameter {
    /**
     *
     * @param file
     */
    public void setFile(final MultipartFile file) {
        this.file = file;
    }

    /**
     * @return file
     */
    public MultipartFile getFile() {
        return file;
    }

    private MultipartFile file;
}
