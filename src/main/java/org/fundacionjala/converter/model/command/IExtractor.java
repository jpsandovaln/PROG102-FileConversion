package org.fundacionjala.converter.model.command;

import org.fundacionjala.converter.model.command.metadata.Param;

/**
 * @author Angela Martinez
 * @version 0.1
 */
public interface IExtractor {
    String extract(Param param) throws Exception;
}
