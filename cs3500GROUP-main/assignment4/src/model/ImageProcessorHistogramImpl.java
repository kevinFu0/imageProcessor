package model;

import java.io.IOException;

import commands.Command;

import static model.ImageComponent.blue;
import static model.ImageComponent.green;
import static model.ImageComponent.red;

public class ImageProcessorHistogramImpl implements ImageProcessorModelHistogram {
  EnhancedImageProcessorModel model;

  public ImageProcessorHistogramImpl(EnhancedImageProcessorModel model) {
    this.model = model;
  }

  @Override
  public void applyFilter(double[][] kernel) {
    this.model.applyFilter(kernel);
  }

  @Override
  public void applyTransformation(double[][] transformation) {
    this.model.applyTransformation(transformation);
  }

  @Override
  public void loadImage(String filePath, String imageName) throws IllegalArgumentException {
    this.model.loadImage(filePath, imageName);
  }

  @Override
  public void saveImage(String filePath, String imageName) throws IOException {
    this.model.saveImage(filePath, imageName);
  }

  @Override
  public void visualizeRGB(ImageComponent component) throws IllegalArgumentException {
    this.model.visualizeRGB(component);
  }

  @Override
  public void visualizeValue() {
    this.model.visualizeValue();
  }

  @Override
  public void visualizeIntensity() {
    this.model.visualizeIntensity();
  }

  @Override
  public void visualizeLuma() {
    this.model.visualizeLuma();
  }

  @Override
  public void flipHorizontally() {
    this.model.flipHorizontally();
  }

  @Override
  public void flipVertically() {
    this.model.flipVertically();
  }

  @Override
  public void brighten(int magnitude) {
    this.model.brighten(magnitude);
  }

  @Override
  public void darken(int magnitude) {
    this.model.darken(magnitude);
  }

  @Override
  public int[] getPixelAt(int row, int col) {
    return this.model.getPixelAt(row, col);
  }

  @Override
  public int getImageWidth() {
    return this.model.getImageWidth();
  }

  @Override
  public int getImageHeight() {
    return this.model.getImageHeight();
  }

  @Override
  public void selectCurrentImage(String imageName) {
    this.model.selectCurrentImage(imageName);
  }

  @Override
  public void storeCurrentImageAs(String imageName) {
    this.model.storeCurrentImageAs(imageName);
  }

  @Override
  public int[] getHistogram(HistValueType type, String imageName) {
    this.selectCurrentImage(imageName);
    int componentIndex = 0;
    switch(type) {
      case red:
        this.model.visualizeRGB(red);
        break;
      case green:
        this.model.visualizeRGB(green);
        break;
      case blue:
        this.model.visualizeRGB(blue);
        break;
      case intensity:
        this.model.visualizeIntensity();
        break;
      default:
        break;
    }
    return this.histogramHelper(componentIndex);
  }

  //returns an array containing the frequency of each value(0-255) in the first component of a
  // red, green, blue, or intensity visualization image
  private int[] histogramHelper(int componentIndex) {
    int[] histogram = new int[256];
      for (int r = 0; r < model.getImageHeight(); r++) {
        for (int c = 0; c < model.getImageHeight(); c++) {
          int value = model.getPixelAt(r, c)[componentIndex];
          histogram[value] += 1;
        }
      }
    return histogram;
  }

  @Override
  public void apply(Command command) throws IOException {
    command.execute(this);
  }
}
