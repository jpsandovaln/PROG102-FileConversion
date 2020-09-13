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
    private int positionX;
    private int positionY;
    private int forceResize;
    private int thumbnail;

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
    public int getPositionX() {
        return positionX;
    }

    /**
     *
     * @param positionX
     */
    public void setPositionX(final int positionX) {
        this.positionX = positionX;
    }

    /**
     *
     * @return
     */
    public int getPositionY() {
        return positionY;
    }

    /**
     *
     * @param positionY
     */
    public void setPositionY(final int positionY) {
        this.positionY = positionY;
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

    /**
     *
     * @return
     */
    @Override
    public boolean validate() {
        return false;
    }
}
