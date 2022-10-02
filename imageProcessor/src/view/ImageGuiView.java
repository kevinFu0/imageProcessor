package view;

import java.awt.image.BufferedImage;

import controller.Features;

/**
 * Represents a GUI view containing the methods necessary for displaying an image processor program.
 */
public interface ImageGuiView {

  /**
   * Initializes the view by creating buttons, panels, labels, etc.
   */
  void initializeView();

  /**
   * Adds features based on the features interface to the corresponding buttons
   * in the GUI.
   * @param f object that implements the features interface
   */
  void addFeatures(Features f);

  /**
   * Displays the given image in the window.
   * @param image the image to be displayed
   */
  void refreshImage(BufferedImage image);


  /**
   * Updates the histogram displayed so that it matches the given histogram values
   * and frequencies.
   * @param histograms an array of histogram arrays where the first index is the type of histogram
   *                  (0 = red, 1 = green, 2 = blue, 3 = intensity) and the second index is
   *                   the value (0 - 255)
   */
  void refreshHistogram(int[][] histograms);

  /**
   * Displays the given message in the GUI window.
   * @param message the message to be displayed
   */
  void renderMessage(String message);
}
