package org.fundacionjala.converter.model.parameter;

import java.util.List;

public abstract class ModelParameter {
  private String inputFile;
  private String outputFile;
  private String md5;

  /**
   * setInputFile
   */
  public void setInputFile(final String inputFile) {
    this.inputFile = inputFile;
  }

  /**
   * setOutputFile
   */
  public void setOutputFile(final String outputFile) {
    this.outputFile = outputFile;
  }

  /**
   * setMd5
   */
  public void setMd5(final String md5) {
    this.md5 = md5;
  }

  /**
   * getInputFile
   */
  public String getInputFile() {
    return inputFile;
  }

  /**
   * getOutputFile
   */
  public String getOutputFile() {
    return outputFile;
  }

  /**
   * getMd5
   */
  public String getMd5() {
    return md5;
  }

  /**
   * List<String>
   */
  protected abstract List<String> getParameter();
}
