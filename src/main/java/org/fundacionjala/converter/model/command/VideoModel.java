package org.fundacionjala.converter.model.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.fundacionjala.converter.model.parameter.ModelParameter;
import org.fundacionjala.converter.model.parameter.multimedia.VideoParameter;

public class VideoModel implements ICommand {
    private List<String> listParams;
    private List<String> listThumbnailParams;
    private CommandBuilder commandBuilder;
    List<List<String>> list;
    public VideoModel() throws IOException {
        listParams = new ArrayList<String>();
        listThumbnailParams = new ArrayList<String>();
        commandBuilder = new CommandBuilder();
        list = new ArrayList<>();
    }

    /**
   * videoConverter
   *
   * @throws ExecutionException
   * @throws InterruptedException
   * @throws IOException
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
   * @throws ExecutionException
   * @throws InterruptedException
   * @throws IOException
   */
    private List<String> compressToMp4(final VideoParameter parameter) {
        listParams.clear();
        listParams.add(parameter.getToolPath());
        listParams.add(VideoParameter.VCODEC_COMMAND);
        listParams.add(VideoParameter.VCODEC_H264);
        listParams.add(VideoParameter.ACODEC_COMMAND);
        listParams.add(VideoParameter.ACODEC_AAC);
        listParams.add(VideoParameter.INPUT_COMMAND);
        listParams.add(parameter.getFilePath());
        listParams.add(parameter.getPathConvertedFile() + "demo.mp4");
        return listParams;
    }

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
    /*String output = common.getStorageConvertedPath() + "/thumbnail.gif";
    List<String> command = parameter.TIME.getParameter();
    command.add(0, common.videoExecutable());
    command.add(fileName);
    for (String param: parameter.PALETTE.getParameter()) {
      command.add(param);
    }
    //command.add(parameter.PALETTE.getParameter());
    System.out.println(command);
    command.add(output);
    common.execute(command);
    setOutputFileName(output);*/
    }
    /**
    * videoModel convert to gif
    *
    * @throws ExecutionException
    * @throws InterruptedException
    * @throws IOException
    */
    private List<String> gif(final VideoParameter parameter) {
        listParams.clear();
        listParams.add(parameter.getToolPath());
        listParams.add(VideoParameter.FRAME_RATIO);
        listParams.add(VideoParameter.CANT_FRAMES);
        listParams.add(VideoParameter.INPUT_COMMAND);
        listParams.add(parameter.getFilePath());
        listParams.add(parameter.getPathConvertedFile() + "demo.gif");
        return listParams;
    }

    /**
     * create command
     * @return list of commands
     */
    public List<List<String>> createCommand(ModelParameter modelParameter) {
        list.clear();
        VideoParameter parameter = (VideoParameter) modelParameter;
        list.add(convert(parameter));
        if (parameter.isExtractThumbnail()) {
            list.add(extractThumbnail(parameter));
        }
        if (parameter.isExtractMetadata()) {
            //list.add(extractMetadata(parameter));
        }
        System.out.println(list);
        return list;
    }
}
