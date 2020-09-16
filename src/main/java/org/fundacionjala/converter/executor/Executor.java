package org.fundacionjala.converter.executor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Executor {

    private boolean debugOutPut;

    public Executor() {
        debugOutPut = false;
    }

    /**
     * setDebugOutput
     */
    public void setDebugOutput(final boolean debugOutPut) {
        this.debugOutPut = debugOutPut;
    }

    public List<String> executeList(final List<List<String>> commands) throws ExecutionException, IOException, InterruptedException {
        List<String> outputList = new ArrayList();
        for(List<String> command : commands) {
            outputList.add(execute(command));
        }
        return outputList;

    }

    /**
     * This method execute the command.
     *
     * @param command list of the commands
     * @return status of the execution
     * @throws ExecutionException
     * @throws IOException
     * @throws InterruptedException
     */
    private String execute(final List<String> command) throws ExecutionException, IOException, InterruptedException {
        Process processDuration = new ProcessBuilder(command).redirectErrorStream(true).start();
        StringBuilder output = new StringBuilder();
        BufferedReader processOutputReader = new BufferedReader(new InputStreamReader(processDuration.getInputStream()));
        String line;
        while ((line = processOutputReader.readLine()) != null) {
            output.append(line + System.lineSeparator());
        }
        processDuration.waitFor();

        if (debugOutPut) {
            System.out.println("BEGIN DEBUG");
            System.out.println(output.toString());
            System.out.println("END DEBUG");
        }
        return command.get(command.size() - 1);
    }
}