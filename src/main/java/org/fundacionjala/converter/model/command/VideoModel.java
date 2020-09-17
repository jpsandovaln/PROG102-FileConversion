package org.fundacionjala.converter.model.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.fundacionjala.converter.model.parameter.ModelParameter;
import org.fundacionjala.converter.model.parameter.multimedia.VideoParameter;

public class VideoModel implements ICommand {
    private List<String> listParams;
    private List<String> listThumbnailParams;
    private CommandBuilder commandBuilder;
    private List<List<String>> list;
    public VideoModel() throws IOException {
        listParams = new ArrayList<String>();
        listThumbnailParams = new ArrayList<String>();
        commandBuilder = new CommandBuilder();
        list = new ArrayList<>();
    }

    /**
   * videoConverter
   *
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
   * videoModel Compress to mp4
   *
   */
    private List<String> compressToMp4(final VideoParameter parameter) {
        listParams.clear();
        listParams.add(parameter.getToolPath());
        listParams.add(VideoParameter.VCODEC_COMMAND);
        listParams.add(parameter.getVCodec());
        listParams.add(VideoParameter.ACODEC_COMMAND);
        listParams.add(parameter.getACodec());
        listParams.add(VideoParameter.INPUT_COMMAND);
        listParams.add(parameter.getFilePath());
        listParams.add(parameter.getPathConvertedFile() + "demo.mp4");
        return listParams;
    }

    /**
     * Returns a list with the commands to extract thumbnail
     * @param parameter
     * @return
     */
    public List<String> extractThumbnail(final VideoParameter parameter) {
        listThumbnailParams.clear();
        listThumbnailParams.add(parameter.getToolPath());
        listThumbnailParams.add(VideoParameter.START);
        listThumbnailParams.add(VideoParameter.START_TIME);
        listThumbnailParams.add(VideoParameter.TIME);
        listThumbnailParams.add(VideoParameter.DURATION);
        listThumbnailParams.add(VideoParameter.INPUT_COMMAND);
        listThumbnailParams.add(parameter.getFilePath());
        listThumbnailParams.add(VideoParameter.VF);
        listThumbnailParams.add(VideoParameter.PALETTE);
        listThumbnailParams.add(VideoParameter.LOOP);
        listThumbnailParams.add(VideoParameter.ZERO);
        listThumbnailParams.add(parameter.getPathConvertedFile() + "thumbnail.gif");
        return listThumbnailParams;
    }

    /**
     * Returns a list with the commands to extract metadata
     * @param parameter
     * @return
     */
    public List<String> extractMetadata(final VideoParameter parameter) {
        //do something
        return listParams;
    }

    /**
    * videoModel convert to gif
    *
    */
    private List<String> gif(final VideoParameter parameter) {
        listParams.clear();
        listParams.add(parameter.getToolPath());
        listParams.add(VideoParameter.FRAME_RATE);
        listParams.add(parameter.getFrames());
        listParams.add(VideoParameter.INPUT_COMMAND);
        listParams.add(parameter.getFilePath());
        listParams.add(parameter.getPathConvertedFile() + "demo.gif");
        return listParams;
    }

    /**
     * create command
     * @return list of commands
     */
    public List<List<String>> createCommand(final ModelParameter modelParameter) {
        list.clear();
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
