package org.fundacionjala.converter.model.command;

import org.fundacionjala.converter.model.parameter.ModelParameter;
import java.io.File;
import java.util.List;

public interface ICommand {

    List<List<String>> createCommand(ModelParameter modelParameter);

    default void name(final ModelParameter modelParameter) {
        String fileName = modelParameter.getOutputFile();
        File aFile = new File(fileName);
        int fileNo = 0;
        while (aFile.exists() && !aFile.isDirectory()) {
            fileNo++;
            String newName = fileName.replaceAll(modelParameter.getFormat(), "(" + fileNo + ")" + modelParameter.getFormat());
            aFile = new File(newName);
        }
        modelParameter.setOutputFile(aFile.getAbsolutePath());
    }
}
