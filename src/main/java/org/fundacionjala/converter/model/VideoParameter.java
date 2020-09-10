package org.fundacionjala.converter.model;

import java.util.ArrayList;
import java.util.Arrays;

public enum VideoParameter {
  AUDIO("-vn"),
  COMPRESS("-vcodec", "h264", "-acodec", "mp2", "-i"),
  GIF("-r", "12", "-i"),
  //params for thumbnail
  TIME("-ss", "10", "-t", "5", "-i"),
  PALETTE("-vf", "\"fps=10,scale=320:-1:flags=lanczos,split[s0][s1];[s0]palettegen[p];[s1][p]paletteuse\"", "-loop", "0");

  private String[] parameter;

  VideoParameter(final String... parameter) {
    this.parameter = parameter;
  }

  public ArrayList<String> getParameter() {
    return new ArrayList<String>(Arrays.asList(parameter));
  }
}
