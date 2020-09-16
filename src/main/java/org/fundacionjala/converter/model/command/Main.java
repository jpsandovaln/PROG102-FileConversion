package org.fundacionjala.converter.model.command;

import org.fundacionjala.converter.executor.Executor;
import org.fundacionjala.converter.model.parameter.ModelParameter;
import org.fundacionjala.converter.model.parameter.multimedia.AudioParameter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Main {
    private String name;
    public Main() {
        name = "converter project";
    }

    public static void main(final String[] args) throws InterruptedException, ExecutionException, IOException {
        String inputFile = "storage/inputFiles/MiEternoAmorSecreto.mp3";
        String outputFile = "storage/convertedFiles";
        String name = "MiEternoAmorSecreto";
        String format = ".wav";
        String codec = "libmp3lame";
        String bitRate = "32k";
        String channel = "1";
        String sampleRate = "22050";
        String start = "00:00:30";
        String duration = "10";
        boolean cut = true;
        //boolean metadata = true;

        ModelParameter modelParameter = new AudioParameter();
        modelParameter.setInputFile(inputFile);
        modelParameter.setOutputFile(outputFile);
        ((AudioParameter) modelParameter).setName(name);
        ((AudioParameter) modelParameter).setFormat(format);
        //((AudioParameter) modelParameter).setCodec(codec);
        //((AudioParameter) modelParameter).setBitRate(bitRate); // -b:a
        //((AudioParameter) modelParameter).setChannel(channel); // stereo
        //((AudioParameter) modelParameter).setSampleRate(sampleRate); //-ar
        //((AudioParameter) modelParameter).setStart(start); //-ss
        //((AudioParameter) modelParameter).setDuration(duration); //-t
        //((AudioParameter) modelParameter).setCut(cut); //

        Executor executor = new Executor();
        ICommand audioModel = new AudioModel();
        List<List<String>> list = audioModel.createCommand(modelParameter);
        System.out.println(list.size());
        System.out.println(list.get(0).size());
        for (String command:list.get(0)) {
            System.out.println(command);
        }

        executor.executeList(audioModel.createCommand(modelParameter));

    }
}
