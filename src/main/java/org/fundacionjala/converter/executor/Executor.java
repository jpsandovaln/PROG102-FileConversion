package org.fundacionjala.converter.executor;

import java.io.BufferedReader;
import java.io.File;
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
   * @param commandsList list of the commands
   * @return list of the paths
   * @throws ExecutionException
   * @throws IOException
   * @throws InterruptedException
   */
  public List<String> executeCommandsList(final List<List<String>> commandsList)
      throws InterruptedException, ExecutionException, IOException {
    List<String> outputList = new ArrayList();
    for (List<String> command : commandsList) {
      if (command.indexOf("Metadata") == -1) {
        outputList.add(execute(command));
      } else {
        outputList.add(executeMetadata(command));
      }
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

    if (debugOutput) {
      System.out.println(".......MODE DEBUNG.......");
      command.stream().forEach(s -> {
        System.out.print(s + " ");
      });
      System.out.println(output.toString());
      System.out.println(".......END MODE DEBUNG.......");
    }

    processDuration.waitFor();
    return command.get(command.size() - 1);
  }
  /**
   *
   * @param command
   * @return
   * @throws ExecutionException
   * @throws IOException
   * @throws InterruptedException
   */
  public String executeMetadata(final List<String> command) throws ExecutionException, IOException, InterruptedException {
    List<String> commandMetadata = new ArrayList<String>();
    for (int i = 1; i < command.size() - 1; i++) {
      commandMetadata.add(command.get(i));
    }
    Process processDuration = new ProcessBuilder(commandMetadata)
            .redirectErrorStream(true)
            .redirectOutput(new File(command.get(command.size() - 1))).start();
    processDuration.waitFor();
    StringBuilder output = new StringBuilder();
    BufferedReader processOutputReader = new BufferedReader(new InputStreamReader(processDuration.getInputStream()));
    return command.get(command.size() - 1);
  }

  /**
   *
   * @param command
   * @return a single result of a String command
   * @throws IOException
   * @throws InterruptedException
   */
  public String executeSingleStringCommand(final String command) throws IOException, InterruptedException {
    Process processDuration = new ProcessBuilder("cmd", "/c", command).redirectErrorStream(true).start();
    processDuration.waitFor();
    StringBuilder output = new StringBuilder();
    BufferedReader processOutputReader = new BufferedReader(new InputStreamReader(processDuration.getInputStream()));
    while (processOutputReader.ready()) {
      output.append((char) processOutputReader.read());
    }
    return output.toString();
  }
}
