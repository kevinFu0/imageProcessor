package commands;

import model.ImageComponent;
import model.ImageProcessorModel;

/**
 * visualize rgb command.
 */
public class VisualizeRGB implements Command {

  private String currImage;
  private String newImage;
  ImageComponent component;

  /**
   * constructor for RGB that takes in three strings from Scanner.
   *
   * @param next  the color
   * @param next1 the currImage
   * @param next2 the newImage
   * @throws IllegalArgumentException if the color is not valid.
   */
  public VisualizeRGB(String next, String next1, String next2) throws IllegalArgumentException {
    String comp = next.toLowerCase();
    if (!(comp.equals("green") || comp.equals("red") || comp.equals("blue"))) {
      throw new IllegalArgumentException("invalid color");
    } else {
      if (comp.equals("green")) {
        component = ImageComponent.green;
      }
      if (comp.equals("red")) {
        component = ImageComponent.red;
      }
      if (comp.equals("blue")) {
        component = ImageComponent.blue;
      }
      this.currImage = next1;
      this.newImage = next2;
    }
  }

  @Override
  public void execute(ImageProcessorModel im) {
    im.selectCurrentImage(currImage);
    im.visualizeRGB(component);
    im.storeCurrentImageAs(newImage);
  }
}
