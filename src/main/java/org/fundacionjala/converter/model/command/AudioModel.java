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

    private static final String
            CODEC = "-codec:a",
            BITRATE = "-b:a",
            CHANNEL = "-ac",
            SAMPLE_RATE = "-ar",
            VOLUME = "-vol",
            START = "-ss",
            DURATION = "-t";
    /**
     * create command
     * @return list of commands
     * @param modelParameter
     */
    @Override
    public List<List<String>> createCommand(final ModelParameter modelParameter) {
        List<List<String>> listCommands = new ArrayList<>();
        List<String> convert = new ArrayList<>();
        convert = convert(convert, modelParameter);
        listCommands.add(convert);
        AudioParameter param = (AudioParameter) modelParameter;
        if (param.getIsMetadata()) {
            List<String> metadata = new ArrayList<>();
            metadata(metadata, modelParameter);
            listCommands.add(metadata);
        }
        if (param.getIsCut()) {
            List<String> cut = new ArrayList<>();
            cut = cut(cut, modelParameter);
            listCommands.add(cut);
        }
        return listCommands;
    }

    private List<String> convert(final List<String> convert, final ModelParameter modelParameter) {
        ConfigPath configPath = new ConfigPath();
        File file = new File(configPath.getVideoAudioTool());
        String fileToolPath = file.getAbsolutePath();
        convert.add(fileToolPath);
        convert.add("-y");
        convert.add("-i");
        convert.add(modelParameter.getInputFile());
        AudioParameter param = (AudioParameter) modelParameter;
        addToList(convert, param.getCodec(), CODEC);
        addToList(convert, param.getBitRate(), BITRATE);
        addToList(convert, param.getChannel(), CHANNEL);
        addToList(convert, param.getSampleRate(), SAMPLE_RATE);
        addToList(convert, param.getVolume(), VOLUME);
        String nameFile = ((AudioParameter) modelParameter).getName();
        String formatFile = ((AudioParameter) modelParameter).getFormat();
        convert.add(modelParameter.getOutputFile() + nameFile + formatFile);
        return convert;
    }

    private void addToList(final List<String> list, final String condition, final String command) {
        if (condition != null) {
            list.add(command);
            list.add(condition);
        }
    }

    private List<String> cut(final List<String> cut, final ModelParameter modelParameter) {
        ConfigPath configPath = new ConfigPath();
        File file = new File(configPath.getVideoAudioTool());
        String fileToolPath = file.getAbsolutePath();
        cut.add(fileToolPath);
        cut.add("-y");
        cut.add("-i");
        cut.add(modelParameter.getInputFile());
        AudioParameter param = (AudioParameter) modelParameter;
        addToList(cut, param.getStart(), START);
        addToList(cut, param.getDuration(), DURATION);
        String nameFile = param.getName();
        String formatFile = param.getFormat();
        cut.add(modelParameter.getOutputFile() + nameFile + "cut" + formatFile);
        return cut;
    }
    private List<String> metadata(final List<String> metadata, final ModelParameter modelParameter) {
        // MetadataModel metadata = new MetadataModel()
        // return metadata.createCommand(ModelParameter modelParameter);
        return  null;
    }
}
