package org.fundacionjala.converter.model.command;

import java.util.ArrayList;
import java.util.Arrays;

public enum VideoParameter {
    AUDIO("-vn"),
    COMPRESS("-vcodec", "h264", "-acodec", "mp2", "-i"),
    GIF("-r", "12", "-i");

    private String[] parameter;

    VideoParameter(final String... parameter) {
        this.parameter = parameter;
    }

    public ArrayList<String> getParameter() {
        return new ArrayList<String>(Arrays.asList(parameter));
    }
}