package org.fundacionjala.converter.model.configPath;

public final class ConfigPath {
    private ConfigPath() {
        // Checkstyle :(
    }
    /**
     * Returns executable video and audio path.
     */
    public static String getVideoAudioTool() {
        return ConfigHandler.getConfigPathInstance().getValueAsString("audio.video.path");
    }

    /**
     * Returns executable ocr path.
     */
    public static String getExtractTextTool() {
        return ConfigHandler.getConfigPathInstance().getValueAsString("ocr.path");
    }

    /**
     * Returns executable meta data extractor path.
     */
    public static String getMetaDataExtractorTool() {
        return ConfigHandler.getConfigPathInstance().getValueAsString("metadata.path");
    }

    /**
     * Returns converted files path
     */
    public static String getConvertedFilesPath() {
        return ConfigHandler.getConfigPathInstance().getValueAsString("convertedFiles.path");
    }

    /**
     * Returns executable image tool.
     */
    public static String getImageTool() {
        return ConfigHandler.getConfigPathInstance().getValueAsString("image.path");
    }

    /**
     * Returns tmp file
     */
    public static String getTmpFilePath() {
        return ConfigHandler.getConfigPathInstance().getValueAsString("tempFiles.path");
    }

    /**
     * Returns inputFile file path
     */
    public static String getInputFile() {
        return ConfigHandler.getConfigPathInstance().getValueAsString("inputFiles.path");
    }

    /**
     * Returns tessDataDir
     */
    public static String getTesstDataDir() {
        return ConfigHandler.getConfigPathInstance().getValueAsString("ocr.tessdata.path");
    }
}
