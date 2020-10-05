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
public class AudioModel implements ICommand<AudioParameter> {

    private static final String CODEC = "-codec:a";
    private static final String BITRATE = "-b:a";
    private static final String CHANNEL = "-ac";
    private static final String SAMPLE_RATE = "-ar";
    private static final String START = "-ss";
    private static final String DURATION = "-t";
    private static final String OVERWRITING = "-y";
    private static final String INPUT = "-i";
    private static final String CUT_SUFFIX = "_cut";
    private static final String VOID = "";
    private static final String JSON = "j";
    private static final String VERBOSE = "v";
    /**
     * create command
     * @return list of commands
     * @param audioParameter
     */
    @Override
    public List<List<String>> createCommand(final AudioParameter audioParameter)  {
        try {
            audioParameter.validate();
        } catch (InvalidDataException e) {
            e.getMessage();
        }
        List<List<String>> listCommands = new ArrayList<>();
        List<String> convert = new ArrayList<>();
        convert = convert(convert, audioParameter);
        listCommands.add(convert);
        if (audioParameter.getIsCut()) {
            try {
                List<String> cut = new ArrayList<>();
                cut = cut(cut, audioParameter);
                listCommands.add(cut);
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }
        }
        if (audioParameter.isExtractMetadata()) {
            listCommands.add(extractMetadata(audioParameter, VOID));
            if (audioParameter.getIsCut()) {
                listCommands.add(extractMetadata(audioParameter, CUT_SUFFIX));
            }
        }

        return listCommands;
    }

    /**
     * Collects list of converter
     * @param convert
     * @param audioParameter
     * @return
     */
    private List<String> convert(final List<String> convert, final AudioParameter audioParameter) {
        String fileToolPath = ConfigPath.getVideoAudioTool();
        String fileConvertPath = ConfigPath.getConvertedFilesPath();
        convert.add(fileToolPath);
        convert.add(OVERWRITING);
        convert.add(INPUT);
        convert.add(audioParameter.getInputFile());
        addToList(convert, audioParameter.getCodec(), CODEC);
        addToList(convert, audioParameter.getBitRate(), BITRATE);
        addToList(convert, audioParameter.getChannel(), CHANNEL);
        addToList(convert, audioParameter.getSampleRate(), SAMPLE_RATE);
        String formatFile = audioParameter.getFormat();
        convert.add(fileConvertPath + audioParameter.getMd5() + formatFile);

        return convert;
    }

    /**
     * add to list if condition is true
     * @param list
     * @param condition
     * @param command
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
     * collects list of extract metadata
     * @param audioParameter
     * @param suffix
     * @return
     */
    private List<String> extractMetadata(final AudioParameter audioParameter, final String suffix) {
        String fileConvertPath = ConfigPath.getConvertedFilesPath();
        String input = fileConvertPath + audioParameter.getMd5() + suffix + audioParameter.getFormat();
        String output = "";
        MetadataParameter metadataParameter = new MetadataParameter(input, JSON, VERBOSE, output, VOID);
        MetadataModel metadataModel = new MetadataModel();
        return metadataModel.createCommand(metadataParameter).get(0);
    }

}
