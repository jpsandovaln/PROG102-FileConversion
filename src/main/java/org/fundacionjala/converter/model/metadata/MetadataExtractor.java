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
    String targetDir;
    @Value("${metadata.path}")
    String toolPath;

    public String getExtension(String format) {
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
        public boolean isValidFormat(String format) {
            return (!(getExtension(format)).equals(""));
        }

        public String getMetadata(String pathFile, String exportFormat, String detail){
            String sSistemaOperativo = System.getProperty("os.name");
            String detailCommand = getDetailCommand(detail);
            if (isValidFormat(exportFormat)) {
                if ((getExtension(exportFormat)).equals("screen")) {
                    return getMetadataForScreen(pathFile, detailCommand);
                } else {
                    return getMetadataToExport(pathFile, exportFormat, detailCommand);
                }
            }
            else {
                return "Invalid format";
            }
        }

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

    private String getMetadataToExport(String pathFile, String exportFormat, final String detailCommand) {
            String formatExport = "-" + exportFormat+" ";
            String redirectOutput = " > ";
            String extension = getExtension(exportFormat);
            String command = toolPath + formatExport + detailCommand + pathFile + redirectOutput + targetDir + "metadata" + extension;
        System.out.println(command);
            ProcessBuilder processBuilder = new ProcessBuilder("powershell", "/c","\"" + command + "\"");
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

        private String getMetadataForScreen(String pathFile, final String detailCommand) {
            String command = toolPath + detailCommand + pathFile;
            System.out.println(command);
            ProcessBuilder processBuilder = new ProcessBuilder("powershell", "/c","\"" + command + "\"");
            StringBuilder metadata = new StringBuilder();
            try {
                process = processBuilder.start();
                process.waitFor();
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                while (reader.ready()) {
                    metadata.append((char)reader.read());
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
