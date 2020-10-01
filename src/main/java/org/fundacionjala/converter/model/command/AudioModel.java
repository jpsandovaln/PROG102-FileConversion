package org.fundacionjala.converter.model.command;

import org.fundacionjala.converter.model.configPath.ConfigPath;
import org.fundacionjala.converter.model.parameter.ModelParameter;
import org.fundacionjala.converter.model.parameter.metadata.MetadataParameter;
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
            DURATION = "-t",
            OVERWRITING = "-y",
            INPUT = "-i",
            CUT_SUFFIX = "cut",
            VOID = "",
            META_SUFFIX = "-meta",
            JSON = "j",
            VERBOSE = "v";
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
        if (param.getIsCut()) {
            List<String> cut = new ArrayList<>();
            cut = cut(cut, modelParameter);
            listCommands.add(cut);
        }
        if (param.getIsMetadata()) {
            listCommands.add(extractMetadata(param, VOID));
            if (param.getIsCut()) {
                listCommands.add(extractMetadata(param, CUT_SUFFIX));
            }
        }
        return listCommands;
    }

    private List<String> convert(final List<String> convert, final ModelParameter modelParameter) {
        File file = new File(ConfigPath.getVideoAudioTool());
        String fileToolPath = file.getAbsolutePath();
        convert.add(fileToolPath);
        convert.add(OVERWRITING);
        convert.add(INPUT);
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
        name(modelParameter);
        return convert;
    }

    private void addToList(final List<String> list, final String condition, final String command) {
        if (condition != null) {
            list.add(command);
            list.add(condition);
        }
    }

    private List<String> cut(final List<String> cut, final ModelParameter modelParameter) {
        File file = new File(ConfigPath.getVideoAudioTool());
        String fileToolPath = file.getAbsolutePath();
        cut.add(fileToolPath);

        cut.add(OVERWRITING);
        cut.add(INPUT);
        cut.add(modelParameter.getInputFile());
        AudioParameter param = (AudioParameter) modelParameter;
        addToList(cut, param.getStart(), START);
        addToList(cut, param.getDuration(), DURATION);
        String nameFile = param.getName();
        String formatFile = param.getFormat();
        cut.add(modelParameter.getOutputFile() + nameFile + CUT_SUFFIX + formatFile);
        name(modelParameter);
        return cut;
    }
    private List<String> extractMetadata(final AudioParameter audioParameter, final String suffix) {
        String input = audioParameter.getOutputFile() + audioParameter.getName() + suffix + audioParameter.getFormat();
        String output = audioParameter.getName() + suffix + audioParameter.getFormat().substring(1) + META_SUFFIX;
        MetadataParameter metadataParameter = new MetadataParameter(input, JSON, VERBOSE, output, VOID);
        MetadataModel metadataModel = new MetadataModel();
        return metadataModel.createCommand(metadataParameter).get(0);
    }
}
