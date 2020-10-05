package org.fundacionjala.converter.model.parameter;

import org.fundacionjala.converter.model.utility.ChecksumMD5;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public abstract class ModelParameter {
  private String inputFile;
  private String outputFile;
  private String md5;
  private String format;

  public ModelParameter() {
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
   * Sets md5 value
   * @param md5
   */
  public void setMd5(final String md5) {
    this.md5 = md5;
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
  public void setFormat(final String format) throws NullPointerException {
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
    String getMd5 = null;
    ChecksumMD5 checksumMD5 = new ChecksumMD5();
    try {
      getMd5 = checksumMD5.getMD5(inputFile);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return getMd5;
  }

  /**
   * Sets inputFile value
   * @param inputFile the inputFile to set
   * @throws IOException
   */
  public void setInputFile(final String inputFile) throws NullPointerException, IOException {
    this.inputFile = inputFile;
  }

  /**
   * Sets outputFile value
   * @param outputFile the outputFile to set
   */
  public void setOutputFile(final String outputFile)  throws NullPointerException {
    this.outputFile = outputFile;
  }
}
