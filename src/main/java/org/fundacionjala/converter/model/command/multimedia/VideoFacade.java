/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.model.command.multimedia;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.commons.io.FilenameUtils;
import org.fundacionjala.converter.executor.Executor;
import org.fundacionjala.converter.model.command.MetadataModel;
import org.fundacionjala.converter.model.configPath.ConfigPath;
import org.fundacionjala.converter.model.parameter.metadata.MetadataParameter;
import org.fundacionjala.converter.model.parameter.multimedia.VideoParameter;

/**
 * @author Laura Montaño
 * @version 0.1
 */
public class VideoFacade {

    private VideoModel videoModel;
    private List<List<String>> list;
    private List<String> fullList;
    private List<String> outputFiles;
    private List<List<String>> listMetadataCommands;
    private List<MetadataParameter> listMetadataParameters;
    private ConfigPath configPath;
    private static final String FORMAT = "j";   // json
    private static final String DETAIL = "v";
    private static final String THUMBNAIL = "(thumbnail)";

    public VideoFacade() {
        this.videoModel = new VideoModel();
    }

    /**
     * Creates a list of commands for the parameters given
     * @param videoParameter - the reference VideoParameter with parameters of video
     * @return List<List<String>> - list of commands
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private List<List<String>> getListMetadataCommands(final VideoParameter videoParameter)
    throws NoSuchAlgorithmException, IOException, InterruptedException, ExecutionException {
        listMetadataCommands = new ArrayList<>();
        listMetadataParameters = new ArrayList<>();
        String checksum = "";
        for (String path : videoParameter.getOutputFiles()) {
            listMetadataParameters.add(new MetadataParameter(path, FORMAT, DETAIL, configPath.getConvertedFilesPath() + FilenameUtils.getBaseName(path), checksum));
        }
        for (MetadataParameter metadataParameter : listMetadataParameters) {
            listMetadataCommands.addAll(new MetadataModel().createCommand(metadataParameter));
        }
        return listMetadataCommands;
    }

    /**
     * Creates a list of commands for the parameters given
     * @param videoParameter - the reference VideoParameter with parameters of video
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public List<String> convertVideo(final VideoParameter videoParameter)
            throws NoSuchAlgorithmException, IOException, InterruptedException, ExecutionException {
        list = new ArrayList<>();
        fullList = new ArrayList<>();
        outputFiles = new ArrayList<String>();
        videoParameter.setOutputFiles(outputFiles);
        Executor executor = new Executor();
        list.addAll(videoModel.createCommand(videoParameter));
        if (videoParameter.isExtractThumbnail()) {
            videoParameter.setFormat(VideoParameter.GIF);
            String newName = videoParameter.getMd5() + THUMBNAIL;
            videoParameter.setName(newName);
            list.add(videoModel.extractThumbnail(videoParameter));
        }
        if (videoParameter.isExtractMetadata()) {
            list.addAll(getListMetadataCommands(videoParameter));
        }
        fullList = executor.executeCommandsList(list);
        return fullList;
    }
}
