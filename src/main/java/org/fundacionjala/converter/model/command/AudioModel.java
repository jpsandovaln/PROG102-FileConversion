package org.fundacionjala.converter.model.command;

import org.fundacionjala.converter.model.configPath.ConfigPath;
import org.fundacionjala.converter.model.parameter.ModelParameter;
import org.fundacionjala.converter.model.parameter.multimedia.AudioParameter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mirko Romay
 * @version 0.1
 */
public class AudioModel implements ICommand {

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
        String nameFile = ((AudioParameter) modelParameter).getAudioName();
        String formatFile = ((AudioParameter) modelParameter).getAudioFormat();
        commandsConvert.add(modelParameter.getOutputFile() + nameFile + formatFile);
        return commandsConvert;
    }
}
