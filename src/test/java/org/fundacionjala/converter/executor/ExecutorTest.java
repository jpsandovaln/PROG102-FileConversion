package org.fundacionjala.converter.executor;

import org.fundacionjala.converter.executor.exception.CommandListEmptyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExecutorTest {
    private Executor executor;

    @BeforeEach
    public void init() {
        executor = new Executor();
    }

    @Test
    public void testCommandListEmptyExceptionExecuteCommandsList() {
        List<List<String>> commandList = new ArrayList<>();
        commandList.add(new ArrayList<>());

        assertThrows(CommandListEmptyException.class, () -> {
            executor.executeCommandsList(commandList);
        });
    }

    @Test
    public void testCommandListEmptyExceptionExecuteMetadata() {
        executor = new Executor();
        List<String> command = new ArrayList<String>();
        assertThrows(CommandListEmptyException.class, () -> {
            executor.executeMetadata(command);
        });
    }

    @Test
    public void testCommandListEmptyExceptionExecuteSingleStringCommand() {
        executor = new Executor();
        String command = "";
        assertThrows(CommandListEmptyException.class, () -> {
            executor.executeSingleStringCommand(command);
        });
    }
}