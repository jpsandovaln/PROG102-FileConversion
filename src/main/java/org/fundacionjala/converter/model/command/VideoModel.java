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
    String output = common.getStorageConvertedPath() + "/demo.mp4";
    List<String> command = parameter.COMPRESS.getParameter();
    command.add(0, common.videoExecutable());
    command.add(fileName);
    command.add(output);
    System.out.println(command);
    common.execute(command);
    setOutputFileName(output);
  }

  public void extractThumbnail() throws ExecutionException, IOException, InterruptedException {
    String output = common.getStorageConvertedPath() + "/thumbnail.gif";
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
    String output = common.getStorageConvertedPath() + "/demo.gif";
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

  public static void main(String []args) {
    try {
      VideoModel video = new VideoModel();
      video.setInputFileName("storage/inputFiles/lesson-2a-medium.mov");
      video.extractThumbnail();
    } catch (Exception e) {
      e.printStackTrace();
    }
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
