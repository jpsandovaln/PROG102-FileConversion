package org.fundacionjala.converter.model.parameter.multimedia;

import java.io.IOException;

public class AudioParameter extends MultimediaParameter {

    private String audioName;
    private String audioFormat;
    private String audioCodec;
    private String audioBitRate;
    private String audioChannel;
    private String audioSampleRate;
    private String audioStart;
    private String audioDuration;
    private String audioVolume;
    private AudioParameterValidation audioValidation = new AudioParameterValidation();

    /**
     *
     * @return new name file
     */
    public String getAudioName() {
        return audioName;
    }

    /**
     *
     * @return audio format
     */
    public String getAudioFormat() {
        return audioFormat;
    }

    /**
     *
     * @return audio codec
     */
    public String getAudioCodec() {
        return audioCodec;
    }

    /**
     *
     * @return audio bit rate
     */
    public String getAudioBitRate() {
        return audioBitRate;
    }

    /**
     *
     * @return audio channel
     */
    public String getAudioChannel() {
        return audioChannel;
    }

    /**
     *
     * @return audio sample rate
     */
    public String getAudioSampleRate() {
        return audioSampleRate;
    }

    /**
     *
     * @return audio start
     */
    public String getAudioStart() {
        return audioStart;
    }

    /**
     *
     * @return audio duration
     */
    public String getAudioDuration() {
        return audioDuration;
    }

    /**
     *
     * @return audio volume
     */
    public String getAudioVolume() {
        return audioVolume;
    }

    /**
     *
     * @param inputFile
     * @throws IOException
     */
    public void setInputFile(final String inputFile) throws IOException {
        if (audioValidation.validateAudioFile(inputFile)) {
            super.setInputFile(inputFile);
        } else {
            // exception el archivo no es un audio.
            System.out.println("the file does not type audio" + inputFile);
        }
    }


    /**
     *
     * @param name
     */
    public void setAudioName(final String name) {
         this.audioName = audioValidation.validateAudioName(name);
    }

    /**
     *
     * @param audioFormat
     */
    public void setAudioFormat(final String audioFormat) {
        if (audioValidation.validateAudioFormat(audioFormat)) {
            this.audioFormat = audioFormat;
        } else {
            // exception the format does not belong to audio +audioFormat
            System.out.println("the format does not belong to audio" + audioFormat);
        }
    }

    /**
     *
     * @param audioCodec
     */
    public void setAudioCodec(final String audioCodec) {
        this.audioCodec = audioCodec;
    }

    /**
     *
     * @param audioBitRate
     */
    public void setAudioBitRate(final String audioBitRate) {
        this.audioBitRate = audioBitRate;
    }

    /**
     *
     * @param audioChannel
     */
    public void setAudioChannel(final String audioChannel) {
        this.audioChannel = audioChannel;
    }

    /**
     *
     * @param audioSampleRate
     */
    public void setAudioSampleRate(final String audioSampleRate) {
        this.audioSampleRate = audioSampleRate;
    }

    /**
     *
     * @param audioStart
     */
    public void setAudioStart(final String audioStart) {
        this.audioStart = audioStart;
    }

    /**
     *
     * @param audioDuration
     */
    public void setAudioDuration(final String audioDuration) {
        this.audioDuration = audioDuration;
    }

    /**
     *
     * @param audioVolume
     */
    public void setAudioVolume(final String audioVolume) {
        this.audioVolume = audioVolume;
    }
}
