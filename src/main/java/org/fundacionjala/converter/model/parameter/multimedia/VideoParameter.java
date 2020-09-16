package org.fundacionjala.converter.model.parameter.multimedia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VideoParameter extends MultimediaParameter {
    private String extension;
    private String filePath;
    private String pathConvertedFile;
    private String toolPath;
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
    public static final String START = "-ss";
    public static final String START_TIME = "10";
    public static final String TIME = "-t";
    public static final String DURATION = "5";
    //private String palette = "-vf \"fps=10,scale=320:-1:flags=lanczos,split[s0][s1];[s0]palettegen[p];[s1][p]paletteuse\" -loop 0";
    public static final String VF = "-vf";
    public static final String PALETTE = "\"fps=10,scale=320:-1:flags=lanczos,split[s0][s1];[s0]palettegen[p];[s1][p]paletteuse\"";
    public static final String LOOP = "-loop";
    public static final String ZERO = "0";
    private boolean extractMetadata;
    private boolean extractThumbnail;
    //perhaps the pathTool will be here
    public VideoParameter(String toolPath, String pathConvertedFile) {
        this.toolPath = toolPath;
        this.pathConvertedFile = pathConvertedFile;
        extractMetadata = false;
        extractThumbnail = false;
    }
    public boolean isExtractMetadata() {
        return extractMetadata;
    }

    public void setExtractMetadata(final boolean extractThumbnail) {
        this.extractMetadata = extractMetadata;
    }
    public boolean isExtractThumbnail() {
        return extractThumbnail;
    }

    public void setExtractThumbnail(final boolean extractThumbnail) {
        this.extractThumbnail = extractThumbnail;
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
