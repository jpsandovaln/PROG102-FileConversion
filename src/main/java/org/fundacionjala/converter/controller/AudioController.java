package org.fundacionjala.converter.controller;

import org.fundacionjala.converter.model.AudioModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class AudioController {
    @Value("${inputFiles.path}")
    private String input;

    @Value("${convertedFiles.path}")
    private String converted;

    @Value("${audio.video.path}")
    private String tool;

    /**
     * Show all output of audio file conversion
     * @param file uploaded
     * @param format string
     * @param codec string
     * @param bitRate string
     * @param channels string
     * @param sampleRate string
     * @param start string
     * @param duration string
     * @param vol string
     * @return all output of audio file conversion
     */
    @RequestMapping(method = RequestMethod.POST, value = "convertAudio")
    public String convertAudio(@RequestParam("file") final MultipartFile file, @RequestParam("format") final String format, @RequestParam("codec") final String codec,
                               @RequestParam("bitRate") final String bitRate, @RequestParam("channels") final String channels, @RequestParam("sampleRate") final String sampleRate,
                               @RequestParam("start") final String start, @RequestParam("duration") final String duration, final String vol) {
        AudioModel converter = new AudioModel();
        return converter.convertAudio(file, format, codec, bitRate, channels, sampleRate, start,
                duration, vol, input, converted, tool);
    }
}
