package org.fundacionjala.converter.controller;

import org.fundacionjala.converter.model.AudioModel;
import org.fundacionjala.converter.params.AudioParameter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
public class AudioController {

    static final int FORMAT_INDEX = 0;
    static final int CODEC_INDEX = 1;
    static final int BITRATE_INDEX = 2;
    static final int CHANNELS_INDEX = 3;
    static final int SAMPLERATE_INDEX = 4;
    static final int START_INDEX = 5;
    static final int DURATION_INDEX = 6;
    static final int VOL_INDEX = 7;

    @Value("${inputFiles.path}")
    private String input;

    @Value("${convertedFiles.path}")
    private String converted;

    @Value("${audio.video.path}")
    private String tool;

    private String verifyEmptyParam(final String param, final String value) {

        if (!value.equals("")) {
            return " " + param + " " + value;
        }
        return value;
    }

    private String getCommandParameters(final AudioParameter param) {
        String outputParameters = "";
        outputParameters += verifyEmptyParam("-codec:a", param.getCodec());
        outputParameters += verifyEmptyParam("-b:a", param.getBitRate());
        outputParameters += verifyEmptyParam("-ac", param.getChannels());
        outputParameters += verifyEmptyParam("-ar", param.getSampleRate());
        outputParameters += verifyEmptyParam("-ss", param.getStart());
        outputParameters += verifyEmptyParam("-t", param.getDuration());
        outputParameters += verifyEmptyParam("-vol", param.getVol()) + " ";
        return outputParameters;
    }

    private String addProjectDirectory(final String path) {
        return (System.getProperty("user.dir") + "/").replace("\\", "/") + path;
    }

    private String copySourceFile(final AudioParameter param) {
        try {
            String fileName = param.getFile().getOriginalFilename();
            String source = input + fileName;
            Files.copy(param.getFile().getInputStream(), Paths.get(source), StandardCopyOption.REPLACE_EXISTING);
            return source;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return "Error al cargar el archivo";
        }
    }

    private String getTargetFile(final String fileName, final String format) {
        String fileNameConverted = fileName.substring(fileName.lastIndexOf('/') + 1, fileName.indexOf('.'));
        String target = converted + fileNameConverted + "-converted" + format;
        return target;
    }

    private AudioParameter createAudioParameter(final MultipartFile file, final String params) {
        AudioParameter param = new AudioParameter();
        param.setFile(file);
        param.setMd5("MD5-Example");
        String[] paramPart = params.split(",");
        param.setFormat(paramPart[FORMAT_INDEX]);
        param.setCodec(paramPart[CODEC_INDEX]);
        param.setBitRate(paramPart[BITRATE_INDEX]);
        param.setChannels(paramPart[CHANNELS_INDEX]);
        param.setSampleRate(paramPart[SAMPLERATE_INDEX]);
        param.setStart(paramPart[START_INDEX]);
        param.setDuration(paramPart[DURATION_INDEX]);
        param.setVol(paramPart[VOL_INDEX]);
        return param;
    }

    /**
     * Show all output of audio file conversion
     * @param file uploaded
     * @param params string
     * @return all output path or error message of audio file conversion
     */

    @RequestMapping(method = RequestMethod.POST, value = "convertAudio")
    public String convertAudio(@RequestParam final MultipartFile file, @RequestParam final String params) {
        AudioParameter parameter = createAudioParameter(file, params);
        String source = copySourceFile(parameter);
        if (!source.contains("/")) {
            return source;
        }
        String target = getTargetFile(source, parameter.getFormat());
        String commandParameters = getCommandParameters(parameter);
        AudioModel converter = new AudioModel();
        return converter.convertAudio(commandParameters, addProjectDirectory(source), addProjectDirectory(target), addProjectDirectory(tool));
    }
}
