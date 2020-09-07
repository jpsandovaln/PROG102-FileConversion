package org.fundacionjala.converter.model;

public abstract class Param {
    private String convertedFilesPath;
    private String inputFilesPath;

    public void setConvertedFilesPath(String convertedFilesPath) {
        this.convertedFilesPath = convertedFilesPath;
    }

    public void setInputFilesPath(String inputFilesPath) {
        this.inputFilesPath = inputFilesPath;
    }

    public String getConvertedFilesPath() {
        return convertedFilesPath;
    }

    public String getInputFilesPath() {
        return inputFilesPath;
    }
}
