package org.fundacionjala.converter.model.parameter;

import java.io.IOException;

public abstract class ModelParameter {
  private String inputFile;
  private String outputFile;
  private String md5;
  private String format;

  public ModelParameter() {
  }
  /**
   * Sets  value
   * @param md5
   */
  public void setMd5(final String md5) {
    this.md5 = md5;
  }
  public ModelParameter(final String inputFile, final String outputFile, final String md5) {
    this.inputFile = inputFile;
    this.outputFile = outputFile;
    this.md5 = md5;
  }

  public ModelParameter(final String inputFile, final String outputFile, final String md5, final String format) {
    this.inputFile = inputFile;
    this.outputFile = outputFile;
    this.md5 = md5;
    this.format = format;
  }

  /**
   * @return format
   */
  public String getFormat() {
    return format;
  }

  /**
   * Sets format value
   * @param format the format to set
   */
  public void setFormat(final String format) {
    this.format = format;
  }

  /**
   * @return inputFile
   */
  public String getInputFile() {
    return inputFile;
  }

  /**
   * @return outputFile
   */
  public String getOutputFile() {
    return outputFile;
  }

  /**
   * @return md5
   */
  public String getMd5() {
    return md5;
  }

  /**
   * Sets inputFile value
   * @param inputFile the inputFile to set
   */
  public void setInputFile(final String inputFile) throws IOException {
    this.inputFile = inputFile;
  }

  /**
   * Sets outputFile value
   * @param outputFile the outputFile to set
   */
  public void setOutputFile(final String outputFile) {
    this.outputFile = outputFile;
  }
}
