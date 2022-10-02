package model;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import commands.Command;

/**
 * Implements a model for an image processor. Capable of loading, saving, brightening, darkening,
 * visualizing various components, and flipping horizontally or vertically.
 */
public class ImageProcessorModelImpl implements ImageProcessorWithCommand {
  protected int[][][] image; //represents an image with its row, column, and rgb values respectively
  protected int imageWidth;
  protected int imageHeight;
  protected int maxValue; //max color value in the image

  protected Map<String, int[][][]> images;
  protected Map<String, Integer> maxValues;

  /**
   * Constructor for ImageProcessorModelImpl, initializes the maps.
   */
  public ImageProcessorModelImpl() {
    images = new HashMap<String, int[][][]>();
    maxValues = new HashMap<String, Integer>();

  }

  @Override
  public void loadImage(String filePath, String imageName) throws IllegalArgumentException {
    try {
      this.image = readPPMToArray(filePath);
      this.images.put(imageName, image);
      this.maxValues.put(imageName, this.maxValue);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Could not find a valid file of the given name");
    }
  }

  @Override
  public void saveImage(String filePath, String imageName) throws IOException {
    this.selectCurrentImage(imageName);
    File f = new File(filePath);
    FileWriter writer = new FileWriter(f);
    Appendable imageText = new StringBuilder();
    imageText.append("P3\n");
    imageText.append(imageWidth + " " + imageHeight + "\n");
    imageText.append(maxValue + "\n");
    for (int r = 0; r < imageHeight; r++) {
      for (int c = 0; c < imageWidth; c++) {
        imageText.append(image[r][c][0] + "\n");
        imageText.append(image[r][c][1] + "\n");
        imageText.append(image[r][c][2] + "\n");
      }
    }
    writer.write(imageText.toString());
    writer.close();
  }

  @Override
  public void visualizeRGB(ImageComponent component) throws IllegalArgumentException {
    switch (component) {
      case red:
        this.image = this.doToEachPixel(new SetAllToValueAtIndex(0));
        break;
      case green:
        this.image = this.doToEachPixel(new SetAllToValueAtIndex(1));
        break;
      case blue:
        this.image = this.doToEachPixel(new SetAllToValueAtIndex(2));
        break;
      default:
        throw new IllegalArgumentException("Invalid component given.");
    }

  }

  @Override
  public void visualizeValue() {
    this.image = this.doToEachPixel(new SetAllToMax());
  }

  @Override
  public void visualizeIntensity() {
    this.image = this.doToEachPixel(new SetAllToAverage());
  }

  @Override
  public void visualizeLuma() {
    this.image = this.doToEachPixel(new SetAllToLuma());
  }

  @Override
  public void flipHorizontally() {
    int[][][] flippedImage;
    int channels = this.image[0][0].length;
    flippedImage = new int[imageHeight][imageWidth][channels];
    for (int r = 0; r < imageHeight; r++) {
      for (int c = 0; c < imageWidth; c++) {
        for (int i = 0; i < channels; i++) {
          flippedImage[r][imageWidth - 1 - c][i] = this.image[r][c][i];
        }
      }
    }
    this.image = flippedImage;
  }

  @Override
  public void flipVertically() {
    int[][][] flippedImage;
    int channels = this.image[0][0].length;
    flippedImage = new int[imageHeight][imageWidth][channels];
    for (int r = 0; r < imageHeight; r++) {
      for (int c = 0; c < imageWidth; c++) {
        for (int i = 0; i < channels; i++) {
          flippedImage[imageHeight - 1 - r][c][i] = this.image[r][c][i];
        }
      }
    }
    this.image = flippedImage;
  }

  @Override
  public void brighten(int magnitude) {
    this.image = this.doToEachPixel(new AdjustBrightness(magnitude));
  }

  @Override
  public void darken(int magnitude) {
    this.image = this.doToEachPixel(new AdjustBrightness(-magnitude));
  }


  @Override
  public int[] getPixelAt(int row, int col) {
    return this.image[row][col];
  }

  @Override
  public int getImageWidth() {
    return this.imageWidth;
  }

  @Override
  public int getImageHeight() {
    return this.imageHeight;
  }

  @Override
  public void selectCurrentImage(String imageName) {
    this.image = images.get(imageName);
    this.imageHeight = image.length;
    this.imageWidth = image[0].length;
    this.maxValue = maxValues.get(imageName);
  }

  @Override
  public void storeCurrentImageAs(String imageName) {
    this.images.put(imageName, image);
    this.maxValues.put(imageName, maxValue);
  }

  /**
   * Applies the given function object to each pixel in the current image.
   *
   * @param func function to be applied to each pixel
   * @return the new transformed image
   */
  private int[][][] doToEachPixel(Function<int[], int[]> func) {
    int channels = image[0][0].length;
    int[][][] newImage = new int[imageHeight][imageWidth][channels];
    for (int r = 0; r < imageHeight; r++) {
      for (int c = 0; c < imageWidth; c++) {
        newImage[r][c] = func.apply(image[r][c]);
      }
    }
    return newImage;
  }

  /**
   * This reads a ppm file and turns it into a 3d array of integers in the format:
   * imageArray[row][col][RGB] where row = the row of the pixel's coordinate,
   * col = the col of the pixel's coordinate,
   * and the last array index (RGB) is an array of size three where [0] = red value,
   * [1] = green value, and [2] = green value for that pixel.
   *
   * @param filename name of the file to be read
   * @return the 3d array representing the image
   * @throws FileNotFoundException    if no file of the given name could be found
   * @throws IllegalArgumentException if the file is not a PPM file
   */
  private int[][][] readPPMToArray(String filename) throws FileNotFoundException,
          IllegalArgumentException {
    Scanner sc;


    sc = new Scanner(new FileInputStream(filename));

    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;
    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }

    this.imageWidth = sc.nextInt();
    this.imageHeight = sc.nextInt();
    this.maxValue = sc.nextInt();

    int[][][] imageArray = new int[imageHeight][imageWidth][3];

    for (int i = 0; i < imageHeight; i++) {
      for (int j = 0; j < imageWidth; j++) {
        imageArray[i][j][0] = sc.nextInt();
        imageArray[i][j][1] = sc.nextInt();
        imageArray[i][j][2] = sc.nextInt();
      }
    }
    return imageArray;
  }

  /**
   * applies the command to this model.
   *
   * @param command a command.
   */
  @Override
  public void apply(Command command) {
    command.execute(this);
  }


  /**
   * Sets all values in the array to the value at a certain index.
   *
   * @return the new array
   */
  private class SetAllToValueAtIndex implements Function<int[], int[]> {
    int index;

    SetAllToValueAtIndex(int index) {
      this.index = index;
    }

    @Override
    public int[] apply(int[] ints) {
      int[] answer = new int[ints.length];
      int value = ints[index];
      for (int i = 0; i < ints.length; i++) {
        answer[i] = value;
      }
      return answer;
    }
  }

  /**
   * Sets all values in the array to the maximum value in that array.
   */
  private class SetAllToMax implements Function<int[], int[]> {

    private SetAllToMax() {
    }

    @Override
    public int[] apply(int[] ints) {
      int[] answer = new int[ints.length];
      int currMax = ints[0];

      for (int i = 0; i < ints.length; i++) {
        if (ints[i] > currMax) {
          currMax = ints[i];
        }
      }
      for (int i = 0; i < ints.length; i++) {
        answer[i] = currMax;
      }
      return answer;
    }
  }

  /**
   * Sets all values in the array to the maximum value in that array.
   */
  private class SetAllToAverage implements Function<int[], int[]> {

    private SetAllToAverage() {
    }

    @Override
    public int[] apply(int[] ints) {
      int[] answer = new int[ints.length];
      int sum = 0;
      for (int i = 0; i < 3; i++) {
        sum += ints[i];
      }
      int average = (int) Math.round(sum / (ints.length * 1.0));
      for (int i = 0; i < 3; i++) {
        answer[i] = average;
      }
      return answer;
    }
  }

  /**
   * Sets each rgb value to the luma of the pixel.
   */
  private class SetAllToLuma implements Function<int[], int[]> {

    @Override
    public int[] apply(int[] ints) {
      int[] answer = new int[ints.length];
      int sum = (int) Math.round((.2126 * ints[0]) + (.7152 * ints[1]) + (.0722 * ints[2]));
      for (int i = 0; i < 3; i++) {
        answer[i] = sum;
      }
      return answer;
    }
  }

  /**
   * Adjusts brightness of the pixel by a constant.
   */
  private class AdjustBrightness implements Function<int[], int[]> {

    int constant;

    private AdjustBrightness(int constant) {
      this.constant = constant;
    }

    @Override
    public int[] apply(int[] ints) {
      int[] answer = new int[ints.length];
      int temp;
      for (int i = 0; i < 3; i++) {
        temp = ints[i] + constant;
        if (temp > 255) {
          answer[i] = 255;
        } else {
          if (temp < 0) {
            answer[i] = 0;
          } else {
            answer[i] = temp;
          }
        }
      }
      return answer;
    }
  }


}
