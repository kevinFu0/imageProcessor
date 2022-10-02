package controller;


/**
 *represents a controller for image processor.
 */
public interface ImageProcessController {


  /**
   * runs the image processors.
   * @throws IllegalStateException if the controller is unable
   *                                   to successfully read input or transmit output
   */
  void run() throws IllegalStateException;
}
