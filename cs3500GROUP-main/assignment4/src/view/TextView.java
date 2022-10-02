package view;


import java.io.IOException;

/**
 * represents a view class for text-based image processor.
 */
public class TextView implements ImageProcessorView {

  private Appendable destination;

  /**
   * constructor for textview.
   * @param destination appendable object.
   * @throws IllegalArgumentException if appendable is null.
   */
  public TextView(Appendable destination) throws IllegalArgumentException {
    if (destination == null) {
      throw new IllegalArgumentException("appendable can't be null");
    }
    this.destination = destination;
  }


  @Override
  public void renderMessage(String msg) throws IOException {
    this.destination.append(msg);
  }
}
