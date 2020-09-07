package org.fundacionjala.converter.model;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class AudioModel {

    /**
     * Show all output of audio file conversion
     * @param file uploaded
     * @param format string
     * @param codec string
     * @param bitRate string
     * @param channels string
     * @param sampleRate string
     * @param start string
     * @param duration string
     * @param vol string
     * @return all output of audio file conversion
     */
    public String convertAudio(final MultipartFile file, final String format, final String codec,
                               final String bitRate, final String channels, final String sampleRate,
                               final String start, final String duration, final String vol,
                               final String input, final String converted, final String tool) {
        try {
            String fileName = file.getOriginalFilename();
            String exitThree = "";
            String source = input + fileName;
            String fileNameConverted = fileName.substring(0, fileName.indexOf("."));
            String target = converted + fileNameConverted + "-converted" + format;
            Files.copy(file.getInputStream(), Paths.get(source), StandardCopyOption.REPLACE_EXISTING);
            source = exitThree + source;
            target = exitThree + target;
            String outputParameters = "";
            if (codec != "") {
                outputParameters += " -codec:a " + codec;
            }
            if (bitRate != "") {
                outputParameters += " -b:a " + bitRate;
            }
            if (channels != "") {
                outputParameters += " -ac " + channels;
            }
            if (sampleRate != "") {
                outputParameters += " -ar " + sampleRate;
            }
            if (start != "") {
                outputParameters += " -ss " + start;
            }
            if (duration != "") {
                outputParameters += " -t " + duration;
            }
            if (vol != "") {
                outputParameters += " -vol " + vol;
            }
            outputParameters += " ";
            String projectDirectory = System.getProperty("user.dir") + "/";
            projectDirectory = projectDirectory.replace("\\", "/");
            String command = projectDirectory + tool + " -y -i " + projectDirectory + source + outputParameters + projectDirectory + target;
            ProcessBuilder builder = new ProcessBuilder("cmd", "/c", "\"" + command + "\"");
            builder.redirectErrorStream(true);
            Process process = builder.start();
            process.waitFor();
            InputStreamReader streamReader = new InputStreamReader(process.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder result = new StringBuilder();
            while (reader.ready()) {
                result.append((char) reader.read());
            }
            return result.toString();
        } catch (java.io.IOException e) {
            return e.getMessage();
        } catch (java.lang.InterruptedException e) {
            return e.getMessage();
        }
    }
}
