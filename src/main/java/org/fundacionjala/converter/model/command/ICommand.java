package org.fundacionjala.converter.model.command;

import org.fundacionjala.converter.model.parameter.ModelParameter;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface ICommand {

    List<List<String>> createCommand(ModelParameter modelParameter) throws NoSuchAlgorithmException, IOException, InterruptedException, ExecutionException;
}
