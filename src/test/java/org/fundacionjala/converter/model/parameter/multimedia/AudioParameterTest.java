package org.fundacionjala.converter.model.parameter.multimedia;

import org.fundacionjala.converter.model.command.multimedia.AudioModel;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class AudioParameterTest {
    @Test
    public void testCreateCommandReturnsEmptyListIfParameterIsEmpty() {
        AudioModel audioModel = new AudioModel();
        AudioParameter audioParameter = new AudioParameter();
        int result = audioModel.createCommand(audioParameter).size();
        assertEquals(0, result);
    }
    @Test
    public void testCreateCommandReturnsEmptyListIfParameterIsNull()  {
        AudioModel audioModel = new AudioModel();
        int result = audioModel.createCommand(null).size();
        assertEquals(0, result);
    }

}