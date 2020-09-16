package org.fundacionjala.converter.model.command;

import org.fundacionjala.converter.model.parameter.ModelParameter;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ImageModel implements ICommand {

    /**
     *
     * @param file
     * @param newName
     * @param height
     * @param weight
     * @param extension
     * @param thumbnail
     * @param forceResize
     * @return
     */
    public String convert(final MultipartFile file, final String newName, final int height, final int weight,
            final String extension, final int thumbnail, final int forceResize) {
        try {
            String path = "C:\\Users\\Administrator\\Desktop";
            String fileName = file.getOriginalFilename();
            String force = "";
            if (forceResize == 1) {
                force = "!";
            }
            String cmd = "cd " + path + " && magick " + fileName + " -resize " + height + "x" + weight + force + " "
                    + newName + "." + extension;
            String thumbnailCmd = "cd " + path + " && magick " + fileName + " -resize 128x128 thumbnail-" + newName
                    + "." + extension;
            readCmdCommand(cmd);
            if (thumbnail == 1) {
                readCmdCommand(thumbnailCmd);
            }
            return "successfully";
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    /**
     *
     * @param file
     * @param newName
     * @param height
     * @param weight
     * @param startPositionX
     * @param startPositionY
     * @param extension
     * @return
     */
    public String selectingImageRegion(final MultipartFile file, final String newName, final int height,
            final int weight, final int startPositionX, final int startPositionY, final String extension) {
        try {
            String path = "C:\\Users\\Administrator\\Desktop";
            String fileName = file.getOriginalFilename();
            String cmd = "cd " + path + " && magick " + fileName + " -crop " + height + "x" + weight + "+"
                    + startPositionX + "+" + startPositionY + " " + newName + "." + extension;
            readCmdCommand(cmd);
            return "successfully";
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    public static String readCmdCommand(final String cmd) {
        try {
            ProcessBuilder builder = new ProcessBuilder("cmd", "/c", "\"" + cmd + "\"");
            builder.redirectErrorStream(true);
            Process process = builder.start();
            InputStreamReader streamReader = new InputStreamReader(process.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);
            return "successfully";
        } catch (IOException ex) {
            return ex.getMessage();
        }
    }

    /**
     *
     * @param file
     * @param newName
     * @param extension
     * @return
     */
    public String grayScale(final MultipartFile file, final String newName, final String extension) {
        try {
            String path = "C:\\Users\\Administrator\\Desktop";
            String fileName = file.getOriginalFilename();
            String cmd = "cd " + path + " && magick " + fileName + " -colorspace Gray " + newName + "." + extension;
            readCmdCommand(cmd);
            return "successfully";
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    /**
     * create command
     *
     * @return list of commands
     */
    @Override
    public List<List<String>> createCommand(final ModelParameter modelParameter) {
        // TODO Auto-generated method stub
        return null;
    }
}
