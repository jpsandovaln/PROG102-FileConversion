/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter;

import java.util.ArrayList;
import java.util.List;

import org.fundacionjala.converter.executor.Executor;
import org.fundacionjala.converter.model.command.CommandBuilder;
import org.fundacionjala.converter.model.command.ExtractTextModel;
import org.fundacionjala.converter.model.configPath.ConfigPath;
import org.fundacionjala.converter.model.parameter.ModelParameter;
import org.fundacionjala.converter.model.parameter.extractText.ExtractTextParameter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Julia Escalante
 * @version 0.1
 */
@SpringBootApplication
public class Application {
    private String name;

    public Application() {
        name = "converter project";
    }

    public static void main(final String[] args) {
        // SpringApplication.run(Application.class, args);
        String inputFile = "D:\\AT\\PROG102\\code\\PROG102-FileConversion\\storage\\inputFiles\\image.png";
        //String inputFile = "D:\\AT\\PROG102\\code\\PROG102-FileConversion\\storage\\inputFiles\\imageEnglish.jpg";
        //String inputFile = "D:\\AT\\PROG102\\code\\PROG102-FileConversion\\storage\\inputFiles\\imageSpanish.jpg";
        String outputFile = "D:\\AT\\PROG102\\code\\PROG102-FileConversion\\storage\\outputFiles\\image-txt";
        String languageSp = "español";
        String languageEn = "english";
        String type = "word";
        ModelParameter eTextParameter = new ExtractTextParameter();
        ExtractTextModel eTextModelEn = new ExtractTextModel();
        eTextParameter.setInputFile(inputFile);
        eTextParameter.setOutputFile(outputFile);
        //((ExtractTextParameter) eTextParameter).setLanguage(languageSp);
        ((ExtractTextParameter) eTextParameter).setLanguage(languageEn);
        ((ExtractTextParameter) eTextParameter).setType(type);
        /*List<List<String>> commandList = eTextModelEn.createCommand(eTextParameter);
        List<String> commandToEx = commandList.get(0); 
        Executor executor = new Executor();
        try {
            executor.execute(commandToEx);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        eTextModelEn.createDocument(eTextParameter);
    }
}
