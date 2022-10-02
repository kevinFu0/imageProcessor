package commands;

import model.ImageProcessorModel;

/**
 * visualize intensity command.
 */
public class VisualizeIntensity implements Command {

  private String currImage;
  private String newImage;

  /**
   * constructor for visualizeIntensity.
   *
   * @param c the current Image String
   * @param n the new image String
   * @throws IllegalArgumentException if the strings are null
   */
  public VisualizeIntensity(String c, String n) throws IllegalArgumentException {
    if (c == null || n == null) {
      throw new IllegalArgumentException("arguments can't be null");
    }
    this.currImage = c;
    this.newImage = n;
  }

  @Override
  public void execute(ImageProcessorModel im) {
    im.selectCurrentImage(currImage);
    im.visualizeIntensity();
    im.storeCurrentImageAs(newImage);
  }
}
