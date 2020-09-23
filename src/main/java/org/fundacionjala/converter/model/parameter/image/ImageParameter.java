package org.fundacionjala.converter.model.parameter.image;

import org.fundacionjala.converter.model.parameter.ModelParameter;

public class ImageParameter extends ModelParameter {

  private int height;
  private int width;
  private String positionXAndPositionY;
  private boolean isGrayScale;
  private boolean isThumbnail;
  private boolean isRezise;
  /**
   *
   * @return
   */
  public String getPositionXAndPositionY() {
    return positionXAndPositionY;
  }

  /**
   *
   * @param positionXAndPositionY
   */
  public void setPositionXAndPositionY(final String positionXAndPositionY) {
    this.positionXAndPositionY = positionXAndPositionY;
  }

  /**
   *
   * @return
   */
  public boolean isGrayScale() {
    return isGrayScale;
  }

  /**
   *
   * @param grayScale
   */
  public void setGrayScale(final boolean grayScale) {
    isGrayScale = grayScale;
  }

  /**
   *
   * @return
   */
  public boolean isThumbnail() {
    return isThumbnail;
  }

  /**
   *
   * @param thumbnail
   */
  public void setThumbnail(final boolean thumbnail) {
    isThumbnail = thumbnail;
  }

  /**
   *
   * @return
   */
  public boolean isRezise() {
    return isRezise;
  }

  /**
   *
   * @param rezise
   */
  public void setRezise(final boolean rezise) {
    isRezise = rezise;
  }

  /**
   *
   * @return
   */
  public String getName() {
    return name;
  }

  /**
   *
   * @param name
   */
  public void setName(final String name) {
    this.name = name;
  }

  private String name;

  /**
   * getIsGray()
   */
  public boolean getIsGray() {
    return isGrayScale;
  }

  /**
   * setIsGray()
   */
  public void setIsGray(final boolean isGrayScale) {
    this.isGrayScale = isGrayScale;
  }

  /**
   * getHeight()
   */
  public int getHeight() {
    return height;
  }

  /**
   * setHeight()
   */
  public void setHeight(final int height) {
    this.height = height;
  }

  /**
   * getWidth()
   */
  public int getWidth() {
    return width;
  }

  /**
   * setWidth()
   */
  public void setWidth(final int width) {
    this.width = width;
  }

  /**
   * setPositionXAndPositionY()
   */
  public String setPositionXAndPositionY(final String startPositionX, final String startPositionY) {
    return startPositionX + "+" + startPositionY;
  }

  /**
   * getCrop()
   */
  public String getCrop() {
    return height + "x" + width + "+" + positionXAndPositionY;
  }

  /**
   * getSelectingImageRegion()
   */
  public boolean getSelectingImageRegion() {
    return false;
  }

  /**
   * setIsThumbnail(final boolean isThumbnail)
   */
  public void setIsThumbnail(final boolean isThumbnail) {
    this.isThumbnail = isThumbnail;
  }

  /**
   * getIsThumbnail()
   */
  public boolean getIsThumbnail() {
    return isThumbnail;
  }

  /**
   * getIsResize()
   */
  public boolean getIsResize() {
    return isRezise;
  }

  /**
   * setIsResize()
   */
  public void setIsResize(final boolean isRezise) {
    this.isRezise = isRezise;
  }
}
