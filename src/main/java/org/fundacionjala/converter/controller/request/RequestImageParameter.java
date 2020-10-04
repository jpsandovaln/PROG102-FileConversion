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
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets height value
     * @param height the height to set
     */
    public void setHeight(final int height) {
        this.height = height;
    }

    /**
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets width value
     * @param width the width to set
     */
    public void setWidth(final int width) {
        this.width = width;
    }

    /**
     * @return extractThumbnail
     */
    public boolean getExtractThumbnail() {
        return extractThumbnail;
    }

    /**
     * Sets extractThumbnail value
     * @param extractThumbnail the extractThumbnail to set
     */
    public void setExtractThumbnail(final boolean extractThumbnail) {
        this.extractThumbnail = extractThumbnail;
    }

    /**
     * @return extractMetadata
     */
    public boolean getExtractMetadata() {
        return extractMetadata;
    }

    /**
     * Sets extractMetadata value
     * @param extractMetadata - the extractMetadata to set
     */
    public void setExtractMetadata(final boolean extractMetadata) {
        this.extractMetadata = extractMetadata;
    }

    /**
     * @return position
     */
    public String getPosition() {
        return position;
    }

    /**
     * Sets position value
     * @param position the position to set
     */
    public void setPosition(final String position) {
        this.position = position;
    }

    /**
     * @return changeSize
     */
    public boolean getChangeSize() {
        return changeSize;
    }

    /**
     * Sets changeSize value
     * @param changeSize - the changeSize to set
     */
    public void setChangeSize(final boolean changeSize) {
        this.changeSize = changeSize;
    }

    /**
     * @return gray
     */
    public boolean getGray() {
        return gray;
    }

    /**
     * Sets gray value
     * @param gray the gray to set
     */
    public void setGray(final boolean gray) {
        this.gray = gray;
    }

    /**
     * Validates image parameters
     * @throws Exception
     */
    @Override
    public void validate() throws Exception {
        super.validate();
        if (this.getHeight() < 1) {
            throw new Exception("Invalid height");
        }
        if (this.getWidth() < 1) {
            throw new Exception("Invalid width");
        }
    }
}
