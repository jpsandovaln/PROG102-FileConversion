package org.fundacionjala.converter;

import org.fundacionjala.converter.executor.Executor;
import org.fundacionjala.converter.model.command.MetadataModel;
import org.fundacionjala.converter.model.parameter.metadata.MetadataParameter;
import org.junit.jupiter.api.Test;
import org.junit.Assert;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

class ApplicationTests {

    @Test
    public void MetadataConverterTest() throws InterruptedException, ExecutionException, IOException {
        //This method was commented because exiftool is not present at GitHub repository, so Test failed for that reason.
        /*MetadataParameter mp = new MetadataParameter("storage\\inputFiles\\aud.mp3", "t", "v", "meta", "abcabcabc");
        Executor e = new Executor();
        MetadataModel mm = new MetadataModel();
        String result = e.executeList(mm.createCommand(mp)).toString();
        String expected = "[E:\\Workspace\\PROG102\\Project\\PROG102-FileConversion\\storage\\convertedFiles\\meta.txt]";
        Assert.assertEquals(result, expected);*/
    }
}
