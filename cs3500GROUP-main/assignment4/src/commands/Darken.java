package commands;

import model.ImageProcessorModel;

/**
 * darken command.
 */
public class Darken implements Command {

  private String currImage;
  private String newImage;
  int magnitude;

  /**
   * constructor for brighten.
   * @param m the magnitude.
   * @param c currImage string.
   * @param n newImage string.
   * @throws IllegalArgumentException if the arguments are invalid
   */
  public Darken(int m, String c, String n) throws IllegalArgumentException {
    if (m < 0 || c == null || n == null) {
      throw new IllegalArgumentException("invalid inputs");
    }
    this.magnitude = m;
    this.currImage = c;
    this.newImage = n;
  }

  @Override
  public void execute(ImageProcessorModel im) {
    im.selectCurrentImage(currImage);
    im.darken(magnitude);
    im.storeCurrentImageAs(newImage);
  }
}
