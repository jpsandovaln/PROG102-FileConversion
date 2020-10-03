package org.fundacionjala.converter.model.command;

import org.fundacionjala.converter.model.configPath.ConfigPath;
import org.fundacionjala.converter.model.parameter.metadata.MetadataParameter;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MetadataModelTest {
    private String inputFile = "storage/inputFiles/test.mp3";
    private String outputFile = "storage/convertedFiles/";
    private String md5OriginalFile = "e1b3fab24c8af81c1aa13dbbb4e44ff0";
    @Test
    public void testCreateCommandDetailCommon() {

        String exportFormat = "j";
        String detail = "common";
        String expected = "[[Metadata, " + ConfigPath.getMetaDataExtractorTool() + ", -j, -common, storage/inputFiles/test.mp3, storage/convertedFiles/e1b3fab24c8af81c1aa13dbbb4e44ff0j_Metadata.json]]";
        String nameFileOutPut = outputFile + md5OriginalFile + exportFormat;
        MetadataParameter parameter= new MetadataParameter(inputFile, exportFormat, detail, outputFile,
            md5OriginalFile);
        parameter.setOutputFile(nameFileOutPut);

        MetadataModel metaDataModel = new MetadataModel();
        String result = metaDataModel.createCommand(parameter).toString();
        assertEquals(expected, result);
    }

    @Test
    public void testNullPointerExceptionCreateCommand() {
        MetadataParameter parameter = null;
        MetadataModel metaDataModel = new MetadataModel();

        assertThrows(NullPointerException.class, () -> {
            metaDataModel.createCommand(parameter).toString();
        });
    }

    @Test
    public void testCreateCommandDetailDefault() {
        String exportFormat = "j";
        String detail = "d";
        String expected = "[[Metadata, " + ConfigPath.getMetaDataExtractorTool() + ", -j, storage/inputFiles/test.mp3, storage/convertedFiles/e1b3fab24c8af81c1aa13dbbb4e44ff0j_Metadata.json]]";
        String nameFileOutPut = outputFile + md5OriginalFile + exportFormat;

        MetadataParameter parameter= new MetadataParameter(inputFile, exportFormat, detail, outputFile,
                md5OriginalFile);
        parameter.setOutputFile(nameFileOutPut);

        MetadataModel metaDataModel = new MetadataModel();
        String result = metaDataModel.createCommand(parameter).toString();
        assertEquals(expected, result);
    }
}