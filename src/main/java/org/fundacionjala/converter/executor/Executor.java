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
   * setDebugOutput
   */
  public void setDebugOutput(final boolean debugOutput) {
    this.debugOutput = debugOutput;
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
  public List<String> executeCommandsList(final List<List<String>> commandsList) throws InterruptedException, ExecutionException, IOException {
    List<String> outputList = new ArrayList<>();
    for (List<String> command : commandsList) {
      outputList.add(execute(command));
    }
    return outputList;
  }

  /**
   *
   * @param command
   * @return
   * @throws ExecutionException
   * @throws IOException
   * @throws InterruptedException
   */
  public String execute(final List<String> command) throws ExecutionException, IOException, InterruptedException {
    String commandToExecute = "";
    StringBuilder output = new StringBuilder();
    String line;
    Process process = new ProcessBuilder(command).redirectErrorStream(true).start();
    BufferedReader processOutputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
    while ((line = processOutputReader.readLine()) != null) {
      output.append(line + System.lineSeparator());
    }
    process.waitFor();
    if (debugOutput) {
      System.out.println("BEGIN DEBUG");
      System.out.println(output.toString());
      System.out.println("END DEBUG");
    }
    for (int i = 0; i < command.size(); i++) {
      commandToExecute += command.get(i) + " ";
    }
    System.out.println(commandToExecute);
    return commandToExecute;
  }
}
