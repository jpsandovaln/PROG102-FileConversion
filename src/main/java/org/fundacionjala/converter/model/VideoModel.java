package org.fundacionjala.converter.model;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.fundacionjala.converter.commons.Commons;
import org.springframework.web.multipart.MultipartFile;

public class VideoModel {

  private String fileName;
  private String outputFileName;
  private Commons common;
  private VideoParameter parameter;

  public VideoModel(final MultipartFile file) throws IOException {
    common = new Commons("video");
    fileName = common.moveFileInputStorage(file);
  }

  /**
   * videoModel Compres to mp4
   *
   * @throws ExecutionException
   * @throws InterruptedException
   * @throws IOException
   */
  public void compressToMp4() throws ExecutionException, IOException, InterruptedException {
    String output = common.getStorageConvertedPath() + "demo2.mp4";
    List<String> command = parameter.COMPRESS.getParameter();
    command.add(0, common.videoExecutable());
    command.add(fileName);
    command.add(output);
    common.execute(command);
  }

  /**
   * videoModel convert to gif
   *
   * @throws ExecutionException
   * @throws InterruptedException
   * @throws IOException
   */
  public void gif() throws ExecutionException, IOException, InterruptedException {
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
   * This method setOutputFileNmae
   */
  public void setOutputFileName(final String outputFileName) {
    this.outputFileName = outputFileName;
  }

  /**
   * This method returns getOutputFileNmae
   */
  public String getOutputFileNmae() {
    return outputFileName;
  }
}
