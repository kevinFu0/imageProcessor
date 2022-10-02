package commands;

import model.EnhancedImageProcessorModel;
import model.ImageProcessorModel;

/**
 * Represents the blur command.
 */
public class Blur extends AbstractCommand {


  /**
   * Constructor for Blur.
   * @param c the name of the current image
   * @param n the name of the new image
   * @throws IllegalArgumentException if the arguments are invalid
   */
  public Blur(String c, String n) {
    super(c, n);
  }

  @Override
  public void execute(ImageProcessorModel im) {
    double[][] blurFilter = {{1 / 16.0, 1 / 9.0, 1 / 16.0},
        {1 / 8.0, 1 / 4.0, 1 / 8.0} , {1 / 16.0, 1 / 8.0, 1 / 16.0}};
    im.selectCurrentImage(currImage);
    EnhancedImageProcessorModel em = (EnhancedImageProcessorModel) im;
    em.applyFilter(blurFilter);
    em.storeCurrentImageAs(newImage);
  }
}
