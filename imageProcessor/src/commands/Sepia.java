package commands;

import model.EnhancedImageProcessorModel;
import model.ImageProcessorModel;

/**
 * Represents sepia command.
 */
public class Sepia extends AbstractCommand {

  /**
   * Constructor for Sepia.
   *
   * @param c the name of the current image
   * @param n the name of the new image
   */
  public Sepia(String c, String n) {
    super(c, n);
  }

  @Override
  public void execute(ImageProcessorModel im) {
    double[][] sepiaTransformation = {{.393, .769, .189},
        {.349, .686, .168}, {.272, .534, .131}};
    im.selectCurrentImage(currImage);
    EnhancedImageProcessorModel em = (EnhancedImageProcessorModel) im;
    em.applyTransformation(sepiaTransformation);
    em.storeCurrentImageAs(newImage);
  }
}
