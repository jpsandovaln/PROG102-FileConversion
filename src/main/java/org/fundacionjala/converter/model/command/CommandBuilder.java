/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.model.command;

import java.util.List;

/**
 * @author Angela Martinez
 * @version 0.1
 */
public class CommandBuilder {
    private Process process;
    private String command = "";

    /**
     * Build the command
     * @param listParams
     */
    public void buildCommand(final List<String> listParams) {
        command = "";
        for (String param: listParams) {
            command += param;
        }
        System.out.println(command);
    }

    /**
     * Execute the command
     * @param listParams
     * @throws Exception
     */
    public void execute(final List<String> listParams) throws Exception {
        buildCommand(listParams);
        ProcessBuilder processBuilder = new ProcessBuilder("powershell", "/c", "\"" + command + "\"");
            process = processBuilder.start();
            process.waitFor();
    }
}
