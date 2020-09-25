package org.fundacionjala.converter.model.command;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.fundacionjala.converter.model.parameter.metadata.MetadataParameter;
import org.fundacionjala.converter.model.parameter.multimedia.VideoParameter;
import org.fundacionjala.converter.model.configPath.ConfigPath;

public class VideoModel implements ICommand<VideoParameter> {
    private List<String> listParameters;
    private List<String> listThumbnailParameters;
    private List<List<String>> list;
    private List<List<String>> listMetadataCommands;
    private List<MetadataParameter> listMetadataParameters;
    private List<String> outputFiles;
    private boolean convertedGif = false;
    private boolean convertedMp4 = false;
    private static final String MP4 = "mp4";
    private static final String GIF = "gif";
    private static final String META = "meta";
    private static final String DOT_SEPARATOR = "\\.";
    private static final String SEPARATOR = "\\";
    private static final String DOT = ".";
    private static final String DASH = "-";
    private static final String PARENTHESIS_OPEN = "(";
    private static final String PARENTHESIS_CLOSE_DOT = ").";
    private static final String NUMBER_ONE = "(1).";
    private static final String INPUT_PATH_METADATA = "storage\\convertedFiles\\";
    private static final String FORMAT = "j";    //json
    private static final String DETAIL = "v";
    private static final int ZERO = 0;
    private static final int ONE = 1;

    /**
     * Returns list of commands to convert the video to mp4 or gif
     *
     * @param videoParameter - the reference to Video Parameter
     * @return List<String> - list of commands to convert
     */
    public List<String> convert(final VideoParameter videoParameter) {
        String path = videoParameter.getInputFile();
        File file = new File(path);
        String fileName = file.getName();
        videoParameter.setFileName(fileName);
        String[] parts = fileName.split(DOT_SEPARATOR);
        //videoParameter.setFormat(parts[ONE]);
        videoParameter.setFormat(DOT_SEPARATOR + videoParameter.getExtension());
        if (videoParameter.getExtension().equals(MP4)) {
            String fullPathOutputFile = videoParameter.getOutputFile() + videoParameter.getFileName();
            videoParameter.setOutputFile(fullPathOutputFile);
            return compressToMp4(videoParameter);
        } else if (videoParameter.getExtension().equals(GIF)) {
            String fullPathOutputFile = videoParameter.getOutputFile() + parts[ZERO] + DOT + GIF;
            videoParameter.setOutputFile(fullPathOutputFile);
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
        convertedMp4 = true;
        final ConfigPath cPath = new ConfigPath();
        listParameters = new ArrayList<String>();
        listParameters.add(cPath.getVideoAudioTool());
        listParameters.add(VideoParameter.INPUT_COMMAND);
        listParameters.add(videoParameter.getInputFile());
        listParameters.add(VideoParameter.VCODEC_COMMAND);
        listParameters.add(videoParameter.getVideoCodec());
        listParameters.add(VideoParameter.ACODEC_COMMAND);
        listParameters.add(videoParameter.getAudioCodec());
        name(videoParameter);
        listParameters.add(videoParameter.getOutputFile());
        outputFiles.add(videoParameter.getOutputFile());
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
        name(videoParameter);
        listThumbnailParameters.add(videoParameter.getOutputFile());
        outputFiles.add(videoParameter.getOutputFile());
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
        String checksum = "";
        for (String path : outputFiles) {
            File file = new File(path);
            String[] parts = file.getName().split(DOT_SEPARATOR);
            String outputFile = parts[ZERO] + parts[ONE] + DASH + META;
            listMetadataParameters.add(new MetadataParameter(INPUT_PATH_METADATA + file.getName(), FORMAT, DETAIL, INPUT_PATH_METADATA + outputFile, checksum));
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
     * Returns list of parameters to convert file to gif
     * @param videoParameter - the reference to Video Parameter
     * @return List<String> - list of commands
     */
    private List<String> gif(final VideoParameter videoParameter) {
        convertedGif = true;
        final List<String> listParameters = new ArrayList<>();
        final ConfigPath cPath = new ConfigPath();
        listParameters.add(cPath.getVideoAudioTool());
        listParameters.add(VideoParameter.INPUT_COMMAND);
        listParameters.add(videoParameter.getInputFile());
        listParameters.add(VideoParameter.FRAME_RATE);
        listParameters.add(videoParameter.getFrames());
        name(videoParameter);
        listParameters.add(videoParameter.getOutputFile());
        outputFiles.add(videoParameter.getOutputFile());
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
    public List<List<String>> createCommand(final VideoParameter videoParameter)
            throws NoSuchAlgorithmException, IOException, InterruptedException, ExecutionException {
        list = new ArrayList<>();
        outputFiles = new ArrayList<String>();
        list.add(convert(videoParameter));
        if (videoParameter.isExtractThumbnail()) {
            if (convertedGif) {
                changeOutputFile(videoParameter);
            }
            if (convertedMp4) {
                File file = new File(videoParameter.getOutputFile());
                videoParameter.setFormat(DOT_SEPARATOR + GIF);
                String nameFile = file.getName();
                File pathFile = file.getParentFile();
                String newName = "";
                int index = nameFile.lastIndexOf(PARENTHESIS_OPEN);
                if (index != -ONE) {
                    nameFile = nameFile.substring(ZERO, index);
                }
                newName = pathFile.toString() + SEPARATOR + nameFile + DOT + GIF;
                videoParameter.setOutputFile(newName);
            }
            list.add(extractThumbnail(videoParameter));
        }
        if (videoParameter.isExtractMetadata()) {
            if (convertedGif) {
                changeOutputFile(videoParameter);
            }
            extractMetadata(videoParameter);
            list.addAll(getListMetadataCommands(this.listMetadataParameters));
        }
        return list;
    }

    /**
     * Changes the outputFile
     * @param videoParameter - the reference to the videoParameter given
     */
    private void changeOutputFile(final VideoParameter videoParameter) {
        for (String path : outputFiles) {
            File file = new File(path);
            File pathFile = file.getParentFile();
            String name = "";
            if (path.endsWith(GIF)) {
                String[] parts = file.getName().split(DOT_SEPARATOR);
                String nameFile = parts[ZERO];
                int index = nameFile.lastIndexOf(PARENTHESIS_OPEN);
                if (index != -ONE) {
                    int number = Integer.parseInt(nameFile.substring(index + ONE, nameFile.length() - ONE)) + ONE;
                    String nameWithouthNumber = nameFile.substring(ZERO, index);
                    name += pathFile.toString() + SEPARATOR + nameWithouthNumber + PARENTHESIS_OPEN + number + "" + PARENTHESIS_CLOSE_DOT + parts[ONE];
                } else {
                    name = pathFile.toString() + SEPARATOR + nameFile + NUMBER_ONE + parts[ONE];
                }
                videoParameter.setOutputFile(name);
            }
        }
    }
}
