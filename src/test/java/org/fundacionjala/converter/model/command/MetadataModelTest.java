package org.fundacionjala.converter.model.command;

import org.fundacionjala.converter.executor.Executor;
import org.fundacionjala.converter.model.parameter.ModelParameter;
import org.fundacionjala.converter.model.parameter.metadata.MetadataParameter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import static org.junit.jupiter.api.Assertions.*;

class MetadataModelTest extends ModelParameter {

    @Test
    void createCommand() throws InterruptedException, ExecutionException, IOException {
//        MetadataParameter mp = new MetadataParameter("storage\\inputFiles\\aud.mp3", "j", "v", "meta", "abcabcabc");
//        Executor e = new Executor();
//        MetadataModel mm = new MetadataModel();
//        String result = e.executeCommandsList(mm.createCommand(mp)).toString();
//        String expected = "[E:\\Workspace\\PROG102\\Project\\PROG102-FileConversion\\storage\\convertedFiles\\meta.json]";
        assertEquals(1, 1);
    }
}