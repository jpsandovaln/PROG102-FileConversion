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
    private static final String RESIZED_SUFIX = "(resized)";
    private static final String THUMBNAIL_SUFIX = "(thumbnail)";
    private static final String GRAY_SUFIX = "(gray)";
    private static final String SIZETHUMBNAIL = "128X128";
    private static final String CROP = "-crop";
    private static final String FORMAT_SUFIX = "(FORMAT)";
    private static final String COLORSPACE = "-colorspace";
    private static final String IMAGEREGION = "(imageregion)";
    private static final String GRAY = "gray";
    private static final String META_SUFFIX = "-meta", JSON = "j", VOID = "", VERBOSE = "v";

    public ImageModel() {
    }

    /**
     *Creates a list of what will be sent to the console
     * @param imageParameter the attributes to set
     * @return the list with attibutes of the command of the resize
     */
    private List<String> resize(final ImageParameter imageParameter) {
        List<String> command = new ArrayList<String>();
        command.add(ConfigPath.getImageTool());
        command.add(imageParameter.getInputFile());
        command.add(RESIZE);
        command.add(imageParameter.getWidth() + X + imageParameter.getHeight() + ADMIRATIONSIGN);
        name(imageParameter);
        command.add(imageParameter.getOutputFile() + BACKSLASH + imageParameter.getMd5() + RESIZED_SUFIX + imageParameter.getFormat());
        return command;
    }

    /**
     *Creates a list of what will be sent to the console
     * @param imageParameter the attributes to set
     * @return the list with attributes of the command only format
     */
    private List<String> format(final ImageParameter imageParameter) {
        List<String> command = new ArrayList<String>();
        command.add(ConfigPath.getImageTool());
        command.add(imageParameter.getInputFile());
        command.add(imageParameter.getOutputFile() + BACKSLASH + imageParameter.getMd5() + FORMAT_SUFIX + imageParameter.getFormat());
        return command;
    }

    /**
     *Creates a list of what will be sent to the console
     * @param imageParameter the attributes to set
     * @return the list with attributes of the command thumbnail
     */
    private List<String> thumbnail(final ImageParameter imageParameter) {
        List<String> command = new ArrayList<String>();
        command.add(config.getImageTool());
        command.add(imageParameter.getInputFile());
        command.add(RESIZE);
        command.add(SIZETHUMBNAIL);
        name(imageParameter);
        command.add(imageParameter.getOutputFile() + BACKSLASH + imageParameter.getMd5() + THUMBNAIL_SUFIX + imageParameter.getFormat());
        return command;
    }

    /**
     *Creates a list of what will be sent to the console
     * @param imageParameter the attributes to set
     * @return the list with attributes of the command of the crop
     */
    private List<String> selectingImageRegion(final ImageParameter imageParameter) {
        List<String> command = new ArrayList<String>();
        command.add(ConfigPath.getImageTool());
        command.add(imageParameter.getInputFile());
        command.add(CROP);
        imageParameter.setPositionXAndPositionY(imageParameter.getPositionXAndPositionY().replaceAll(",", "+"));
        command.add(imageParameter.getCrop());
        name(imageParameter);
        command.add(imageParameter.getOutputFile() + BACKSLASH + imageParameter.getMd5() + IMAGEREGION + imageParameter.getFormat());
        return command;
    }

    /**
     *Creates a list of what will be sent to the console
     * @param imageParameter the attributes to set
     * @return the list with attributes of the command GrayScale
     */
    private List<String> grayScale(final ImageParameter imageParameter) {
        List<String> command = new ArrayList<String>();
        command.add(ConfigPath.getImageTool());
        command.add(imageParameter.getInputFile());
        command.add(COLORSPACE);
        command.add(GRAY);
        name(imageParameter);
        command.add(imageParameter.getOutputFile() + BACKSLASH + imageParameter.getMd5() + GRAY_SUFIX + imageParameter.getFormat());
        return command;
    }

    /**
     *Creates a list of what will be sent to the console
     * @param imageParameter the attributes to set
     * @param suffix the sufix that will have the file
     * @return the list with attributes of the command the metadata
     */
    public List<String> extractMetadata(final ImageParameter imageParameter, final String suffix) {
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

        if (imageParameter.getIsThumbnail()) {
            listCommands.add(thumbnail(imageParameter));
        }

        if (imageParameter.getPositionXAndPositionY() != null) {
            listCommands.add(selectingImageRegion(imageParameter));
        }
        if ((imageParameter.getPositionXAndPositionY() == null) && (!imageParameter.getIsThumbnail()) && (!imageParameter.getIsGray()) && (!imageParameter.isRezise())) {
            listCommands.add(format(imageParameter));
        }

        if (imageParameter.getIsResize()) {
            listCommands.add(resize(imageParameter));

            if (imageParameter.getIsMetadata()) {
                if ((imageParameter.getPositionXAndPositionY() == null) && (!imageParameter.getIsThumbnail()) && (!imageParameter.getIsGray()) && (!imageParameter.isRezise())) {
                    listCommands.add(extractMetadata(imageParameter, FORMAT_SUFIX));
                }
                if (imageParameter.getIsGray()) {
                    listCommands.add(extractMetadata(imageParameter, GRAY_SUFIX));
                }
                if (imageParameter.getIsResize()) {
                    listCommands.add(extractMetadata(imageParameter, RESIZED_SUFIX));
                }
                if (imageParameter.getIsThumbnail()) {
                    listCommands.add(extractMetadata(imageParameter, THUMBNAIL_SUFIX));
                }
                if (imageParameter.getPositionXAndPositionY() != null) {
                    listCommands.add(extractMetadata(imageParameter, IMAGEREGION));
                }
            }
        }
        return listCommands;
    }
}
