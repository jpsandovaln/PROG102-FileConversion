package org.fundacionjala.converter.model.command;

import java.util.ArrayList;
import java.util.List;

import org.fundacionjala.converter.model.parameter.ModelParameter;
import org.fundacionjala.converter.model.parameter.multimedia.VideoParameter;
import org.fundacionjala.converter.model.configPath.ConfigPath;

public class VideoModel implements ICommand {
    private List<String> listParams;
    private List<String> listThumbnailParams;
    private List<List<String>> list;

    /**
     * Returns list of commands to convert the video to mp4 or gif
     * @param parameter - the reference to Video Parameter
     * @return List<String> - list of commands to convert
     */
    public List<String> convert(final VideoParameter parameter) {
        if (parameter.getExtension().equals("mp4")) {
            return compressToMp4(parameter);
        } else if (parameter.getExtension().equals("gif")) {
            return gif(parameter);
        }
        return null;
    }

    /**
     * Compress video to mp4
     * @param parameter
     * @return List<String> - list of commands to convert
     */
    private List<String> compressToMp4(final VideoParameter parameter) {
        ConfigPath cPath = new ConfigPath();
        listParams = new ArrayList<String>();
        listParams.add(cPath.getVideoAudioTool());
        listParams.add(VideoParameter.VCODEC_COMMAND);
        listParams.add(parameter.getVCodec());
        listParams.add(VideoParameter.ACODEC_COMMAND);
        listParams.add(parameter.getACodec());
        listParams.add(VideoParameter.INPUT_COMMAND);
        listParams.add(parameter.getInputFile());
        listParams.add(parameter.getOutputFile() + "demo.mp4");
        return listParams;
    }

    /**
     * Returns a list of commands to extract thumbnail
     * @param parameter - the reference to Video Parameter
     * @return List<String> - list of commands
     */
    private List<String> extractThumbnail(final VideoParameter parameter) {
        listThumbnailParams = new ArrayList<>();
        ConfigPath cPath = new ConfigPath();
        listThumbnailParams.add(cPath.getVideoAudioTool());
        listThumbnailParams.add(VideoParameter.START);
        listThumbnailParams.add(VideoParameter.START_TIME);
        listThumbnailParams.add(VideoParameter.TIME);
        listThumbnailParams.add(VideoParameter.DURATION);
        listThumbnailParams.add(VideoParameter.INPUT_COMMAND);
        listThumbnailParams.add(parameter.getInputFile());
        listThumbnailParams.add(VideoParameter.VF);
        listThumbnailParams.add(VideoParameter.PALETTE);
        listThumbnailParams.add(VideoParameter.LOOP);
        listThumbnailParams.add(VideoParameter.ZERO);
        listThumbnailParams.add(parameter.getOutputFile() + "thumbnail.gif");
        return listThumbnailParams;
    }

    /**
     * Returns list of commands to extract metadata
     * @param parameter - the reference to Video Parameter
     * @return List<String> - list of commands
     */
    private List<String> extractMetadata(final VideoParameter parameter) {
        //do something
        return listParams;
    }

    /**
     * Returns list of parameters to convert file to gif
     * @param parameter - the reference to Video Parameter
     * @return List<String> - list of commands
     */
    private List<String> gif(final VideoParameter parameter) {
        List<String> listParams = new ArrayList<>();
        ConfigPath cPath = new ConfigPath();
        listParams.add(cPath.getVideoAudioTool());
        listParams.add(VideoParameter.FRAME_RATE);
        listParams.add(parameter.getFrames());
        listParams.add(VideoParameter.INPUT_COMMAND);
        listParams.add(parameter.getInputFile());
        listParams.add(parameter.getOutputFile() + "demo.gif");
        return listParams;
    }

    /**
     * Creates command
     * @return list of commands
     */
    public List<List<String>> createCommand(final ModelParameter modelParameter) {
        list = new ArrayList<>();
        VideoParameter parameter = (VideoParameter) modelParameter;
        list.add(convert(parameter));
        if (parameter.isExtractThumbnail()) {
            list.add(extractThumbnail(parameter));
        }
        if (parameter.isExtractMetadata()) {
            list.add(extractMetadata(parameter));
        }
        return list;
    }
}
