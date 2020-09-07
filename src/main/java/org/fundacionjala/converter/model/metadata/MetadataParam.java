package org.fundacionjala.converter.model.metadata;

import org.fundacionjala.converter.model.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MetadataParam extends Param {
    public static final String REDIRECTION = " > ";
    private String filePath;
    private String formatExport;
    private String formatExportCommand;
    private String detail;
    private String fileName = "metadata";
    //@Value("${filesMetadataPath}")
    //private String fileMetadataPath;
    private String targetDir = "storage/convertedFiles/metadata/";
    //@Value("${metadata.path}")
    private String toolPath = "thirdParty/exiftool/exiftool.exe ";

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setTargetDir(String targetDir) {
        this.targetDir = targetDir;
    }

    public void setToolPath(String toolPath) {
        this.toolPath = toolPath;
    }
    public void setFormatExportCommand(String formatExportCommand) {
        this.formatExportCommand = formatExportCommand;
    }

    public String getFileName() {
        return fileName;
    }

    public static String getREDIRECTION() {
        return REDIRECTION;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFormatExport() {
        return formatExport;
    }

    public String getDetail() {
        return detail;
    }

    public String getTargetDir() {
        System.out.println(targetDir);
        return targetDir;
    }

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
            case "s":
                return "screen";
            default:
                return ".xmp";
        }
    }
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

    public String getFormatExportCommand() {
            return formatExportCommand;
    }
    /**
     * Returns the command for detail
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

    public void setFormatExport(String formatExport) {
        this.formatExport = formatExport;
    }

    public void setDetail(String detail) {
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
