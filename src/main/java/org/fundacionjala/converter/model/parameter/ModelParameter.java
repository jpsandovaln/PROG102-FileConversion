package org.fundacionjala.converter.model.parameter;

import java.util.List;

public abstract class ModelParameter {
  private String inputFile;
  private String outputFile;
  private String md5;


  public ModelParameter() {
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

  /**
   * @param inputFile the inputFile to set
   */
  public void setInputFile(final String inputFile) {
    this.inputFile = inputFile;
  }

  /**
   * @param outputFile the outputFile to set
   */
  public void setOutputFile(final String outputFile) {
    this.outputFile = outputFile;
  }
}
