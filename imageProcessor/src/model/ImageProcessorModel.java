package model;

import java.io.IOException;

/**
 * Represents a model for an image processor. Capable of loading, saving, brightening, darkening,
 * visualizing various components, and flipping horizontally or vertically.
 */
public interface ImageProcessorModel {


  /**
   * Loads the given ppm image to be used by the model.
   *
   * @param imageName the name of the image being loaded
   * @throws IllegalArgumentException if the image is not a ppm file
   */
  public void loadImage(String filePath, String imageName) throws IllegalArgumentException;

  /**
   * Saves an image as a ppm file with the given name.
   *
   * @param imageName name of the new image file
   */
  public void saveImage(String filePath, String imageName) throws IOException;

  /**
   * Creates an image that visualizes an individual R,G,B component.
   *
   * @param component the channel to be visualized (red, green, or blue)
   */
  public void visualizeRGB(ImageComponent component) throws IllegalArgumentException;

  /**
   * Visualizes the value using the maximum value of the three components for each pixel.
   */
  public void visualizeValue();

  /**
   * Visualizes the intensity using the average of the three components for each pixel.
   */
  public void visualizeIntensity();

  /**
   * Visualizes the luma component of the pixels using the weighted sum of the rgb values,
   * .2126r + .7152g + .0722b.
   */
  public void visualizeLuma();

  /**
   * Flips the image horizontally.
   */
  public void flipHorizontally();

  /**
   * Flips the image vertically.
   */
  public void flipVertically();

  /**
   * This brightens the image by the given magnitude.
   *
   * @param magnitude the amount to brighten the image by
   */
  public void brighten(int magnitude);

  /**
   * This darkens the image by the given magnitude.
   *
   * @param magnitude the amount to darken the image by
   */
  public void darken(int magnitude);

  /**
   * Retrieves the pixel at the given position with its rgb values.
   *
   * @param row row coordinate of the pixel
   * @param col column coordinate of the pixel
   * @return an array of the rgb values of the pixel at the given coordinates
   */
  public int[] getPixelAt(int row, int col);

  /**
   * Produces the width of the current image.
   *
   * @return the width of the image
   */
  public int getImageWidth();

  /**
   * Produces the height of the current image.
   *
   * @return the height of the image
   */
  public int getImageHeight();

  /**
   * This allows the user to input the name of the image they currently want to work with
   * in order to select it.
   *
   * @param imageName name of the image to be modified
   */
  public void selectCurrentImage(String imageName);

  /**
   * Stores the current image in the model under the given name.
   *
   * @param imageName name that the current image will be mapped to
   */
  public void storeCurrentImageAs(String imageName);
}