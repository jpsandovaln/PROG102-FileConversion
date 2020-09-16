package org.fundacionjala.converter.executor;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.junit.Test;

public class ExecutorTest {
    List<String> list = new ArrayList<String>();
    Executor executor = new Executor();

    @Test
    public void addStringsToIndexZeroAndOneInExecute() throws ExecutionException, IOException, InterruptedException {
        List<String> expected = new ArrayList<String>();
        expected.add("cmd.exe");
        expected.add("/c");
        expected.add("subcommand1");
        list.add("subcommand1");
        executor.execute(list);
        assertEquals(expected, list);
    }
}