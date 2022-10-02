package controller;

/**
 * This interface represents the features present in an interactive image processor.
 */
public interface Features {

  /**
   * Saves the image to the given location.
   * @param filePath the filepath where the image is to be saved
   */
  void saveImage(String filePath);

  /**
   * Loads the image from the given location.
   * @param filePath the filepath where the image is coming from
   */
  void loadImage(String filePath);

  /**
   * Darkens the image by 20.
   */
  void darken();

  /**
   * Brightens the image by 20.
   */
  void brighten();

  /**
   * Blurs the image.
   */
  void blur();

  /**
   * Sharpens the image.
   */
  void sharpen();

  /**
   * Flips the image horizontally.
   */
  void flipHorizontal();

  /**
   * Flips the image vertically.
   */
  void flipVertical();

  /**
   * Applies a greyscale transformation to the image.
   */
  void greyscale();

  /**
   * Applies a sepia transformation to the image.
   */
  void sepia();

  /**
   * Creates an intensity visualization of the image.
   */
  void visualizeIntensity();

  /**
   * Creates a luma visualization of the image.
   */
  void visualizeLuma();

  /**
   * Creates a red component visualization of the image.
   */
  void visualizeRed();

  /**
   * Creates a green component visualization of the image.
   */
  void visualizeGreen();

  /**
   * Creates a blue component visualization of the image.
   */
  void visualizeBlue();

  /**
   * Creates a value visualization of the image.
   */
  void visualizeValue();
}
