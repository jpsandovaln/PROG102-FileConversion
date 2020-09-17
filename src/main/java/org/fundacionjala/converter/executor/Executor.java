package org.fundacionjala.converter.executor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Executor {

  private boolean debugOutput;

  public Executor() {
    debugOutput = false;
  }

  /**
   * This method execute the command.
   *
   * @param list list of the commands
   * @return list of the paths
   * @throws ExecutionException
   * @throws IOException
   * @throws InterruptedException
   */
  public List<String> executeCommandsList(final List<List<String>> commandsList) throws InterruptedException, ExecutionException, IOException {
    List<String> outputList = new ArrayList();
    for (List<String> command : commandsList) {
      outputList.add(execute(command));
    }
    return outputList;
  }

  private String execute(final List<String> command) throws ExecutionException, IOException, InterruptedException {
    Process processDuration = new ProcessBuilder(command).redirectErrorStream(true).start();
    StringBuilder output = new StringBuilder();
    BufferedReader processOutputReader = new BufferedReader(new InputStreamReader(processDuration.getInputStream()));
    String line;
    while ((line = processOutputReader.readLine()) != null) {
      output.append(line + System.lineSeparator());
    }
    processDuration.waitFor();
    return command.get(command.size() - 1);
  }
}
