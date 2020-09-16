package org.fundacionjala.converter.model;

import org.fundacionjala.converter.executor.Executor;
import org.fundacionjala.converter.model.command.VideoModel;
import org.fundacionjala.converter.model.parameter.multimedia.VideoParameter;

public class Main {
    public static void main(String []args) {
        try {
            VideoModel video = new VideoModel();
            VideoParameter videoParameter = new VideoParameter("thirdParty/ffmpeg/bin/ffmpeg.exe ", "storage/convertedFiles/video/");
            videoParameter.setFilePath("storage/inputFiles/lesson-2a-medium.mov ");
            videoParameter.setExtension("mp4");
            Executor executor = new Executor();
            executor.executeList(video.createCommand(videoParameter));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
