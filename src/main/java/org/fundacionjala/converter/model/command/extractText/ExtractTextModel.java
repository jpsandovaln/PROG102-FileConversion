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
import org.fundacionjala.converter.model.commons.exception.ModelParameterException;
import org.fundacionjala.converter.model.commons.validation.FormatValidation;
import org.fundacionjala.converter.model.configPath.ConfigPath;
import org.fundacionjala.converter.model.parameter.extractText.ExtractTextParameter;

/**
 * @author Jhordan Soto
 * @version 1.0
 */
public class ExtractTextModel implements ICommand<ExtractTextParameter> {
    public static final String LANGUAGE_EN = "en";
    public static final String TESSDATA_DIR = "--tessdata-dir";
    public ExtractTextModel() {
    }

    /**
     * Creates command
     * @return list of commands
     */
    @Override
    public List<List<String>> createCommand(final ExtractTextParameter parameter) throws ModelParameterException {
        List<List<String>> listCommands = new ArrayList<>();
        listCommands.add(extractText(parameter));
        return listCommands;
    }

    /**
     * Extracts text from an image
     * @param parameter
     * @return
     */
    private List<String> extractText(final ExtractTextParameter parameter) throws ModelParameterException {
        if (parameter != null) {
            List<String> command = new ArrayList<>();
            command.add(ConfigPath.getExtractTextTool());
            command.add(TESSDATA_DIR);
            command.add(ConfigPath.getTesstDataDir());
            if (!parameter.getLanguage().equals(LANGUAGE_EN)) {
                command.add(parameter.LANG_COMMAND);
                command.add(parameter.getLanguage());
            }
            command.add(parameter.getInputFile());
            command.add(parameter.getOutputFile() + changeName(parameter.getOutputFile(), parameter.getFileName(), FormatValidation.FORMAT_TXT));
            return command;
        } else {
            throw new ModelParameterException("Invalid ExtractTextParameter");
        }
    }
}
