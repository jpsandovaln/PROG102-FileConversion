package org.fundacionjala.converter.model.command;

import org.fundacionjala.converter.executor.Executor;
import org.fundacionjala.converter.model.parameter.ModelParameter;
import org.fundacionjala.converter.model.parameter.multimedia.AudioParameter;
//import org.junit.Test;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

public class AudioModelTest {
    @Test
    public void testConvertMp3ToWav() throws InterruptedException, ExecutionException, IOException {
        ModelParameter modelParameter = new AudioParameter();
        modelParameter.setInputFile("storage/inputFiles/MiEternoAmorSecreto.mp3");
        modelParameter.setOutputFile("storage/convertedFiles/MiEternoAmorSecreto.wav");
        ((AudioParameter) modelParameter).setAudioFormat(".wav");

        ICommand command1 = new AudioModel();
        command1.createCommand(modelParameter);
        Executor executor = new Executor();

        AudioModel audioModel = new AudioModel();
        executor.execute(audioModel.createCommand(modelParameter));
        assertEquals("storage/convertedFiles/MiEternoAmorSecreto.wav", modelParameter.getOutputFile());
    }

}