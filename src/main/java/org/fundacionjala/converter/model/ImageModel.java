package org.fundacionjala.converter.model;

import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ImageModel {

    public String execute(MultipartFile file , String newName , int height , int weight , String extension, int thumbnail) {
        try {
            String path = "C:\\Users\\Administrator\\Desktop";
            String fileName = file.getOriginalFilename();
            String cmd = "cd " + path + " && magick " + fileName + " -resize " + height + "x" + weight + " " + newName + "." + extension;
            String thumbnailCmd = "cd " + path + " && magick " + fileName + " -resize 128x128 thumbnail-" + newName + "." + extension;
            readCmdCommand(cmd);
            if(thumbnail == 1) {
                readCmdCommand(thumbnailCmd);
            }
            return "successfully";
        }catch (Exception ex)
        {
            return ex.getMessage();
        }
    }

    public String selectingImageRegion(MultipartFile file, String newName, int height, int weight, int startPositionX, int startPositionY, String extension) {
        try {
            String path = "C:\\Users\\Administrator\\Desktop";
            String fileName = file.getOriginalFilename();
            String cmd = "cd " + path + " && magick " + fileName + " -crop "+ height + "x" + weight + "+" + startPositionX + "+"+ startPositionY + " " + newName + "." +extension;
            readCmdCommand(cmd);
            return "successfully";
        }catch (Exception ex)
        {
            return ex.getMessage();
        }
    }
    public static String readCmdCommand(String cmd) {
        try
        {
            ProcessBuilder builder = new ProcessBuilder("cmd", "/c", "\"" +  cmd + "\"");
            builder.redirectErrorStream(true);
            Process process =  builder.start();
            InputStreamReader streamReader = new InputStreamReader(process.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);
            return "successfully";
        }
        catch (IOException ex)
        {
            return ex.getMessage();
        }
    }

    public String grayScale(MultipartFile file, String newName, String extension) {
        try {
            String path = "C:\\Users\\Administrator\\Desktop";
            String fileName = file.getOriginalFilename();
            String cmd = "cd " + path + " && magick " + fileName + " -colorspace Gray " + newName + "." +extension;
            readCmdCommand(cmd);
            return "successfully";
        }catch (Exception ex)
        {
            return ex.getMessage();
        }
    }
}
