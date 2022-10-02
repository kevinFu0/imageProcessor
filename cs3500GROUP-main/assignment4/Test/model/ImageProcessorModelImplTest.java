package model;


import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Contains tests and examples for ImageProcessorImpl.
 */
public class ImageProcessorModelImplTest {
  ImageProcessorModel imagePModel;
  int[] sunnyDay00;
  int[] sunnyDay1419;
  int[] sunnyDay714;

  @Before
  public void initConditions() {
    this.imagePModel = new ImageProcessorModelImpl();
    this.imagePModel.loadImage("res/SunnyDay.ppm", "Sunny-Day");
    this.sunnyDay00 = new int[]{245, 245, 101};
    this.sunnyDay1419 = new int[]{119, 200, 118};
    this.sunnyDay714 = new int[]{144, 88, 25};
  }

  @Test
  public void testLoadImage() {
    this.imagePModel.loadImage(
            "res/SunnyDay.ppm", "Sunny-Day");
    assertEquals(15, imagePModel.getImageHeight());
    assertEquals(20, imagePModel.getImageWidth());
  }

  @Test
  public void testSaveImage() throws IOException {
    this.imagePModel.visualizeIntensity();
    this.imagePModel.storeCurrentImageAs("SunnyDay-Intensity");
    this.imagePModel.saveImage("res/SunnyDay-Intensity.ppm",
            "SunnyDay-Intensity");
    assertTrue(new File("res/SunnyDay-Intensity").exists());
  }

  @Test
  public void testGetImageHeight() {
    assertEquals(15, this.imagePModel.getImageHeight());
  }

  @Test
  public void testGetImageWidth() {
    assertEquals(20, this.imagePModel.getImageWidth());
  }

  @Test
  public void testGetPixelAt() {
    assertTrue(Arrays.equals(sunnyDay1419, imagePModel.getPixelAt(14, 19)));
    assertTrue(Arrays.equals(sunnyDay00, imagePModel.getPixelAt(0, 0)));
    assertTrue(Arrays.equals(sunnyDay714, imagePModel.getPixelAt(7, 14)));
  }

  @Test
  public void testVisualizeRGBRed() {
    int[] pixel1419r = {119, 119, 119};
    imagePModel.visualizeRGB(ImageComponent.red);
    imagePModel.storeCurrentImageAs("SunnyDay-red-gs");
    assertTrue(Arrays.equals(pixel1419r, imagePModel.getPixelAt(14, 19)));
    try {
      imagePModel.saveImage("res/SunnyDay-red-gs.ppm",
              "SunnyDay-red-gs");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  @Test
  public void testVisualizeRGBGreen() {
    int[] pixel1419g = {200, 200, 200};
    imagePModel.visualizeRGB(ImageComponent.green);
    assertTrue(Arrays.equals(pixel1419g, imagePModel.getPixelAt(14, 19)));

  }

  @Test
  public void testVisualizeRGBBlue() {
    int[] pixel1419b = {118, 118, 118};
    imagePModel.visualizeRGB(ImageComponent.blue);
    assertTrue(Arrays.equals(pixel1419b, imagePModel.getPixelAt(14, 19)));

  }

  @Test
  public void testVisualizeValue() {
    int[] value1419 = {200, 200, 200};
    imagePModel.visualizeValue();
    imagePModel.storeCurrentImageAs("SunnyDay-value-gs");
    assertTrue(Arrays.equals(value1419, imagePModel.getPixelAt(14, 19)));
    try {
      imagePModel.saveImage("res/SunnyDay-value-gs.ppm",
              "SunnyDay-value-gs");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  @Test
  public void testVisualizeIntensity() {
    int[] intensity1419 = {146, 146, 146};
    imagePModel.visualizeIntensity();
    imagePModel.storeCurrentImageAs("SunnyDay-intensity-gs");
    assertTrue(Arrays.equals(intensity1419, imagePModel.getPixelAt(14, 19)));
    try {
      imagePModel.saveImage("res/SunnyDay-intensity-gs.ppm",
              "SunnyDay-intensity-gs");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testVisualizeLuma() {
    int[] luma1419 = {177, 177, 177};
    imagePModel.visualizeLuma();
    imagePModel.storeCurrentImageAs("SunnyDay-luma-gs");
    assertTrue(Arrays.equals(luma1419, imagePModel.getPixelAt(14, 19)));
    try {
      imagePModel.saveImage("res/SunnyDay-luma-gs.ppm", "SunnyDay-luma-gs");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testFlipHorizontally() {
    imagePModel.flipHorizontally();
    assertTrue(Arrays.equals(sunnyDay1419, imagePModel.getPixelAt(14, 0)));
    assertTrue(Arrays.equals(sunnyDay00, imagePModel.getPixelAt(0, 19)));
    imagePModel.storeCurrentImageAs("SunnyDay-horizontal");
    try {
      imagePModel.saveImage("res/SunnyDay-horizontal.ppm", "SunnyDay-horizontal");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testFlipVertically() {
    imagePModel.flipVertically();
    assertTrue(Arrays.equals(sunnyDay1419, imagePModel.getPixelAt(0, 19)));
    assertTrue(Arrays.equals(sunnyDay00, imagePModel.getPixelAt(14, 0)));
    imagePModel.storeCurrentImageAs("SunnyDay-vertical");
    try {
      imagePModel.saveImage("res/SunnyDay-vertical.ppm", "SunnyDay-vertical");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testBrighten() {
    imagePModel.brighten(60);
    int[] brighter1419 = {179, 255, 178};
    int[] brighter00 = {255, 255, 161};
    assertTrue(Arrays.equals(brighter1419, imagePModel.getPixelAt(14, 19)));
    assertTrue(Arrays.equals(brighter00, imagePModel.getPixelAt(0, 0)));
    imagePModel.storeCurrentImageAs("SunnyDay-brighter-by-60");
    try {
      imagePModel.saveImage("res/SunnyDay-brighter-by-60.ppm",
              "SunnyDay-brighter-by-60");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testDarken() {
    imagePModel.darken(60);
    int[] darker1419 = {59, 140, 58};
    int[] darker00 = {185, 185, 41};
    int[] darker714 = {84, 28, 0};
    assertTrue(Arrays.equals(darker1419, imagePModel.getPixelAt(14, 19)));
    assertTrue(Arrays.equals(darker00, imagePModel.getPixelAt(0, 0)));
    assertTrue(Arrays.equals(darker714, imagePModel.getPixelAt(7, 14)));
  }

  @Test
  public void testSelectCurrentImage() {
    imagePModel.loadImage("res/Koala.ppm", "Koala");
    int[] koala00 = new int[]{101, 90, 58};
    assertTrue(Arrays.equals(koala00, imagePModel.getPixelAt(0, 0)));
    imagePModel.selectCurrentImage("Sunny-Day");
    assertTrue(Arrays.equals(sunnyDay00, imagePModel.getPixelAt(0, 0)));
  }

  @Test
  public void testStoreCurrentImageAs() {
    int[] darker1419 = {59, 140, 58};
    int[] darker00 = {185, 185, 41};
    int[] darker714 = {84, 28, 0};
    imagePModel.darken(60);
    imagePModel.storeCurrentImageAs("Darker-Day");
    imagePModel.brighten(20);
    imagePModel.storeCurrentImageAs("Brighter-Darker-Day");
    imagePModel.selectCurrentImage("Darker-Day");
    assertTrue(Arrays.equals(darker1419, imagePModel.getPixelAt(14, 19)));
    assertTrue(Arrays.equals(darker00, imagePModel.getPixelAt(0, 0)));
    assertTrue(Arrays.equals(darker714, imagePModel.getPixelAt(7, 14)));
  }

}