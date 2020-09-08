/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.model.metadata;
import org.fundacionjala.converter.model.Param;

/**
 * @author Angela Martinez
 * @version 0.1
 */
public class MetadataParam extends Param {
    public static final String REDIRECTION = " > ";
    private String filePath;
    private String formatExport;
    private String formatExportCommand;
    private String detail;
    private String fileName = "metadata";
    private String targetDir;
    private String toolPath;

    /**
     * Sets the name of the metadata file
     * @param fileName
     */
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * Sets the path of the directory where the metadata will be stored
     * @param targetDir
     */
    public void setTargetDir(final String targetDir) {
        this.targetDir = targetDir;
    }

    /**
     * Sets the path where the tool is saved
     * @param toolPath
     */
    public void setToolPath(final String toolPath) {
        this.toolPath = toolPath;
    }

    /**
     * Sets format to export metadata
     * @param formatExportCommand
     */
    public void setFormatExportCommand(final String formatExportCommand) {
        this.formatExportCommand = formatExportCommand;
    }

    /**
     * Returns the name of the metadata file
     * @return
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Returns the path of file
     * @return
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Returns the format to export metadata
     * @return
     */
    public String getFormatExport() {
        return formatExport;
    }

    /**
     * Returns parameter for detail
     * @return
     */
    public String getDetail() {
        return detail;
    }

    /**
     * Returns the path where the metadata will be saved
     * @return
     */
    public String getTargetDir() {
        return targetDir;
    }

    /**
     * Returns the path where the tool is saved
     * @return
     */
    public String getToolPath() {
        return toolPath;
    }


    /**
     * Returns the extension of the format
     * @param format of file
     * @return a string with the extension of format
     */
    public String getFormatExport(final String format) {
        switch (format) {
            case "j":
                return ".json";
            case "h":
                return ".html";
            case "t":
                return ".txt";
            case "T":
                return ".csv";
            default:
                return ".xmp";
        }
    }

    /**
     * Returns the parameter to define the type of format to export the metadata
     * @param format
     * @return
     */
    public String getFormatExportCommand(final String format) {
        switch (format) {
            case "j":
                return "-j ";
            case "h":
                return "-h ";
            case "t":
                return "-t ";
            case "T":
                return "-T ";
            default:
                return " ";
        }
    }

    /**
     * Returns the format to export metadata
     * @return
     */
    public String getFormatExportCommand() {
        return formatExportCommand;
    }

    /**
     * Returns the parameter for detail
     * @param detail amount of information to extract
     * @return the commando for detail
     */
    public String getDetail(final String detail) {
        switch (detail) {
            case "v":
                return "-v ";
            case "c":
                return "-common ";
            default:
                return " ";
        }
    }

    /**
     * Sets format to export metadata
     * @param formatExport
     */
    public void setFormatExport(final String formatExport) {
        this.formatExport = formatExport;
    }

    /**
     * Sets details
     * @param detail
     */
    public void setDetail(final String detail) {
        this.detail = detail;
    }

    /**
     * Sets file's path
     * @param filePath
     */
    public void setFilePath(final String filePath) {
        this.filePath = filePath;
    }
}
