package org.fundacionjala.converter.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommandBuilder {
    private Process process;
    private String command = "";

    public String execute(final List<String> listParams) {
        for (String param: listParams) {
            command += param;
        }
        System.out.println(command);
        ProcessBuilder processBuilder = new ProcessBuilder("powershell", "/c", "\"" + command + "\"");
        try {
            process = processBuilder.start();
            process.waitFor();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "File exported success";
    }
}
