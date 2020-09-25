package org.fundacionjala.converter.model.parameter.multimedia;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class AudioParameterValidation {

    /**
     *
     * @param path
     * @return true if file is mimetype audio
     * @throws IOException
     */
    public boolean validateAudioFile(final String path) throws IOException {
        File file = new File(path);
        String typeFile = Files.probeContentType(file.toPath());
        if (typeFile.contains("audio")) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param audioFormat
     * @return true if format does exist in enum
     */
    public boolean validateAudioFormat(final String audioFormat) {
        String audio = audioFormat.substring(1).toUpperCase();
        for (AudioFormat format: AudioFormat.values()) {
            if (audio.equals(format.name())) {
                return true;
            }
        }
        return false;
    }

    /**
     * validate audio file name
     * @param name
     * @return
     */
    public String validateAudioName(final String name) {
      /*  if (name.charAt(0) != '/') {
            return "/" + name;
        } else {*/
            return name;
       // }
    }
}
