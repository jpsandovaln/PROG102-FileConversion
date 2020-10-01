package org.fundacionjala.converter.model.parameter.image;

import org.fundacionjala.converter.model.parameter.ModelParameter;

public class ImageParameter extends ModelParameter {

    private int height;
    private int width;
    private String positionXAndPositionY;
    private String name;
    private boolean isGrayScale;
    private boolean isThumbnail;
    private boolean isRezise;
    private boolean isMetadata;

    /**
     * @return isMetadata
     */
    public boolean isMetadata() {
        return isMetadata;
    }

    /**
     * Sets metadata value
     * @param metadata the metadata to set
     */
    public void setMetadata(final boolean metadata) {
        isMetadata = metadata;
    }

    /**
     * @return positionXandPositionY
     */
    public String getPositionXAndPositionY() {
        return positionXAndPositionY;
    }

    /**
     * Sets positionXAndPositionY value
     * @param positionXAndPositionY the positionXAndPositionY to set
     */
    public void setPositionXAndPositionY(final String positionXAndPositionY) {
        this.positionXAndPositionY = positionXAndPositionY;
    }

    /**
     * @return isMetadata
     */
    public boolean getIsMetadata() {
        return isMetadata;
    }

    /**
     * @return isGrayScale
     */
    public boolean isGrayScale() {
        return isGrayScale;
    }

    /**
     * Sets grayScale value
     * @param grayScale the grayScale to set
     */
    public void setGrayScale(final boolean grayScale) {
        isGrayScale = grayScale;
    }

    /**
     * @return isThumbnail
     */
    public boolean isThumbnail() {
        return isThumbnail;
    }

    /**
     * Sets isThumbnail value
     * @param thumbnail the thumbnail to set
     */
    public void setThumbnail(final boolean thumbnail) {
        isThumbnail = thumbnail;
    }

    /**
     * @return isRezise
     */
    public boolean isRezise() {
        return isRezise;
    }

    /**
     * Sets rezise value
     * @param rezise the rezise to set
     */
    public void setRezise(final boolean rezise) {
        isRezise = rezise;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name value
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return isGrayScale
     */
    public boolean getIsGray() {
        return isGrayScale;
    }

    /**
     * Sets isGrayScale value
     * @param isGrayScale the isGrayScale to set
     */
    public void setIsGray(final boolean isGrayScale) {
        this.isGrayScale = isGrayScale;
    }

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
     * @return startPositionX + "+" + startPositionY
     */
    public String setPositionXAndPositionY(final String startPositionX, final String startPositionY) {
        return startPositionX + "+" + startPositionY;
    }

    /**
     * @return height + "x" + width + "+" + positionXAndPositionY
     */
    public String getCrop() {
        return height + "x" + width + "+" + positionXAndPositionY;
    }

    /**
     * @return false
     */
    public boolean getSelectingImageRegion() {
        return false;
    }

    /**
     * Sets isThumbnail value
     * @param isThumbnail the isThumbnail to set
     */
    public void setIsThumbnail(final boolean isThumbnail) {
        this.isThumbnail = isThumbnail;
    }

    /**
     * @return isThumbnail()
     */
    public boolean getIsThumbnail() {
        return isThumbnail;
    }

    /**
     * @return isResize
     */
    public boolean getIsResize() {
        return isRezise;
    }

    /**
     * Sets isResize value
     * @param isResize the isResize to set
     */
    public void setIsResize(final boolean isRezise) {
        this.isRezise = isRezise;
    }
}
