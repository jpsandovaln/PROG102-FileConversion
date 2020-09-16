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
   * This method execute the command.
   *
   * @param list list of the commands
   * @return list of the paths
   * @throws ExecutionException
   * @throws IOException
   * @throws InterruptedException
   */

  public List<String> executeList(final List<List<String>> list) throws InterruptedException, ExecutionException, IOException {
    List<String> outputList = new ArrayList();
    for (List<String> l : list) {
      outputList.add(execute(l));
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
    System.out.println(command.get(command.size() - 1));
    return command.get(command.size() - 1);
  }
}
