/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller.request;

import org.springframework.web.multipart.MultipartFile;

public class RequestImageParameter extends RequestParameter {

    private int height;
    private int width;
    private int positionXAndPositionY;
    private int forceResize;
    private int thumbnail;

    public RequestImageParameter(final MultipartFile file, final String format, final int height,
                                 final int width, final int positionXAndPositionY, final int forceResize, final int thumbnail) {
        super(file, format);
        this.height = height;
        this.width = width;
        this.positionXAndPositionY = positionXAndPositionY;
        this.forceResize = forceResize;
        this.thumbnail = thumbnail;
    }

    /**
     *
     * @throws Exception
     */
    @Override
    public void validate() throws Exception {
        super.validate();
        if ("".equals(this.getHeight())) {
            throw new Exception("failed Height empty");
        }
        if ("".equals(this.getWidth())) {
            throw new Exception("failed Width empty");
        }
        if ("".equals(this.getForceResize())) {
            throw new Exception("failed Force Resize empty");
        }
        if ("".equals(this.getThumbnail())) {
            throw new Exception("failed Thumbnail empty");
        }
    }

    /**
     *
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     *
     * @param height
     */
    public void setHeight(final int height) {
        this.height = height;
    }

    /**
     *
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     *
     * @param width
     */
    public void setWidth(final int width) {
        this.width = width;
    }
    /**
     *
     * @return
     */
    public int getForceResize() {
        return forceResize;
    }

    /**
     *
     * @param forceResize
     */
    public void setForceResize(final int forceResize) {
        this.forceResize = forceResize;
    }

    /**
     *
     * @return
     */
    public int getThumbnail() {
        return thumbnail;
    }

    /**
     *
     * @param thumbnail
     */
    public void setThumbnail(final int thumbnail) {
        this.thumbnail = thumbnail;
    }

}
