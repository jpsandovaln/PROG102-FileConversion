package org.fundacionjala.converter.model.command.multimedia;

import org.fundacionjala.converter.model.command.ICommand;
import org.fundacionjala.converter.model.command.MetadataModel;
import org.fundacionjala.converter.model.commons.exception.InvalidDataException;
import org.fundacionjala.converter.model.commons.validation.DurationAndStartAudioValidation;
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

    private static final String CODEC = "-codec:a";
    private static final String BITRATE = "-b:a";
    private static final String CHANNEL = "-ac";
    private static final String SAMPLE_RATE = "-ar";
    private static final String VOLUME = "-vol";
    private static final String START = "-ss";
    private static final String DURATION = "-t";
    private static final String OVERWRITING = "-y";
    private static final String INPUT = "-i";
    private static final String CUT_SUFFIX = "cut";
    private static final String VOID = "";
    private static final String META_SUFFIX = "-meta";
    private static final String JSON = "j";
    private static final String VERBOSE = "v";
    /**
     * Creates a list of commands for the parameters given
     * @param modelParameter - the reference ModelParameter with parameters of audio
     * @return List<List<String>> - list of commands
     */
    @Override
    public List<List<String>> createCommand(final ModelParameter modelParameter) {
        List<List<String>> listCommands = new ArrayList<>();
        List<String> convert = new ArrayList<>();
        convert = convert(convert, modelParameter);
        listCommands.add(convert);
        AudioParameter param = (AudioParameter) modelParameter;
        if (param.getIsCut()) {
            try {
                List<String> cut = new ArrayList<>();
                cut = cut(cut, modelParameter);
                listCommands.add(cut);
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }
        }
        if (param.isExtractMetadata()) {
            listCommands.add(extractMetadata(param, VOID));
            if (param.getIsCut()) {
                listCommands.add(extractMetadata(param, CUT_SUFFIX));
            }
        }
        return listCommands;
    }

    /**
     * Returns list of commands to convert the audio file
     * @param modelParameter - the reference ModelParameter with parameters of audio
     * @param convert - the reference List<String> where to add commands
     * @return convert - the reference List<String> of commands list to convert
     * @throws InvalidDataException
     */
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

    /**
     * Adds to list
     * @param list - the reference List<String> to the list where to add elements
     * @param condition - the reference String of condition
     * @param command - the reference String of command
     */
    private void addToList(final List<String> list, final String condition, final String command) {
        if (condition != null) {
            list.add(command);
            list.add(condition);
        }
    }

    /**
     * Cuts audio file
     * @param cut - the reference List<String> to add to the list
     * @param modelParameter - the reference ModelParameter with parameters of audio
     * @return cut - the reference List<String> of commands list to cut
     */
    private List<String> cut(final List<String> cut, final ModelParameter modelParameter) throws InvalidDataException {
        File file = new File(ConfigPath.getVideoAudioTool());
        String fileToolPath = file.getAbsolutePath();
        cut.add(fileToolPath);
        cut.add(OVERWRITING);
        cut.add(INPUT);
        cut.add(modelParameter.getInputFile());
        AudioParameter param = (AudioParameter) modelParameter;
        DurationAndStartAudioValidation validator = new DurationAndStartAudioValidation(param.getDuration(), param.getStart(), param.getSecondsToOutput());
        validator.validate();
        addToList(cut, param.getStart(), START);
        addToList(cut, param.getDuration(), DURATION);
        String nameFile = param.getName();
        String formatFile = param.getFormat();
        cut.add(modelParameter.getOutputFile() + nameFile + CUT_SUFFIX + formatFile);
        name(modelParameter);
        return cut;
    }

    /**
     * Extracts metadata of audio file
     * @param audioParameter - the reference AudioParameter with parameters of audio
     * @param suffix - the reference String to suffix
     * @return
     */
    private List<String> extractMetadata(final AudioParameter audioParameter, final String suffix) {
        String input = audioParameter.getOutputFile() + audioParameter.getName() + suffix + audioParameter.getFormat();
        String output = audioParameter.getName() + suffix + audioParameter.getFormat().substring(1) + META_SUFFIX;
        MetadataParameter metadataParameter = new MetadataParameter(input, JSON, VERBOSE, output, VOID);
        MetadataModel metadataModel = new MetadataModel();
        return metadataModel.createCommand(metadataParameter).get(0);
    }
}
