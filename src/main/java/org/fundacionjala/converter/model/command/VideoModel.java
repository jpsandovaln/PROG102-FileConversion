package org.fundacionjala.converter.model.command;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.fundacionjala.converter.model.commons.Commons;

public class VideoModel implements ICommand {

  private String fileName;
  private String outputFileName;
  private Commons common;
  private VideoParameter parameter;

  public VideoModel() throws IOException {
    common = new Commons("video");
  }

  /**
   * videoConverter
   *
   * @throws ExecutionException
   * @throws InterruptedException
   * @throws IOException
   */
  public void videoConverter(final String ext) throws ExecutionException, IOException, InterruptedException {
    if (ext.equals("mp4")) {
      this.compressToMp4();
    } else if (ext.equals("gif")) {
      this.gif();
    }
  }

  /**
   * videoModel Compres to mp4
   *
   * @throws ExecutionException
   * @throws InterruptedException
   * @throws IOException
   */
  private void compressToMp4() throws ExecutionException, IOException, InterruptedException {
    String output = common.getStorageConvertedPath() + "demo2.mp4";
    List<String> command = parameter.COMPRESS.getParameter();
    command.add(0, common.videoExecutable());
    command.add(fileName);
    command.add(output);
    common.execute(command);
    setOutputFileName(output);
  }

  /**
   * videoModel convert to gif
   *
   * @throws ExecutionException
   * @throws InterruptedException
   * @throws IOException
   */
  private void gif() throws ExecutionException, IOException, InterruptedException {
    String output = common.getStorageConvertedPath() + "demo2.gif";
    List<String> command = parameter.GIF.getParameter();
    command.add(0, common.videoExecutable());
    command.add(fileName);
    command.add(output);
    common.execute(command);
    setOutputFileName(output);
  }
  /**
   * This method return the file name
   */
  public String getFileName() {
    return fileName;
  }

  /**
   * This method setOutputFileName
   */
  public void setOutputFileName(final String outputFileName) {
    this.outputFileName = outputFileName;
  }

  /**
   * This method setInputFileName
   */
  public void setInputFileName(final String inputFileName) {
    this.fileName = inputFileName;
  }

  /**
   * This method returns getOutputFileNmae
   */
  public String getOutputFileName() {
    return outputFileName;
  }

  /**
   * create command
   * @return list of commands
   */
  @Override
  public List<String> createCommand() {
    return null;
  }
}
