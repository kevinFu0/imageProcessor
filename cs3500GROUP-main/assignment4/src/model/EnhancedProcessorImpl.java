package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Implements an enhanced image processor model. Loads, saves, and modifies ppm, png, bmp, and jpg
 * images. Capable of brightening, darkening, filters, transformation, various component greyscales,
 * and flipping horizontally or vertically.
 */
public class EnhancedProcessorImpl extends ImageProcessorModelImpl
    implements EnhancedImageProcessorModel {

  /**
   * Constructor for EnhancedProcessorImpl.
   */
  public EnhancedProcessorImpl() {
    super();
  }

  @Override
  public void applyFilter(double[][] kernel) {
    int channels = this.image[0][0].length;
    int[][][] newImage = new int[imageHeight][imageWidth][channels];
    for (int r = 0; r < imageHeight; r++) {
      for (int c = 0; c < imageWidth; c++) {
        for (int i = 0; i < channels; i++) {
          newImage[r][c][i] = image[r][c][i];
        }
        newImage[r][c][0] = this.applyKernelToPixelChannel(kernel, 0, r, c);
        newImage[r][c][1] = this.applyKernelToPixelChannel(kernel, 1, r, c);
        newImage[r][c][2] = this.applyKernelToPixelChannel(kernel, 2, r, c);
      }
    }
    this.image = newImage;
  }

  //Applies the kernel to the given channel in the pixel at the given position
  private int applyKernelToPixelChannel(double[][] kernel, int channelIndex, int row, int col) {
    int newValue = 0;
    int kernelMidpoint = kernel.length / 2;

    for (int r = 0; r < kernel.length; r++) {
      for (int c = 0; c < kernel.length; c++) {
        try {
          newValue += (int) Math.round(
                  image[r + (row - kernelMidpoint)][c + (col - kernelMidpoint)][channelIndex]
                  * kernel[r][c]);
        } catch (IndexOutOfBoundsException e) {
          newValue += 0;
        }
      }
    }
    if (newValue > 255) {
      return 255;
    }
    if (newValue < 0) {
      return 0;
    }
    return newValue;
  }

  @Override
  public void applyTransformation(double[][] transformation) {
    int channels = this.image[0][0].length;
    int[][][] newImage = new int[imageHeight][imageWidth][channels];
    for (int r = 0; r < imageHeight; r++) {
      for (int c = 0; c < imageWidth; c++) {
        newImage[r][c] = this.applyTransformationToPixel(transformation, getPixelAt(r, c));
      }
    }
    this.image = newImage;
  }

  //Applies the transformation to the given pixel and returns the result
  private int[] applyTransformationToPixel(double[][] transformation, int[] pixel) {
    int channels = pixel.length;
    int transformHeight = transformation.length;
    int[] newPixel = new int[channels];
    for (int r = 0; r < transformHeight; r++) {
      int value = 0;
      for (int c = 0; c < 3; c++) {
        value += transformation[r][c] * pixel[c];
      }
      if (value > 255) {
        value = 255;
      }
      if (value < 0) {
        value = 0;
      }
      newPixel[r] = value;
    }
    if (channels == 4) {
      newPixel[3] = pixel[3];
    }
    return newPixel;
  }

  /**
   * Loads a file according to the format specified.
   *
   * @param filePath  the filepath of the image
   * @param imageName the name of the image being loaded
   * @throws IllegalArgumentException if the given file could not be found
   */
  @Override
  public void loadImage(String filePath, String imageName) throws IllegalArgumentException {
    if (filePath.toLowerCase().contains(".ppm")) {
      super.loadImage(filePath, imageName);
    } else {
      try {
        BufferedImage imageFile = ImageIO.read(new File(filePath));
        imageHeight = imageFile.getHeight();
        imageWidth = imageFile.getWidth();
        maxValue = 255;
        int numChannels = 3;
        if (filePath.toLowerCase().contains(".png")) {
          numChannels = 4;
        }
        int[][][] newImage = new int[imageHeight][imageWidth][numChannels];
        for (int r = 0; r < imageHeight; r++) {
          for (int c = 0; c < imageWidth; c++) {
            Color currentPixel = new Color(imageFile.getRGB(c, r));
            newImage[r][c][0] = currentPixel.getRed();
            newImage[r][c][1] = currentPixel.getGreen();
            newImage[r][c][2] = currentPixel.getBlue();
            if (numChannels == 4) {
              newImage[r][c][3] = currentPixel.getAlpha();
            }
          }
        }
        this.image = newImage;
        this.images.put(imageName, image);
        this.maxValues.put(imageName, this.maxValue);
      } catch (IOException e) {
        throw new IllegalArgumentException("File entered could not be found");
      }
    }
  }

  /**
   * Saves a file according to the specified image format.
   *
   * @param filePath  the filepath where the image is saved
   * @param imageName name of the new image file
   * @throws IllegalArgumentException if the file could not be found
   */
  @Override
  public void saveImage(String filePath, String imageName) throws IllegalArgumentException {
    String fileFormat = filePath.substring(filePath.indexOf(".") + 1);
    if (fileFormat.equalsIgnoreCase("ppm")) {
      try {
        super.saveImage(filePath, imageName);
      } catch (IOException e) {
        throw new IllegalArgumentException("The file could not be saved");
      }
    } else {
      int numChannels = 3;
      int imageType = BufferedImage.TYPE_INT_RGB;
      if (fileFormat.equalsIgnoreCase("png")) {
        numChannels = 4;
        imageType = BufferedImage.TYPE_INT_ARGB;
      }
      BufferedImage buffImage =
              new BufferedImage(imageWidth, imageHeight, imageType);
      for (int r = 0; r < imageHeight; r++) {
        for (int c = 0; c < imageWidth; c++) {
          Color colorInfo = new Color(image[r][c][0], image[r][c][1], image[r][c][2]);
          if (numChannels == 4) {
            if (image[0][0].length == 3) {
              colorInfo = new Color(image[r][c][0], image[r][c][1], image[r][c][2], 255);
            }
            else {
              colorInfo = new Color(image[r][c][0], image[r][c][1], image[r][c][2], image[r][c][3]);
            }
          }
          buffImage.setRGB(c, r, colorInfo.getRGB());
        }
      }
      File imageFile = new File(filePath);
      try {
        ImageIO.write(buffImage, fileFormat, imageFile);
      } catch (IOException e) {
        throw new IllegalArgumentException("The file could not be saved");
      }
    }
  }
}
