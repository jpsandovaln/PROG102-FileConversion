package org.fundacionjala.converter.model.commons;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class Commons {

  private String folder;
  private InputStream inputPath;
  private Properties properties;

  public Commons(final String folder) {
    this.folder = folder + File.separator;
    loadConfiguration();
  }

  /**
   * Loads the application.properties configurations.
   */
  public void loadConfiguration() {
    properties = new Properties();
    inputPath = Commons.class.getClassLoader().getResourceAsStream("application.properties");
    try {
      properties.load(inputPath);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Returns user path storage.
   */
  public String getStoragePath() {
    return properties.getProperty("inputFiles.path") + folder;
  }

  /**
   * Returns converted files path storage.
   */
  public String getStorageConvertedPath() {
    return properties.getProperty("convertedFiles.path") + folder;
  }

  /**
   * Returns executable video path.
   */
  public String videoExecutable() {
    return properties.getProperty("audio.video.path");
  }

  /**
   * This method execute the command.
   *
   * @param command list of the commands
   * @return status of the execution
   * @throws ExecutionException
   * @throws IOException
   * @throws InterruptedException
   */
  public void execute(final List<String> command) throws ExecutionException, IOException, InterruptedException {
      Process processDuration = new ProcessBuilder(command).redirectErrorStream(true).start();
      StringBuilder outPut = new StringBuilder();
      BufferedReader processOutputReader = new BufferedReader(new InputStreamReader(processDuration.getInputStream()));
      String line;
      while ((line = processOutputReader.readLine()) != null) {
        outPut.append(line + System.lineSeparator());
      }
      processDuration.waitFor();
  }
}
