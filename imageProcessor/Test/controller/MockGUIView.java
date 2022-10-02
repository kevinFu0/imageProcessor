package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import controller.Features;
import view.ImageGuiView;

/**
 * Mock of the GUI View interface for testing purposes.
 */
public class MockGUIView implements ImageGuiView {
  private Appendable log;

  /**
   * Constructor for mockGUIView.
   *
   * @param log keeps track of what methods are called in the view
   */
  public MockGUIView(Appendable log) {
    this.log = log;
  }

  @Override
  public void initializeView() {
    try {
      this.log.append("initialized view\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void addFeatures(Features f) {
    try {
      this.log.append("added features\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void refreshImage(BufferedImage image) {
    try {
      this.log.append("refreshed image\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void refreshHistogram(int[][] histograms) {
    try {
      this.log.append("refreshed histograms\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void renderMessage(String message) {
    try {
      this.log.append("rendered message\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
