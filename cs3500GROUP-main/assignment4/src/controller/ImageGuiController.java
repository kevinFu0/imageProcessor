package controller;

import model.EnhancedImageProcessorModel;
import model.ImageProcessorHistogramImpl;
import model.ImageProcessorModelHistogram;
import view.ImageGuiView;

public class ImageGuiController implements ImageProcessController {
  String currentImage;
  ImageProcessorModelHistogram model;
  ImageGuiView view;

  public ImageGuiController(EnhancedImageProcessorModel m, ImageGuiView view) {
    this.model = new ImageProcessorHistogramImpl(m);
    this.view = view;
  }
  @Override
  public void run() throws IllegalStateException {
    this.view.initializeView();
  }
}
