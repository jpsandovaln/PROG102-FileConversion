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
        List<String> convert = new ArrayList<>();
        convert = convert(convert, fileToolPath, modelParameter);
        listCommands.add(convert);
        return listCommands;
    }

    private List convert(final List<String> convert, final String fileToolPath, final ModelParameter modelParameter) {
        if (fileToolPath.charAt(0) != '/') { //windows
            convert.add("cmd");
            convert.add("/c");
            convert.add(fileToolPath);
            convert.add("-i");
        }
        return convert(convert, modelParameter);
    }

    private List<String> convert(final List<String> convert, final ModelParameter modelParameter) {
        convert.add(modelParameter.getInputFile());

        if (((AudioParameter) modelParameter).getCodec() != null) {
            convert.add("-codec:a");
            convert.add(((AudioParameter) modelParameter).getCodec());
        }
        if (((AudioParameter) modelParameter).getBitRate() != null) {
            convert.add("-b:a");
            convert.add(((AudioParameter) modelParameter).getBitRate());
        }
        if (((AudioParameter) modelParameter).getChannel() != null) {
            convert.add("-ac");
            convert.add(((AudioParameter) modelParameter).getChannel());
        }
        if (((AudioParameter) modelParameter).getSampleRate() != null) {
            convert.add("-ar");
            convert.add(((AudioParameter) modelParameter).getSampleRate());
        }

        if (((AudioParameter) modelParameter).getVolume() != null) {
            convert.add("-vol");
            convert.add(((AudioParameter) modelParameter).getVolume());
        }
        String nameFile = ((AudioParameter) modelParameter).getName();
        String formatFile = ((AudioParameter) modelParameter).getFormat();
        convert.add(modelParameter.getOutputFile() + nameFile + formatFile);
        return convert;
    }
}
