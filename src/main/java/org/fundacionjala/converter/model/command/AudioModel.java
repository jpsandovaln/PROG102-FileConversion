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
        List<List<String>> listCommands = new ArrayList<>();
        List<String> convert = new ArrayList<>();
        convert.addAll(initialCommand());
        convert.addAll(convert(convert, modelParameter));
        listCommands.add(convert);

        if (((AudioParameter) modelParameter).getIsMetadata()) {
            List<String> metadata = new ArrayList<>();
            metadata.addAll(initialCommand());
            metadata.addAll(metadata(metadata, modelParameter));
            listCommands.add(metadata);
        }

        if (((AudioParameter) modelParameter).getIsCut()) {
            List<String> cut = new ArrayList<>();
            cut.addAll(initialCommand());
            cut.addAll(cut(cut, modelParameter));
            listCommands.add(cut);
        }

        return listCommands;
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

    private List<String> cut(final List<String> cut, final ModelParameter modelParameter) {
        cut.add(modelParameter.getInputFile());

        if (((AudioParameter) modelParameter).getStart() != null) {
            cut.add("-ss");
            cut.add(((AudioParameter) modelParameter).getStart());
        }

        if (((AudioParameter) modelParameter).getDuration() != null) {
            cut.add("-t");
            cut.add(((AudioParameter) modelParameter).getDuration());
        }

        String nameFile = ((AudioParameter) modelParameter).getName();
        String formatFile = ((AudioParameter) modelParameter).getFormat();
        cut.add(modelParameter.getOutputFile() + nameFile + "cut" + formatFile);
        return cut;
    }
    private List<String> metadata(final List<String> metadata, final ModelParameter modelParameter) {
        return  null;
    }

    private List<String> initialCommand() {
        ConfigPath configPath = new ConfigPath();
        File file = new File(configPath.getVideoAudioTool());
        String fileToolPath = file.getAbsolutePath();
        List<String> command = new ArrayList<>();
        if (fileToolPath.charAt(0) != '/') { //windows
            command.add("cmd");
            command.add("/c");
            command.add(fileToolPath);
            command.add("-i");
        }
        return command;
    }
}
