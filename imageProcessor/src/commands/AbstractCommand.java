package commands;

import model.ImageProcessorModel;

/**
 * Abstract command class.
 */
public abstract class AbstractCommand implements Command {
  protected String currImage;
  protected String newImage;

  /**
   * Constructor for AbstractCommand.
   *
   * @param c the name of the current image
   * @param n the name of the new image
   */
  public AbstractCommand(String c, String n) {
    if (c == null || n == null) {
      throw new IllegalArgumentException("invalid inputs");
    }
    this.currImage = c;
    this.newImage = n;
  }

  @Override
  public abstract void execute(ImageProcessorModel im);
}
