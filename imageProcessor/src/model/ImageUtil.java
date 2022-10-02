package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static void readPPM(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return;
    }
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
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        System.out.println("Color of pixel (" + j + "," + i + "): " + r + "," + g + "," + b);
      }
    }
  }

  /**
   * Reads a ppm file to a 3d array.
   *
   * @param filename the file path
   * @return array representing the image
   * @throws FileNotFoundException if the file cannot be found
   */
  public static int[][][] readPPM2(String filename) throws FileNotFoundException {
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

    int imageWidth = sc.nextInt();
    int imageHeight = sc.nextInt();
    int maxValue = sc.nextInt();

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
   * Creates a buffered image according to the current image in the given model.
   * @param model the model being used
   * @return a buffered image matching the image data in the model
   */
  public static BufferedImage createBufferedImage(ImageProcessorModel model) {
    int imageWidth = model.getImageWidth();
    int imageHeight = model.getImageHeight();
    int numChannels = 3;
    int imageType = BufferedImage.TYPE_INT_ARGB;
    BufferedImage buffImage =
            new BufferedImage(imageWidth, imageHeight, imageType);
    for (int r = 0; r < imageHeight; r++) {
      for (int c = 0; c < imageWidth; c++) {
        Color colorInfo = new Color(model.getPixelAt(r, c)[0], model.getPixelAt(r, c)[1],
                model.getPixelAt(r, c)[2]);
        if (numChannels == 4) {
          if (model.getPixelAt(0, 0).length == 3) {
            colorInfo = new Color(model.getPixelAt(r, c)[0], model.getPixelAt(r, c)[1],
                    model.getPixelAt(r, c)[2], 255);
          } else {
            colorInfo = new Color(model.getPixelAt(r, c)[0], model.getPixelAt(r, c)[1],
                    model.getPixelAt(r, c)[2], model.getPixelAt(r, c)[3]);
          }
        }
        buffImage.setRGB(c, r, colorInfo.getRGB());
      }
    }
    return buffImage;
  }

  /**
   * Demo main for loading files.
   *
   * @param args string arguments
   */
  public static void main(String[] args) {
    String filename;

    if (args.length > 0) {
      filename = args[0];
    } else {
      filename = "SunnyDay.ppm";
    }

    ImageUtil.readPPM(filename);
  }
}

