/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller.request;

public class RequestImageParameter extends RequestParameter {

    private int height;
    private int width;
    private String position;
    private boolean changeSize;
    private boolean gray;
    private boolean extractThumbnail;
    private boolean extractMetadata;

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
        if ("".equals(this.getGray())) {
            throw new Exception("failed Force Resize empty");
        }
        if ("".equals(this.getExtractThumbnail())) {
            throw new Exception("failed Thumbnail empty");
        }
    }

    /**
     *
     * @return
     */
    public boolean getExtractThumbnail() {
        return extractThumbnail;
    }

    /**
     *
     * @param extractThumbnail
     */
    public void setExtractThumbnail(final boolean extractThumbnail) {
        this.extractThumbnail = extractThumbnail;
    }

    /**
     *
     * @return
     */
    public boolean getExtractMetadata() {
        return extractMetadata;
    }

    /**
     *
     * @param extractMetadata
     */
    public void setExtractMetadata(final boolean extractMetadata) {
        this.extractMetadata = extractMetadata;
    }

    /**
     *
     * @return
     */
    public String getPosition() {
        return position;
    }

    /**
     *
     * @param position
     */
    public void setPosition(final String position) {
        this.position = position;
    }

    /**
     *
     * @return
     */
    public boolean getChangeSize() {
        return changeSize;
    }

    /**
     *
     * @param changeSize
     */
    public void setChangeSize(final boolean changeSize) {
        this.changeSize = changeSize;
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
    public boolean getGray() {
        return gray;
    }

    /**
     *
     * @param gray
     */
    public void setGray(final boolean gray) {
        this.gray = gray;
    }

}
