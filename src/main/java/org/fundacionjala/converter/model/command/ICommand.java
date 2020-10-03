package org.fundacionjala.converter.model.command;

import org.fundacionjala.converter.model.parameter.ModelParameter;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.security.NoSuchAlgorithmException;
import java.io.IOException;

public interface ICommand<T extends ModelParameter> {
    List<List<String>> createCommand(T modelParameter) throws NoSuchAlgorithmException, IOException, InterruptedException, ExecutionException;

    /**
     *
     * @param modelParameter the parameter that will be added sufix
     */
    default void name(final ModelParameter modelParameter) {
        String fileName = modelParameter.getOutputFile();
        File aFile = new File(fileName);
        int fileNo = 0;
        while (aFile.exists() && !aFile.isDirectory()) {
            fileNo++;
            String newName = fileName.replaceAll(modelParameter.getFormat(), "(" + fileNo + ")" + modelParameter.getFormat());
            aFile = new File(newName);
        }
        modelParameter.setOutputFile(aFile.getPath());
    }

    /**
     * Returns a new file name
     * @param pathOutputFile
     * @param fileName
     * @param format
     * @return
     */
    default String changeName(final String pathOutputFile, final String fileName, final String format) {
        String file = pathOutputFile + fileName + format;
        String newName = fileName;
        File aFile = new File(file);
        int fileNo = 0;
        while (aFile.exists() && !aFile.isDirectory()) {
            fileNo++;
            newName = fileName + "(" + fileNo + ")";
            aFile = new File(pathOutputFile + newName + format);
        }
        return newName;
    }
}
