package org.fundacionjala.converter.model;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Mirko Romay
 * @version 0.1
 */
public class AudioModel {

    private String buildCommand(final String params, final String source, final String target, final String tool) {
        return tool + " -y -i "  + source + params  + target;
    }

    /**
     * Show all output of audio file conversion
     * @param params string
     * @param source string
     * @param target string
     * @param tool string
     * @return path of audio file converted
     */
    public String convertAudio(final String params, final String source, final String target, final String tool) {
        try {
            String command = buildCommand(params, source, target, tool);
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
            System.out.println(result.toString());
            return target;
        } catch (java.io.IOException | java.lang.InterruptedException e) {
            System.out.println(e.getMessage());
            return "An error occurs when converting file, please try again.";
        }
    }
}
