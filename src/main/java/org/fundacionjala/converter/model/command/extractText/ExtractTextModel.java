/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.model.command.extractText;

import java.util.ArrayList;
import java.util.List;
import org.fundacionjala.converter.model.command.ICommand;
import org.fundacionjala.converter.model.configPath.ConfigPath;
import org.fundacionjala.converter.model.parameter.extractText.ExtractTextParameter;

/**
 * @author Jhordan Soto
 * @version 1.0
 */
public class ExtractTextModel implements ICommand<ExtractTextParameter> {
    public static final String TXT_EXTENSION = ".txt";
    public static final String LANGUAGE_SPA = "spa";

    public ExtractTextModel() {
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
        if (parameter.getLanguage().equals(LANGUAGE_SPA)) {
            command.add(parameter.LANG_COMMAND);
            command.add(parameter.getLanguage());
        }
        command.add(parameter.getInputFile());
        command.add(parameter.getOutputFile() + parameter.getFileName());
        return command;
    }
}
