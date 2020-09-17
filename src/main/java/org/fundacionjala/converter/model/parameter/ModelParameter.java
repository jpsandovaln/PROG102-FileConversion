package org.fundacionjala.converter.model.parameter;

import java.io.IOException;

public abstract class ModelParameter {
  private String inputFile;
  private String outputFile;
  private String md5;

  public ModelParameter() {
  }

  public ModelParameter(final String inputFile, final String outputFile, final String md5) {
    this.inputFile = inputFile;
    this.outputFile = outputFile;
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
   * @param inputFile the inputFile to set
   */
  public void setInputFile(final String inputFile) throws IOException {
    this.inputFile = inputFile;
  }

  /**
   * @param outputFile the outputFile to set
   */
  public void setOutputFile(final String outputFile) {
    this.outputFile = outputFile;
  }
}
