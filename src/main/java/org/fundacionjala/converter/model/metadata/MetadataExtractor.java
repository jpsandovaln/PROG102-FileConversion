/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.model.metadata;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class MetadataExtractor {
    private Process process;
    @Value("${filesMetadata.path}")
    private String targetDir;
    @Value("${metadata.path}")
    private String toolPath;

    /**
     * Returns the extension of the format
     * @param format of file
     * @return a string with the extension of format
     */
    public String getExtension(final String format) {
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
                return "";
        }
    }

    /**
     * Evaluates if the format is valid
     * @param format to evaluate
     * @return a boolean
     */
    public boolean isValidFormat(final String format) {
        return (!(getExtension(format)).equals(""));
    }

    /**
     * Returns a file's metadata
     * @param pathFile file's path
     * @param exportFormat format to export the metadata
     * @param detail amount of information to extract
     * @return file's metadata
     */
    public String getMetadata(final String pathFile, final String exportFormat, final String detail) {
        String sSistemaOperativo = System.getProperty("os.name");
        String detailCommand = getDetailCommand(detail);
        if (isValidFormat(exportFormat)) {
            if ((getExtension(exportFormat)).equals("screen")) {
                return getMetadataForScreen(pathFile, detailCommand);
            } else {
                return getMetadataToExport(pathFile, exportFormat, detailCommand);
            }
        } else {
            return "Invalid format";
        }
    }

    /**
     * Returns the command for detail
     * @param detail amount of information to extract
     * @return the commando for detail
     */
    private String getDetailCommand(final String detail) {
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
     * Returns a file's metadata into another file
     * @param pathFile file's path
     * @param exportFormat format to export the metadata
     * @param detailCommand command of detail
     * @return a message
     */
    private String getMetadataToExport(final String pathFile, final String exportFormat, final String detailCommand) {
        String formatExport = "-" + exportFormat + " ";
        String redirectOutput = " > ";
        String extension = getExtension(exportFormat);
        String command = toolPath + formatExport + detailCommand + pathFile + redirectOutput + targetDir + "metadata" + extension;
        ProcessBuilder processBuilder = new ProcessBuilder("powershell", "/c", "\"" + command + "\"");
        try {
            process = processBuilder.start();
            process.waitFor();

        } catch (IOException e) {
               e.printStackTrace();
        } catch (InterruptedException e) {
               e.printStackTrace();
        }
        return "File exported success";
    }

    /**
     * Returns a file's metadata
     * @param pathFile file's path
     * @param detailCommand command of detail
     * @return a String with file's metadata
     */
    private String getMetadataForScreen(final String pathFile, final String detailCommand) {
        String command = toolPath + detailCommand + pathFile;
        System.out.println(command);
        ProcessBuilder processBuilder = new ProcessBuilder("powershell", "/c", "\"" + command + "\"");
        StringBuilder metadata = new StringBuilder();
        try {
            process = processBuilder.start();
            process.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while (reader.ready()) {
                metadata.append((char) reader.read());
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return metadata.toString();
    }
}

