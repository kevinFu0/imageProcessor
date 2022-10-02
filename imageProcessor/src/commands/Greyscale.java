package commands;

import model.EnhancedImageProcessorModel;
import model.ImageProcessorModel;

/**
 * Represents greyscale command.
 */
public class Greyscale extends AbstractCommand {

  /**
   * Constructor for Greyscale.
   *
   * @param c name of the current image
   * @param n name of the new image
   */
  public Greyscale(String c, String n) {
    super(c, n);
  }

  @Override
  public void execute(ImageProcessorModel im) {
    double[][] gsTransformation = {{.2126, .7152, .0722},
        {.2126, .7152, .0722}, {.2126, .7152, .0722}};
    im.selectCurrentImage(currImage);
    EnhancedImageProcessorModel em = (EnhancedImageProcessorModel) im;
    em.applyTransformation(gsTransformation);
    em.storeCurrentImageAs(newImage);
  }
}
