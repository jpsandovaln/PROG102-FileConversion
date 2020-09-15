package org.fundacionjala.converter.model.command;
import org.fundacionjala.converter.model.configPath.ConfigPath;
import org.fundacionjala.converter.model.parameter.ModelParameter;
import org.fundacionjala.converter.model.parameter.multimedia.AudioParameter;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mirko Romay
 * @version 0.1
 */
public class AudioModel implements ICommand {

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
            return "An error occurs while converting file, please try again.";
        }
    }

    /**
     * create command
     * @return list of commands
     * @param modelParameter
     */
    @Override
    public List<List<String>> createCommand(final ModelParameter modelParameter) {
        ConfigPath configPath = new ConfigPath();
        File file = new File(configPath.getVideoAudioTool());
        String fileToolPath = file.getAbsolutePath();
        List<List<String>> listCommands = new ArrayList<>();
        List<String> commandsConvert = new ArrayList<>();
        if (fileToolPath.charAt(0) != '/') { //windows
            commandsConvert.add("cmd");
            commandsConvert.add("/c");
            commandsConvert.add(fileToolPath);
            commandsConvert.add("-i");
            createCommandConvert(commandsConvert, modelParameter);
        }
        listCommands.add(commandsConvert);
        return listCommands;
    }

    private List createCommandConvert(final List<String> commandsConvert, final ModelParameter modelParameter) {
        commandsConvert.add(modelParameter.getInputFile());
        String nameFile = ((AudioParameter) modelParameter).getAudioName();
        String formatFile = ((AudioParameter) modelParameter).getAudioFormat();
        commandsConvert.add(modelParameter.getOutputFile() + nameFile + formatFile);

        if (((AudioParameter) modelParameter).getAudioCodec() != null) {
            commandsConvert.add("-codec:a");
            commandsConvert.add(((AudioParameter) modelParameter).getAudioCodec());
        }
        if (((AudioParameter) modelParameter).getAudioBitRate() != null) {
            commandsConvert.add("-b:a");
            commandsConvert.add(((AudioParameter) modelParameter).getAudioBitRate());
        }
        if (((AudioParameter) modelParameter).getAudioChannel() != null) {
            commandsConvert.add("-ac");
            commandsConvert.add(((AudioParameter) modelParameter).getAudioChannel());
        }
        if (((AudioParameter) modelParameter).getAudioSampleRate() != null) {
            commandsConvert.add("-ar");
            commandsConvert.add(((AudioParameter) modelParameter).getAudioSampleRate());
        }
        if (((AudioParameter) modelParameter).getAudioStart() != null) {
            commandsConvert.add("-ss");
            commandsConvert.add(((AudioParameter) modelParameter).getAudioStart());
        }
        if (((AudioParameter) modelParameter).getAudioDuration() != null) {
            commandsConvert.add("-t");
            commandsConvert.add(((AudioParameter) modelParameter).getAudioDuration());
        }
        if (((AudioParameter) modelParameter).getAudioVolume() != null) {
            commandsConvert.add("-vol");
            commandsConvert.add(((AudioParameter) modelParameter).getAudioVolume());
        }
        return commandsConvert;
    }
}
