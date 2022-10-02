package commands;

import model.ImageProcessorModel;

/**
 * visualize value command.
 */
public class VisualizeValue implements Command {

  private String currImage;
  private String newImage;

  /**
   * constructor for visualizeValue.
   *
   * @param c the current Image String
   * @param n the new image String
   * @throws IllegalArgumentException if the strings are null
   */
  public VisualizeValue(String c, String n) throws IllegalArgumentException {
    if (c == null || n == null) {
      throw new IllegalArgumentException("arguments can't be null");
    }
    this.currImage = c;
    this.newImage = n;
  }

  @Override
  public void execute(ImageProcessorModel im) {
    im.selectCurrentImage(currImage);
    im.visualizeValue();
    im.storeCurrentImageAs(newImage);
  }
}