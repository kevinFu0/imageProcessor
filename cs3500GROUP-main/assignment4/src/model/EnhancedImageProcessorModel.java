package model;

/**
 * Represents an enhanced model for an image processor that loads and saves ppm, bmp, png, and jpg
 * images. Adds the ability to apply filters and transformations.
 */
public interface EnhancedImageProcessorModel extends ImageProcessorWithCommand {


  /**
   * Applies the given filter kernel to the current image.
   * @param kernel to be applied to each pixel
   */
  void applyFilter(double[][] kernel);

  /**
   * Applies the given transformation matrix to the current image using matrix multiplication.
   * @param transformation the transformation matrix to be applied to each pixel
   */
  void applyTransformation(double[][] transformation);



}
