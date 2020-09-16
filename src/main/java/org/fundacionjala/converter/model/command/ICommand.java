package org.fundacionjala.converter.model.command;

import java.util.List;

import org.fundacionjala.converter.model.parameter.ModelParameter;

public interface ICommand {

    List<List<String>> createCommand(ModelParameter modelParameter);
}
