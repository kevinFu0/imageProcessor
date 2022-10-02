package model;

/**
 * A model for an image processor that can load, save, and perform operations on images,
 * including creating a histogram of pixel component data.
 */
public interface ImageProcessorModelHistogram extends EnhancedImageProcessorModel {

  /**
   * Creates a histogram of pixel component data.
   *
   * @param type the type of histogram being created (red, green, blue, or intensity)
   * @param imageName the name of the image this histogram is based on
   * @return
   */
  int[] getHistogram(HistValueType type, String imageName);
}
