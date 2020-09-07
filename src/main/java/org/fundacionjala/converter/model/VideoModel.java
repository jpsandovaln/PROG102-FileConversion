package org.fundacionjala.converter.model;

import java.util.List;

import org.fundacionjala.converter.commons.Commons;
import org.springframework.web.multipart.MultipartFile;

public class VideoModel {

  private String fileName;
  private String outputFileName;
  private Commons common;
  private VideoParameter parameter;

  public VideoModel(final MultipartFile file) {
    common = new Commons("video");
    fileName = common.moveFileInputStorage(file);
  }

  /**
   * videoModel Compres to mp4
   */
  public boolean compresToMp4() {
    String output = common.getStorageConvertedPath() + "demo2.mp4";
    List<String> command = parameter.COMPRESS.getParameter();
    command.add(0, common.videoExecutable());
    command.add(fileName);
    command.add(output);
    if (common.execute(command)) {
      setOutputFileNmae(output);
      return true;
    }
    return false;
  }

  /**
   * videoModel convert to gif
   */
  public boolean gif() {
    String output = common.getStorageConvertedPath() + "demo2.gif";
    List<String> command = parameter.GIF.getParameter();
    command.add(0, common.videoExecutable());
    command.add(fileName);
    command.add(output);

    command.stream().forEach(value -> {
      System.out.println(value);
    });
    if (common.execute(command)) {
      setOutputFileNmae(output);
      return true;
    }
    return false;
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
  public void setOutputFileNmae(final String outputFileName) {
    this.outputFileName = outputFileName;
  }

  /**
   * This method returns getOutputFileNmae
   */
  public String getOutputFileNmae() {
    return outputFileName;
  }
}
