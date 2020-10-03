package org.fundacionjala.converter.model.parameter.metadata;

import com.lowagie.text.Meta;
import org.fundacionjala.converter.model.command.MetadataModel;
import org.fundacionjala.converter.model.parameter.ModelParameter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MetadataParameterTest extends ModelParameter {
    MetadataParameter metadataParameter = new MetadataParameter("storage\\inputFiles\\aud.mp3", "j", "v", "meta", "abcabcabc");

    @Test
    public void getDetailMetadataParameterTest() {
        String result = metadataParameter.getDetail();
        String expected = "v";
        assertEquals(result, expected);
    }

    @Test
    public void getFormatMetadataParameterTest() {
        String result = metadataParameter.getFormat();
        String expected = "j";
        assertEquals(result, expected);
    }

    @Test
    public void getDetailsMetadataParameterTest() {
        String result = metadataParameter.getInputFile();
        String expected = "storage\\inputFiles\\aud.mp3";
        assertEquals(result, expected);
    }
}