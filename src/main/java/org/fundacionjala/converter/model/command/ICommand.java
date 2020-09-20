package org.fundacionjala.converter.model.command;

import org.fundacionjala.converter.model.parameter.ModelParameter;

import java.util.List;

public interface ICommand <T extends ModelParameter>{

    List<List<String>> createCommand(T modelParameter);
}
