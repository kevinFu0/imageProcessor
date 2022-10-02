package commands;

import model.EnhancedImageProcessorModel;
import model.ImageProcessorModel;

/**
 * The Sharpen command.
 */
public class Sharpen extends AbstractCommand {

  /**
   * Constructor for Sharpen.
   *
   * @param c currImage string.
   * @param n newImage string.
   */
  public Sharpen(String c, String n) {
    super(c, n);
  }

  @Override
  public void execute(ImageProcessorModel im) {
    double[][] sharpenFilter = {{-1 / 8.0, -1 / 8.0, -1 / 8.0, -1 / 8.0, -1 / 8.0},
        {-1 / 8.0, 1 / 4.0, 1 / 4.0, 1 / 4.0, -1 / 8.0},
        {-1 / 8.0, 1 / 4.0, 1, 1 / 4.0, -1 / 8.0},
        {-1 / 8.0, 1 / 4.0, 1 / 4.0, 1 / 4.0, -1 / 8.0},
        {-1 / 8.0, -1 / 8.0, -1 / 8.0, -1 / 8.0, -1 / 8.0}};
    im.selectCurrentImage(currImage);
    EnhancedImageProcessorModel em = (EnhancedImageProcessorModel) im;
    em.applyFilter(sharpenFilter);
    em.storeCurrentImageAs(newImage);
  }
}
