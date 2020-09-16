package org.fundacionjala.converter.model.parameter.multimedia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VideoParameter extends MultimediaParameter {
    private String extension;
    private String filePath;
    //private String outputFileName;
    private String pathConvertedFile;
    private String toolPath;
    private String audio = "-vn";
    public static final String VCODEC_COMMAND = "-vcodec";
    public static final String VCODEC_H264 = "h264";
    public static final String ACODEC_COMMAND = "-acodec";
    public static final String ACODEC_MP2 = "mp2";
    public static final String ACODEC_AAC = "aac";
    private String gif = "-r 12 -i ";
    public static final String FRAME_RATIO = "-r";
    public static final String CANT_FRAMES = "12";
    public static final String INPUT_COMMAND = "-i";
    //params for thumbnail
    private String time = "-ss 10 -t 5 -i ";
    private String palette = "-vf \"fps=10,scale=320:-1:flags=lanczos,split[s0][s1];[s0]palettegen[p];[s1][p]paletteuse\" -loop 0";
    private boolean extractMetadata;
    //perhaps the pathTool will be here
    public VideoParameter(String toolPath, String pathConvertedFile) {
        this.toolPath = toolPath;
        this.pathConvertedFile = pathConvertedFile;
        extractMetadata = false;
    }
    public boolean isExtractMetadata() {
        return extractMetadata;
    }

    public void setExtractMetadata(final boolean extractMetadata) {
        this.extractMetadata = extractMetadata;
    }
    public String getToolPath() {
        return toolPath;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPalette(String palette) {
        this.palette = palette;
    }

    public String getAudio() {
        return audio;
    }


    public String getTime() {
        return time;
    }

    public String getPalette() {
        return palette;
    }

    /**
     * This method return the file name
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     *
     * @param filePath
     */
    public void setFilePath(final String filePath) {
        this.filePath = filePath;
    }
    /**
     * This method setOutputFileName
     */
    public void setPathConvertedFile(final String pathConvertedFile) {
        this.pathConvertedFile = pathConvertedFile;
    }

    /**
     * This method returns getOutputFileNmae
     */
    public String getPathConvertedFile() {
        return pathConvertedFile;
    }
    //no usado
    private String[] parameter;
    VideoParameter(final String... parameter) {
        this.parameter = parameter;
    }

    /**
     * List<String> getParameter()
     */
    @Override
    protected List<String> getParameter() {
        return new ArrayList<String>(Arrays.asList(parameter));
    }
}
