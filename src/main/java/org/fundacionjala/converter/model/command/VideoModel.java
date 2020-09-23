package org.fundacionjala.converter.model.command;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.fundacionjala.converter.model.parameter.ModelParameter;
import org.fundacionjala.converter.model.parameter.metadata.MetadataParameter;
import org.fundacionjala.converter.model.parameter.multimedia.VideoParameter;
import org.fundacionjala.converter.executor.Executor;
import org.fundacionjala.converter.model.commons.ChecksumMD5;
import org.fundacionjala.converter.model.configPath.ConfigPath;

public class VideoModel implements ICommand {
    private List<String> listParameters;
    private List<String> listThumbnailParameters;
    private List<List<String>> list;
    private List<List<String>> listMetadataCommands;
    private List<MetadataParameter> listMetadataParameters;
    private static final String MP4 = "mp4";
    private static final String GIF = "gif";
    private static final String NAME_OUTPUT_MP4 = "demo.mp4";
    private static final String NAME_OUTPUT_THUMBNAIL = "thumbnail.gif";
    private static final String NAME_OUTPUT_GIF = "demo.gif";

    /**
     * Returns list of commands to convert the video to mp4 or gif
     *
     * @param videoParameter - the reference to Video Parameter
     * @return List<String> - list of commands to convert
     */
    public List<String> convert(final VideoParameter videoParameter) {
        if (videoParameter.getExtension().equals(MP4)) {
            return compressToMp4(videoParameter);
        } else if (videoParameter.getExtension().equals(GIF)) {
            return gif(videoParameter);
        }
        return null;
    }

    /**
     * Compress video to mp4
     *
     * @param videoParameter
     * @return List<String> - list of commands to convert
     */
    private List<String> compressToMp4(final VideoParameter videoParameter) {
        final ConfigPath cPath = new ConfigPath();
        listParameters = new ArrayList<String>();
        listParameters.add(cPath.getVideoAudioTool());
        listParameters.add(VideoParameter.INPUT_COMMAND);
        listParameters.add(videoParameter.getInputFile());
        listParameters.add(VideoParameter.VCODEC_COMMAND);
        listParameters.add(videoParameter.getVideoCodec());
        listParameters.add(VideoParameter.ACODEC_COMMAND);
        listParameters.add(videoParameter.getAudioCodec());
        listParameters.add(videoParameter.getOutputFile() + NAME_OUTPUT_MP4);
        return listParameters;
    }

    /**
     * Returns a list of commands to extract thumbnail
     *
     * @param videoParameter - the reference to Video Parameter
     * @return List<String> - list of commands
     */
    private List<String> extractThumbnail(final VideoParameter videoParameter) {
        listThumbnailParameters = new ArrayList<>();
        ConfigPath cPath = new ConfigPath();
        listThumbnailParameters.add(cPath.getVideoAudioTool());
        listThumbnailParameters.add(VideoParameter.INPUT_COMMAND);
        listThumbnailParameters.add(videoParameter.getInputFile());
        listThumbnailParameters.add(VideoParameter.START);
        listThumbnailParameters.add(VideoParameter.START_TIME);
        listThumbnailParameters.add(VideoParameter.TIME);
        listThumbnailParameters.add(VideoParameter.DURATION);
        listThumbnailParameters.add(VideoParameter.VF);
        listThumbnailParameters.add(VideoParameter.PALETTE);
        listThumbnailParameters.add(VideoParameter.LOOP);
        listThumbnailParameters.add(VideoParameter.ZERO);
        listThumbnailParameters.add(videoParameter.getOutputFile() + NAME_OUTPUT_THUMBNAIL);
        return listThumbnailParameters;
    }

    /**
     * Creates a list of metadata Parameters in json format and detail v
     *
     * @param videoParameter - the reference to Video Parameter
     * @return List - list of commands
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private List<MetadataParameter> extractMetadata(final VideoParameter videoParameter)
            throws NoSuchAlgorithmException, IOException, InterruptedException, ExecutionException {
        listMetadataParameters = new ArrayList<>();
        Executor executor = new Executor();
        ChecksumMD5 checksumMD5 = new ChecksumMD5();
        List<String> outputFiles;
        outputFiles = executor.executeCommandsList(this.list);
        String inputFileMetadata = "storage//convertedFiles//";
        String outputFile = "meta";
        String checksum = "";
        String format = "j";    //json
        String detail = "v";
        int name = 0;
        for (String path : outputFiles) {
            checksum = checksumMD5.getMD5(path);
            listMetadataParameters.add(new MetadataParameter(inputFileMetadata + getNameFile(path, videoParameter), format, detail, outputFile + name + "", checksum));
            name++;
        }

        return listMetadataParameters;
    }
    /**
     * Creates a list of commands for the parameters given
     * @param listMetadataParameters - the reference to the List of metadatas parameters
     * @return List - list of commands
     */
    private List<List<String>> getListMetadataCommands(final List<MetadataParameter> listMetadataParameters) {
        listMetadataCommands = new ArrayList<>();
        for (MetadataParameter metadataParameter : listMetadataParameters) {
            listMetadataCommands.addAll(new MetadataModel().createCommand(metadataParameter));
        }
        return listMetadataCommands;
    }

    /**
     * Returns the name of file converted
     * @param videoParameter - the reference to Video Parameter
     * @return String - name of converted file
     */
    private String getNameFile(final String path, final VideoParameter videoParameter) {
        String nameFile;
        String[] parts = path.split("\\.");
        String extension = parts[1];
        switch (extension) {
            case MP4:
                nameFile = NAME_OUTPUT_MP4;
                break;
            default:
                if (!videoParameter.isExtractThumbnail()) {
                    nameFile = NAME_OUTPUT_GIF;
                }
                nameFile = NAME_OUTPUT_THUMBNAIL;
                break;
        }
        return nameFile;
    }

    /**
     * Returns list of parameters to convert file to gif
     * @param videoParameter - the reference to Video Parameter
     * @return List<String> - list of commands
     */
    private List<String> gif(final VideoParameter videoParameter) {
        final List<String> listParameters = new ArrayList<>();
        final ConfigPath cPath = new ConfigPath();
        listParameters.add(cPath.getVideoAudioTool());
        listParameters.add(VideoParameter.FRAME_RATE);
        listParameters.add(videoParameter.getFrames());
        listParameters.add(VideoParameter.INPUT_COMMAND);
        listParameters.add(videoParameter.getInputFile());
        listParameters.add(videoParameter.getOutputFile() + NAME_OUTPUT_GIF);
        return listParameters;
    }

    /**
     * Creates command
     *
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Override
    public List<List<String>> createCommand(final ModelParameter modelParameter)
            throws NoSuchAlgorithmException, IOException, InterruptedException, ExecutionException {
        list = new ArrayList<>();
        VideoParameter videoParameter = (VideoParameter) modelParameter;
        list.add(convert(videoParameter));
        if (videoParameter.isExtractThumbnail()) {
            list.add(extractThumbnail(videoParameter));
        }
        if (videoParameter.isExtractMetadata()) {
            extractMetadata(videoParameter);
            list.clear();
            list.addAll(getListMetadataCommands(this.listMetadataParameters));
        }
        return list;
    }
}
