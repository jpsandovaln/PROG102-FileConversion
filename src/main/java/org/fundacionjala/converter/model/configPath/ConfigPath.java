package org.fundacionjala.converter.model.configPath;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigPath {
    private static Properties properties;

    public ConfigPath(final String folder) {
        loadConfiguration();
    }

    /**
    * Loads the application.properties configurations.
    */
    public void loadConfiguration() {
        properties = new Properties();
        InputStream inputPath = ConfigPath.class.getClassLoader().getResourceAsStream("application.properties");
        try {
            properties.load(inputPath);
        } catch (IOException e) {
          e.printStackTrace();
        }
    }

    /**
      * Returns executable video and audio path.
      */
    public String getVideoAudioTool() {
        return properties.getProperty("audio.video.path");
    }

    /**
    * Returns executable ocr path.
    */
    public String getToolExtactor() {
        return properties.getProperty("ocr.path");
    }

    /**
      * Returns executable meta data extractor path.
    */
    public String getMetaDataExtractorTool() {
        return properties.getProperty("metadata.path");
    }

    /**
     * Returns Converted Files storage path.
    */
    public String getConvertedFilesPath() {
      return properties.getProperty("convertedFiles.path");
    }
}
