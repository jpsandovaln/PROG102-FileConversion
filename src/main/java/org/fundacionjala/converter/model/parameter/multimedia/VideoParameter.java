package org.fundacionjala.converter.model.parameter.multimedia;

public class VideoParameter extends MultimediaParameter {
    private String extension;
    public static final String VCODEC_COMMAND = "-vcodec";
    private String videoCodec;
    public static final String ACODEC_COMMAND = "-acodec";
    public static final String ACODEC_MP2 = "mp2";
    private String audioCodec;
    public static final String FRAME_RATE = "-r";
    private String frames;
    public static final String INPUT_COMMAND = "-i";
    public static final String START = "-ss";
    public static final String START_TIME = "10";
    public static final String TIME = "-t";
    public static final String DURATION = "5";
    public static final String VF = "-vf";
    public static final String PALETTE = "\"fps=10,scale=320:-1:flags=lanczos,split[s0][s1];[s0]palettegen[p];[s1][p]paletteuse\"";
    public static final String LOOP = "-loop";
    public static final String ZERO = "0";
    private boolean extractMetadata = false;
    private boolean extractThumbnail = false;

    /**
     * Sets video codec
     * @param videoCodec
     */
    public void setVideoCodec(final String videoCodec) {
        this.videoCodec = videoCodec;
    }

    /**
     * Gets audio codec
     * @param audioCodec
     */
    public void setAudioCodec(final String audioCodec) {
        this.audioCodec = audioCodec;
    }

    /**
     * Gets video codec
     * @return videoCodec
     */
    public String getVideoCodec() {
        return videoCodec;
    }

    /**
     * Gets audio codec
     * @return audioCodec
     */
    public String getAudioCodec() {
        return audioCodec;
    }

    /**
     * Returns the frames per second
     * @return String - the frames
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
     * @return boolean - the reference to extractMetadata of this object
     */
    public boolean isExtractMetadata() {
        return extractMetadata;
    }

    /**
     * Sets extractMetadata value
     * @param extractMetadata
     */
    public void setExtractMetadata(final boolean extractMetadata) {
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
     * Returns the extension to convert the video
     * @return extension
     */
    public String getExtension() {
        return extension;
    }

    /**
     * Sets the extension to convert the video
     * @param extension - the extension to set
     */
    public void setExtension(final String extension) {
        this.extension = extension;
    }
}
