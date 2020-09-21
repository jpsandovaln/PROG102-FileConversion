/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.model.command.extractText;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.fundacionjala.converter.executor.Executor;
import org.fundacionjala.converter.model.utility.ConvertDoc;
import org.fundacionjala.converter.model.command.ICommand;
import org.fundacionjala.converter.model.configPath.ConfigPath;
import org.fundacionjala.converter.model.parameter.extractText.ExtractTextParameter;

/**
 * @author Jhordan Soto
 * @version 1.0
 */
public class ExtractTextModel implements ICommand<ExtractTextParameter> {
    private ConvertDoc convertDoc;
    private ReaderText reader;
    public static final String TXT_EXTENSION = ".txt";

    public ExtractTextModel() {
        convertDoc = new ConvertDoc();
        reader = new ReaderText();
    }

    /**
     * Creates command
     * @return list of commands
     */
    @Override
    public List<List<String>> createCommand(final ExtractTextParameter parameter) {
        List<List<String>> listCommands = new ArrayList<>();
        listCommands.add(extractText(parameter));
        return listCommands;
    }

    /**
     * Extracts text from image
     * @param parameter
     * @return
     */
    private List<String> extractText(final ExtractTextParameter parameter) {
        ConfigPath cPath = new ConfigPath();
        List<String> command = new ArrayList<String>();
        command.add(cPath.getExtractTextTool());
        command.add(parameter.LANG_COMMAND);
        command.add(parameter.getLanguage());
        command.add(parameter.getInputFile());
        command.add(parameter.getOutputFile() + parameter.getFileName());
        return command;
    }

    /**
     * Create a document
     * @param parameter - the parameter to execute the conversion using tesseract
     */
    /*
    public void createDocument(final ExtractTextParameter parameter) throws Exception {
        List<List<String>> commandList = createCommand(parameter);
        Executor executor = new Executor();
        try {
            executor.executeCommandsList(commandList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DocType type = parameter.getType();
        String outputFile = parameter.getOutputFile() + parameter.getFileName();
        String result = reader.readFile(outputFile + TXT_EXTENSION);
        if (!type.equals(DocType.TXT)) {
           eraseDocument(type, outputFile);
        }
        if (type.equals(DocType.PDF)) {
            convertDoc.createDocumentPdf(outputFile, result);
        }
        if (type.equals(DocType.DOCX)) {
            convertDoc.createDocumentWord(outputFile, result);
        }
    }

    /**
     * Erase the txt when the type is word or pdf
     * @param type
     * @param outputFile
     */
    /*
    private void eraseDocument(final DocType type, final String outputFile) throws Exception {
        if (type.equals(DocType.DOCX) || type.equals(DocType.PDF) || type.equals(DocType.TXT)) {
            Files.delete(Paths.get(outputFile + TXT_EXTENSION));
        }
    }*/
}
