package org.fundacionjala.converter.model.command;

import org.fundacionjala.converter.model.ChecksumMD5;
import org.junit.jupiter.api.Test;

import org.fundacionjala.converter.model.parameter.metadata.MetadataParameter;
import org.fundacionjala.converter.executor.Executor;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

public class MetadataModelTest {
    @Test
    public void testConvertToJson() throws InterruptedException, ExecutionException, IOException, NoSuchAlgorithmException {
        MetadataModel metaDataModel = new MetadataModel();
        String inputFile = "storage/inputFiles/test.mp3";
        String outputFile = "storage/convertedFiles/";
        String exportFormat = "j";
        String detail = "common";
        String md5OriginalFile = "e1b3fab24c8af81c1aa13dbbb4e44ff0";
        String nameFileOutPut = outputFile + md5OriginalFile + exportFormat;
        String expectedMd5 = "bbb93a7a9e7600ea16acec655366e4fd";

        MetadataParameter metaDataParameter = new MetadataParameter(inputFile, exportFormat, detail, outputFile, md5OriginalFile);
        metaDataParameter.setOutputFile(nameFileOutPut);

        List<List<String>> command = metaDataModel.createCommand(metaDataParameter);
        Executor executor = new Executor();
        String result = new ChecksumMD5().getMD5(executor.executeCommandsList(command).get(0));
        assertEquals(expectedMd5, result);
    }
}