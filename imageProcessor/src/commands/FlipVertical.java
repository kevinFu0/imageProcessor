package commands;

import model.ImageProcessorModel;

/**
 * flip vertical command.
 */
public class FlipVertical implements Command {

  private String currImage;
  private String newImage;

  /**
   * constructor for flipHorizontal.
   *
   * @param c the current Image String
   * @param n the new image String
   * @throws IllegalArgumentException if the strings are null
   */
  public FlipVertical(String c, String n) throws IllegalArgumentException {
    if (c == null || n == null) {
      throw new IllegalArgumentException("arguments can't be null");
    }
    this.currImage = c;
    this.newImage = n;
  }

  @Override
  public void execute(ImageProcessorModel im) {
    im.selectCurrentImage(currImage);
    im.flipVertically();
    im.storeCurrentImageAs(newImage);
  }
}