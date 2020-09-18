package org.fundacionjala.converter.model.command;

import org.fundacionjala.converter.model.configPath.ConfigPath;
import org.fundacionjala.converter.model.parameter.ModelParameter;
import org.fundacionjala.converter.model.parameter.image.ImageParameter;
import java.util.ArrayList;
import java.util.List;

public class ImageModel implements ICommand {

    private ConfigPath config;

    public ImageModel() {
        config = new ConfigPath();
    }

    private List<String> resize(final ImageParameter imageParameter) {
        List<String> command = new ArrayList<String>();
        command.add(config.getImageTool());
        command.add(imageParameter.getInputFile());
        command.add("-resize " + imageParameter.getHeight() + "x" + imageParameter.getWidth());
        command.add("!");
        command.add(imageParameter.getOutputFile());
        return command;
    }

    private List<String> thumbnail(final ImageParameter imageParameter) {
        List<String> command = new ArrayList<String>();
        command.add(config.getImageTool());
        command.add(imageParameter.getInputFile());
        command.add("-resize");
        command.add("128x128");
        command.add(imageParameter.getOutputFile());
        return command;
    }

    private List<String> selectingImageRegion(final ImageParameter imageParameter) {
        ConfigPath config = new ConfigPath();
        List<String> command = new ArrayList<String>();
        command.add(config.getImageTool());
        command.add(imageParameter.getInputFile());
        command.add("-crop");
        command.add(imageParameter.getCrop());
        command.add(imageParameter.getOutputFile());
        return command;
    }

    private List<String> grayScale(final ImageParameter imageParameter) {
        List<String> command = new ArrayList<String>();
        command.add(config.getImageTool());
        command.add(imageParameter.getInputFile());
        command.add("-colorspace");
        command.add("gray");
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
