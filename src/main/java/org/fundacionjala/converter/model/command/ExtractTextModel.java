package org.fundacionjala.converter.model.command;

/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.fundacionjala.converter.model.configPath.ConfigPath;
import org.fundacionjala.converter.model.parameter.ModelParameter;

/**
 * @author Jhordan Soto
 * @version 1.0
 */
public class ExtractTextModel implements ICommand {

    private String language;
    private String type;
    private ModelParameter eTextParameter;

    public ExtractTextModel(String language, String type, ModelParameter modelParameter) {
        this.language = language;
        this.type = type;
        this.eTextParameter = modelParameter;
    }

    public void convertDocument(){
        ConvertDoc cDoc = new ConvertDoc();
        String result;
        result = readAFile(eTextParameter.getOutputFile() + ".txt");
        if (type == "word" || type == "pdf"|| type == "SS") {
            try {
                Files.delete(Paths.get(eTextParameter.getOutputFile() + ".txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        cDoc.createDocumentWord(eTextParameter.getOutputFile(), result);
    }

    public String selectLanguage(final String language) {
        String result;
        switch (language) {
            case "español":
                result = "-l spa";
                break;
            case "english":
                result = "";
                break;
            default:
                String advertisement = "Sorry language not supported we are going to do it in english";
                System.out.println(advertisement);
                result = "";
        }
        return result;
    }

    public String selectExtension(final String type) {
        String result;
        switch (type) {
            case "word":
                result = ".docx";
                break;
            case "pdf":
                result = ".pdf";
                break;
            case "SS":
                result = ".txt";
                break;
            case "text":
                result = ".txt";
                break;
            default:
                result = ".txt";
                result = "Format not supported please insert a valid format";
                break;
        }
        return result;
    }

    /**
     * read a file
     * @param fileName the path and name of the file
     * @return the content of the file in a string
     */
    public String readAFile(final String fileName) {
        BufferedReader buffer = null;
        String readString = "";
        try {
            String sCurrentLine;
            buffer = new BufferedReader(new FileReader(fileName));
            while ((sCurrentLine = buffer.readLine()) != null) {
                readString = readString + sCurrentLine;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (buffer != null) {
                    buffer.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return readString;
    }

    /**
     * create command
     * @return list of commands
     */
    @Override
    public List<List<String>> createCommand(final ModelParameter modelParameter) {
        List<List<String>> listCommands = new ArrayList<>();
        ConfigPath cPath = new ConfigPath();
        List<String> command = new ArrayList<String>();
        command.add(cPath.getExtractTextTool());
        command.add(eTextParameter.getInputFile());    //add source
        command.add(eTextParameter.getOutputFile());   //add target
        command.add(selectLanguage(language));   //add language
        listCommands.add(command);
        return listCommands;
    }
}
