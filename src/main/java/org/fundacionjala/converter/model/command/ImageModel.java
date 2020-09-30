package org.fundacionjala.converter.model.command;

import org.fundacionjala.converter.model.configPath.ConfigPath;
import org.fundacionjala.converter.model.parameter.ModelParameter;
import org.fundacionjala.converter.model.parameter.image.ImageParameter;
import org.fundacionjala.converter.model.parameter.metadata.MetadataParameter;

import java.util.ArrayList;
import java.util.List;

public class ImageModel implements ICommand {

    private ConfigPath config;
    private static final String RESIZE = "-resize";
    private static final String X = "X";
    private static final String BACKSLASH = "/";
    private static final String ADMIRATIONSIGN = "!";
    private static final String RESIZEDSUFIX = "(resized)";
    private static final String THUMBNAILSUFIX = "(thumbnail)";
    private static final String GRAYSUFIX = "(gray)";
    private static final String SIZETHUMBNAIL = "128X128";
    private static final String CROP = "-crop";
    private static final String COLORSPACE = "-colorspace";
    private static final String IMAGEREGION = "(imageregion)";
    private static final String GRAY = "gray";
    private static final String META_SUFFIX = "-meta", JSON = "j", VOID = "", VERBOSE = "v";
    public ImageModel() {
        config = new ConfigPath();
    }

    private List<String> resize(final ImageParameter imageParameter) {
        List<String> command = new ArrayList<String>();
        command.add(config.getImageTool());
        command.add(imageParameter.getInputFile());
        command.add(RESIZE);
        command.add(imageParameter.getWidth() + X + imageParameter.getHeight() + ADMIRATIONSIGN);
        name(imageParameter);
        command.add(imageParameter.getOutputFile() + BACKSLASH + imageParameter.getMd5() + RESIZEDSUFIX + imageParameter.getFormat());
        return command;
    }

    private List<String> thumbnail(final ImageParameter imageParameter) {
        List<String> command = new ArrayList<String>();
        command.add(config.getImageTool());
        command.add(imageParameter.getInputFile());
        command.add(RESIZE);
        command.add(SIZETHUMBNAIL);
        name(imageParameter);
        command.add(imageParameter.getOutputFile() + BACKSLASH + imageParameter.getMd5() + THUMBNAILSUFIX + imageParameter.getFormat());
        return command;
    }

    private List<String> selectingImageRegion(final ImageParameter imageParameter) {
        ConfigPath config = new ConfigPath();
        List<String> command = new ArrayList<String>();
        command.add(config.getImageTool());
        command.add(imageParameter.getInputFile());
        command.add(CROP);
        imageParameter.setPositionXAndPositionY(imageParameter.getPositionXAndPositionY().replaceAll(",", "+"));
        command.add(imageParameter.getCrop());
        name(imageParameter);
        command.add(imageParameter.getOutputFile() + BACKSLASH + imageParameter.getMd5() + IMAGEREGION + imageParameter.getFormat());
        return command;
    }

    private List<String> grayScale(final ImageParameter imageParameter) {
        List<String> command = new ArrayList<String>();
        command.add(config.getImageTool());
        command.add(imageParameter.getInputFile());
        command.add(COLORSPACE);
        command.add(GRAY);
        name(imageParameter);
        command.add(imageParameter.getOutputFile() + BACKSLASH + imageParameter.getMd5() + GRAYSUFIX + imageParameter.getFormat());
        return command;
    }
    private List<String> extractMetadata(final ImageParameter imageParameter, final String suffix) {
        String input = imageParameter.getOutputFile() + BACKSLASH + imageParameter.getMd5() + suffix + imageParameter.getFormat();
        String output = imageParameter.getMd5() + suffix + "0" + imageParameter.getFormat().substring(1) + META_SUFFIX;
        MetadataParameter metadataParameter = new MetadataParameter(input, JSON, VERBOSE, output, VOID);
        MetadataModel metadataModel = new MetadataModel();
        return metadataModel.createCommand(metadataParameter).get(0);
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
        if (imageParameter.getPositionXAndPositionY() != null) {
            listCommands.add(selectingImageRegion(imageParameter));
        }

        if (imageParameter.getIsThumbnail()) {
            listCommands.add(thumbnail(imageParameter));
        }

        if (imageParameter.getIsResize()) {
            listCommands.add(resize(imageParameter));
        }
        if (imageParameter.getIsMetadata()) {
            if (imageParameter.getIsGray()) {
                listCommands.add(extractMetadata(imageParameter, GRAYSUFIX));
            }
            if (imageParameter.getIsResize()) {
                listCommands.add(extractMetadata(imageParameter, RESIZEDSUFIX));
            }
            if (imageParameter.getIsThumbnail()) {
                listCommands.add(extractMetadata(imageParameter, THUMBNAILSUFIX));
            }
            if (imageParameter.getPositionXAndPositionY() != null) {
                listCommands.add(extractMetadata(imageParameter, IMAGEREGION));
            }
        }
        return listCommands;
    }
}
