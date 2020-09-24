package org.fundacionjala.converter.model.command;

import org.fundacionjala.converter.model.configPath.ConfigPath;
import org.fundacionjala.converter.model.parameter.ModelParameter;
import org.fundacionjala.converter.model.parameter.image.ImageParameter;

import java.util.ArrayList;
import java.util.List;

public class ImageModel implements ICommand {

    private ConfigPath config;
    private static final String RESIZE = "-resize";
    private static final String X = "x";
    private static final String SIGNOADMIRACION = "!";
    private static final String SIZETHUMBNAIL = "128X128";
    private static final String CROP = "-crop";
    private static final String COLORSPACE = "-colorspace";
    private static final String GRAY = "gray";
    public ImageModel() {
        config = new ConfigPath();
    }

    private List<String> resize(final ImageParameter imageParameter) {
        List<String> command = new ArrayList<String>();
        command.add(config.getImageTool());
        command.add(imageParameter.getInputFile());
        command.add(RESIZE + imageParameter.getHeight() + X + imageParameter.getWidth());
        command.add(SIGNOADMIRACION);
        name(imageParameter);
        command.add(imageParameter.getOutputFile());
        return command;
    }

    private List<String> thumbnail(final ImageParameter imageParameter) {
        List<String> command = new ArrayList<String>();
        command.add(config.getImageTool());
        command.add(imageParameter.getInputFile());
        command.add(RESIZE);
        command.add(SIZETHUMBNAIL);
        name(imageParameter);
        command.add(imageParameter.getOutputFile() + "t");
        return command;
    }

    private List<String> selectingImageRegion(final ImageParameter imageParameter) {
        ConfigPath config = new ConfigPath();
        List<String> command = new ArrayList<String>();
        command.add(config.getImageTool());
        command.add(imageParameter.getInputFile());
        command.add(CROP);
        command.add(imageParameter.getCrop());
        name(imageParameter);
        command.add(imageParameter.getOutputFile());
        return command;
    }

    private List<String> grayScale(final ImageParameter imageParameter) {
        List<String> command = new ArrayList<String>();
        command.add(config.getImageTool());
        command.add(imageParameter.getInputFile());
        command.add(COLORSPACE);
        command.add(GRAY);
        name(imageParameter);
        command.add(imageParameter.getOutputFile());
        return command;
    }

    /**
     * create command
     *
     * @return list of commands
     */
    @Override
    public List<List<String>> createCommand(final ModelParameter modelParameter) {
        List<List<String>> listCommands = new ArrayList<List<String>>();
        ImageParameter imageParameter = (ImageParameter) modelParameter;

        if (imageParameter.getIsGray()) {
            listCommands.add(grayScale(imageParameter));
        }

        if (imageParameter.getSelectingImageRegion()) {
            listCommands.add(selectingImageRegion(imageParameter));
        }

        if (imageParameter.getIsThumbnail()) {
            listCommands.add(thumbnail(imageParameter));
        }

        if (imageParameter.getIsResize()) {
            listCommands.add(resize(imageParameter));
        }
        return listCommands;
    }
}
