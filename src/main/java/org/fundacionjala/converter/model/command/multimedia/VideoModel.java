package org.fundacionjala.converter.model.command.multimedia;

import org.fundacionjala.converter.model.parameter.metadata.MetadataParameter;
import org.fundacionjala.converter.model.parameter.multimedia.VideoParameter;
import org.fundacionjala.converter.model.command.ICommand;
import org.fundacionjala.converter.model.command.MetadataModel;
import org.fundacionjala.converter.model.commons.exception.InvalidDataException;
import org.fundacionjala.converter.model.commons.validation.FormatValidation;
import org.fundacionjala.converter.model.commons.validation.GifValidation;
import org.fundacionjala.converter.model.configPath.ConfigPath;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class VideoModel implements ICommand<VideoParameter> {
    private List<String> listParameters;
    private List<String> listThumbnailParameters;
    private List<List<String>> list;
    private List<List<String>> listMetadataCommands;
    private List<MetadataParameter> listMetadataParameters;
    private List<String> outputFiles;
    private boolean convertedGif = false;
    private static final String FORMAT = "j";   // json
    private static final String DETAIL = "v";
    private ConfigPath configPath;

    public VideoModel() {
    }

    /**
     * Returns list of commands to convert the video file
     * @param videoParameter - the reference VideoParameter with parameters of video
     * @return List<String> - list of commands to convert
     * @throws InvalidDataException
     */
    public List<String> convert(final VideoParameter videoParameter) throws InvalidDataException {
        String format = videoParameter.getFormat();
        if (format.equals(FormatValidation.FORMAT_MP4)) {
            videoParameter.validate();
            return compressMp4(videoParameter);
        } else if (format.equals(FormatValidation.FORMAT_GIF)) {
            GifValidation gifValidation = new GifValidation(videoParameter.getDuration(), videoParameter.getStart(), videoParameter.getSecondsToOutput());
            gifValidation.validate();
            return convertGif(videoParameter);
        } else if (format.equals(FormatValidation.FORMAT_MOV)) {
            return convertMov(videoParameter);
        }
        return null;
    }

    /**
     * Returns list of commands to convert the video to mov format
     * @param videoParameter - the reference VideoParameter with parameters of video
     * @return List<String> - list of commands to convert
     */
    private List<String> convertMov(final VideoParameter videoParameter) {
        listParameters = new ArrayList<String>();
        listParameters.add(configPath.getVideoAudioTool());
        listParameters.add(VideoParameter.INPUT_COMMAND);
        listParameters.add(videoParameter.getInputFile());
        listParameters.add(VideoParameter.VCODEC_COMMAND);
        listParameters.add(VideoParameter.COPY);
        listParameters.add(VideoParameter.ACODEC_COMMAND);
        listParameters.add(VideoParameter.COPY);
        String name = changeName(videoParameter.getOutputFile(), videoParameter.getName(), FormatValidation.FORMAT_MP4) + videoParameter.getFormat();
        listParameters.add(videoParameter.getOutputFile() + name);
        outputFiles.add(videoParameter.getOutputFile() + name);
        return listParameters;
    }

    /**
     * Compress video
     * @param videoParameter - the reference VideoParameter with parameters of video
     * @return List<String> - list of commands to convert
     */
    private List<String> compressMp4(final VideoParameter videoParameter) {
        listParameters = new ArrayList<String>();
        listParameters.add(configPath.getVideoAudioTool());
        listParameters.add(VideoParameter.INPUT_COMMAND);
        listParameters.add(videoParameter.getInputFile());
        listParameters.add(VideoParameter.VCODEC_COMMAND);
        listParameters.add(videoParameter.getVideoCodec());
        listParameters.add(VideoParameter.ACODEC_COMMAND);
        listParameters.add(videoParameter.getCodec());
        String name = changeName(videoParameter.getOutputFile(), videoParameter.getName(), FormatValidation.FORMAT_MP4) + videoParameter.getFormat();
        listParameters.add(videoParameter.getOutputFile() + name);
        outputFiles.add(videoParameter.getOutputFile() + name);
        return listParameters;
    }

    /**
     * Returns list of parameters to convert mp4 to gif
     * @param videoParameter - the reference VideoParameter with parameters of video
     * @return List<String> - list of commands
     */
    private List<String> convertGif(final VideoParameter videoParameter) {
        //System.out.println("***************convert gif **************");
        final List<String> listParameters = new ArrayList<>();
        listParameters.add(configPath.getVideoAudioTool());
        listParameters.add(VideoParameter.INPUT_COMMAND);
        listParameters.add(videoParameter.getInputFile());
        listParameters.add(VideoParameter.START);
        listParameters.add(videoParameter.getStart());
        listParameters.add(VideoParameter.TIME);
        listParameters.add(videoParameter.getSecondsToOutput());
        listParameters.add(VideoParameter.VF);
        listParameters.add(VideoParameter.FRAME_RATE);
        listParameters.add(videoParameter.getFrames());
        listParameters.add(VideoParameter.PALETTE);
        listParameters.add(VideoParameter.LOOP);
        listParameters.add(videoParameter.getControlLoop());
        String name = changeName(videoParameter.getOutputFile(), videoParameter.getName(), FormatValidation.FORMAT_GIF) + videoParameter.getFormat();
        listParameters.add(videoParameter.getOutputFile() + name);
        outputFiles.add(videoParameter.getOutputFile() + name);
        convertedGif = true;
        return listParameters;
    }

    /**
     * Returns a list of commands to extract thumbnail
     * Thumbnail parameters by default are:
     * -frame rate: 10
     * -starts in second 10
     * -duration: 5 seconds
     * Video's duration must be greater than 15 seconds.
     * @param videoParameter - the reference VideoParameter with parameters of video
     * @return List<String> - list of commands
     */
    private List<String> extractThumbnail(final VideoParameter videoParameter) {
        listThumbnailParameters = new ArrayList<>();
        listThumbnailParameters.add(configPath.getVideoAudioTool());
        listThumbnailParameters.add(VideoParameter.INPUT_COMMAND);
        listThumbnailParameters.add(videoParameter.getInputFile());
        listThumbnailParameters.add(VideoParameter.START);
        listThumbnailParameters.add(VideoParameter.START_TIME);
        listThumbnailParameters.add(VideoParameter.TIME);
        listThumbnailParameters.add(VideoParameter.DURATION);
        listThumbnailParameters.add(VideoParameter.VF);
        listThumbnailParameters.add(VideoParameter.PALETTE_THUMBNAIL);
        listThumbnailParameters.add(VideoParameter.LOOP);
        listThumbnailParameters.add(VideoParameter.ZERO);
        String name = changeName(videoParameter.getOutputFile(), videoParameter.getName(), FormatValidation.FORMAT_GIF) + videoParameter.getFormat();
        listThumbnailParameters.add(videoParameter.getOutputFile() + name);
        outputFiles.add(videoParameter.getOutputFile() + name);
        return listThumbnailParameters;
    }

    /**
     * Creates a list of commands for the parameters given
     * @param videoParameter - the reference VideoParameter with parameters of video
     * @return List<List<String>> - list of commands
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private List<List<String>> getListMetadataCommands(final VideoParameter videoParameter)
    throws NoSuchAlgorithmException, IOException, InterruptedException, ExecutionException {
        listMetadataCommands = new ArrayList<>();
        listMetadataParameters = new ArrayList<>();
        String checksum = "";
        for (String path : outputFiles) {
            listMetadataParameters.add(new MetadataParameter(path, FORMAT, DETAIL, configPath.getConvertedFilesPath() + "", checksum));
        }
        for (MetadataParameter metadataParameter : listMetadataParameters) {
            listMetadataCommands.addAll(new MetadataModel().createCommand(metadataParameter));
        }
        return listMetadataCommands;
    }

    /**
     * Creates a list of commands for the parameters given
     * @param videoParameter - the reference VideoParameter with parameters of video
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Override
    public List<List<String>> createCommand(final VideoParameter videoParameter)
            throws NoSuchAlgorithmException, IOException, InterruptedException, ExecutionException {
        list = new ArrayList<>();
        outputFiles = new ArrayList<String>();
        try {
            list.add(convert(videoParameter));
        } catch (InvalidDataException e) {
            e.printStackTrace();
        }
        if (videoParameter.isExtractThumbnail()) {
            if (videoParameter.getFormat() != VideoParameter.GIF) {
                videoParameter.setFormat(VideoParameter.GIF);
            }
            if (convertedGif) {
                String name = changeOutputName(videoParameter.getOutputFile(), videoParameter.getName(), FormatValidation.FORMAT_GIF);
                videoParameter.setName(name);
            }
            list.add(extractThumbnail(videoParameter));
        }
        if (videoParameter.isExtractMetadata()) {
            list.addAll(getListMetadataCommands(videoParameter));
        }
        return list;
    }
}
