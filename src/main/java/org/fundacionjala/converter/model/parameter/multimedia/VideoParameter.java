package org.fundacionjala.converter.model.parameter.multimedia;

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
    public static final String FRAME_RATE = "-r";
    private String frames;
    public static final String INPUT_COMMAND = "-i";
    //params for thumbnail
    public static final String START = "-ss";
    public static final String START_TIME = "10";
    public static final String TIME = "-t";
    public static final String DURATION = "5";
    public static final String VF = "-vf";
    public static final String PALETTE = "\"fps=10,scale=320:-1:flags=lanczos,split[s0][s1];[s0]palettegen[p];[s1][p]paletteuse\"";
    public static final String LOOP = "-loop";
    public static final String ZERO = "0";
    private boolean extractMetadata;
    private boolean extractThumbnail;

    public VideoParameter(final String toolPath, final String pathConvertedFile) {
        this.toolPath = toolPath;
        this.pathConvertedFile = pathConvertedFile;
        extractMetadata = false;
        extractThumbnail = false;
    }

    /**
     * Returns the frames per second
     * @return String
     */
    public String getFrames() {
        return frames;
    }

    /**
     * Sets the frames per second
     * @param frames
     */
    public void setFrames(final String frames) {
        this.frames = frames;
    }

    /**
     * Returns extractMetadata value
     * @return boolean
     */
    public boolean isExtractMetadata() {
        return extractMetadata;
    }

    /**
     * Sets extractMetadata value
     * @param extractThumbnail
     */
    public void setExtractMetadata(final boolean extractThumbnail) {
        this.extractMetadata = extractMetadata;
    }

    /**
     * Returns extractThumbnail value
     * @return extractThumbnail
     */
    public boolean isExtractThumbnail() {
        return extractThumbnail;
    }

    /**
     * Sets extractThumbnail value
     * @param extractThumbnail
     */
    public void setExtractThumbnail(final boolean extractThumbnail) {
        this.extractThumbnail = extractThumbnail;
    }

    /**
     * Returns the path of tool to convert video
     * @return toolPath
     */
    public String getToolPath() {
        return toolPath;
    }

    /**
     * Returns the extension to convert the video
     * @return extension
     */
    public String getExtension() {
        return extension;
    }

    /**
     * Sets the extension to convert the video
     * @param extension
     */
    public void setExtension(final String extension) {
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
}
