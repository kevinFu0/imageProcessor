package controller;

import commands.Blur;
import commands.Brighten;
import commands.Command;
import commands.Darken;
import commands.FlipHorizontal;
import commands.FlipVertical;
import commands.Greyscale;
import commands.LoadImage;
import commands.SaveImage;
import commands.Sepia;
import commands.Sharpen;
import commands.VisualizeIntensity;
import commands.VisualizeLuma;
import commands.VisualizeRGB;
import commands.VisualizeValue;
import model.EnhancedImageProcessorModel;
import model.HistValueType;
import model.ImageProcessorHistogramImpl;
import model.ImageProcessorModelHistogram;
import model.ImageUtil;
import view.ImageGuiView;

/**
 * Controller for interactive GUI image processor.
 */
public class ImageGuiController implements ImageProcessController, Features {
  private final String currentImage;
  private ImageProcessorModelHistogram model;
  private ImageGuiView view;
  private Command command;


  /**
   * Constructor for ImageGuiController.
   *
   * @param m    the model
   * @param view the GUI view
   */
  public ImageGuiController(EnhancedImageProcessorModel m, ImageGuiView view) {
    this.model = new ImageProcessorHistogramImpl(m);
    this.view = view;
    this.currentImage = "image";
  }

  @Override
  public void run() throws IllegalStateException {
    this.view.addFeatures(this);
  }

  @Override
  public void saveImage(String filePath) {
    command = new SaveImage(filePath, currentImage);
    command.execute(model);
    this.updateView("Image saved");
  }

  @Override
  public void loadImage(String filePath) {
    command = new LoadImage(filePath, currentImage);
    command.execute(model);
    this.updateView("Image loaded!");
  }

  @Override
  public void darken() {
    command = new Darken(20, currentImage, currentImage);
    command.execute(model);
    this.updateView("Image darkened!");
  }

  @Override
  public void brighten() {
    command = new Brighten(20, currentImage, currentImage);
    command.execute(model);
    this.updateView("Image brightened!");
  }

  @Override
  public void blur() {
    command = new Blur(currentImage, currentImage);
    command.execute(model);
    this.updateView("Image blurred!");

  }

  @Override
  public void sharpen() {
    command = new Sharpen(currentImage, currentImage);
    command.execute(model);
    this.updateView("Image sharpened!");
  }

  @Override
  public void flipHorizontal() {
    command = new FlipHorizontal(currentImage, currentImage);
    command.execute(model);
    this.updateView("Image flipped horizontally!");
  }

  @Override
  public void flipVertical() {
    command = new FlipVertical(currentImage, currentImage);
    command.execute(model);
    this.updateView("Image flipped vertically!");
  }

  @Override
  public void greyscale() {
    command = new Greyscale(currentImage, currentImage);
    command.execute(model);
    this.updateView("Greyscale transformation applied!");
  }

  @Override
  public void sepia() {
    command = new Sepia(currentImage, currentImage);
    command.execute(model);
    this.updateView("Sepia transformation applied!");
  }

  @Override
  public void visualizeIntensity() {
    command = new VisualizeIntensity(currentImage, currentImage);
    command.execute(model);
    this.updateView("Intensity visualization applied!");
  }

  @Override
  public void visualizeLuma() {
    command = new VisualizeLuma(currentImage, currentImage);
    command.execute(model);
    this.updateView("Luma visualization applied!");
  }

  @Override
  public void visualizeRed() {
    command = new VisualizeRGB("red", currentImage, currentImage);
    command.execute(model);
    this.updateView("Red component visualization applied!");
  }

  @Override
  public void visualizeGreen() {
    command = new VisualizeRGB("green", currentImage, currentImage);
    command.execute(model);
    this.updateView("Green component visualization applied!");
  }

  @Override
  public void visualizeBlue() {
    command = new VisualizeRGB("blue", currentImage, currentImage);
    command.execute(model);
    this.updateView("Blue component visualization applied!");
  }

  @Override
  public void visualizeValue() {
    command = new VisualizeValue(currentImage, currentImage);
    command.execute(model);
    this.updateView("Value visualization applied!");
  }

  //tells the view to update the image and histogram and renders the message
  private void updateView(String message) {
    this.view.refreshImage(ImageUtil.createBufferedImage(this.model));
    int[][] histograms = new int[4][256];
    histograms[0] = this.model.getHistogram(HistValueType.red, currentImage);
    histograms[1] = this.model.getHistogram(HistValueType.green, currentImage);
    histograms[2] = this.model.getHistogram(HistValueType.blue, currentImage);
    histograms[3] = this.model.getHistogram(HistValueType.intensity, currentImage);
    this.view.refreshHistogram(histograms);
    this.view.renderMessage(message);
  }


}
