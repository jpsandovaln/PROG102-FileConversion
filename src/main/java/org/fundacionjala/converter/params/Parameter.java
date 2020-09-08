package org.fundacionjala.converter.params;

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
