package model;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import commands.Blur;
import commands.Command;
import commands.Greyscale;
import commands.Sepia;
import commands.Sharpen;

import static org.junit.Assert.assertTrue;

/**
 * Tests and examples for EnhancedProcessorImpl.
 */
public class EnhancedProcessorImplTest {
  EnhancedImageProcessorModel model = new EnhancedProcessorImpl();
  int[] bumble00;
  int[] bumble149199;
  double[][] blurFilter = { {1 / 16.0, 1 / 9.0, 1 / 16.0},
      {1 / 8.0, 1 / 4.0, 1 / 8.0}, {1 / 16.0, 1 / 8.0, 1 / 16.0} };

  @Before
  public void initConditions() {
    model.loadImage("res/SunnyDay.ppm", "SunnyDay");
    model.loadImage("res/Bumblebee.png", "Bumblebee");
    bumble00 = new int[] {56, 101, 39, 255};
  }

  @Test
  public void testLoadPng() {
    model = new EnhancedProcessorImpl();
    model.loadImage("res/Bumblebee.png", "Bumblebee");
    int[] bumble00 = new int[] {56, 101, 39, 255};
    assertTrue(Arrays.equals(bumble00, model.getPixelAt(0, 0)));
  }

  @Test
  public void testLoadBmp() {
    model = new EnhancedProcessorImpl();
    model.loadImage("res/Bumblebee.bmp", "Bumblebee");
    int[] bumble00 = new int[] {56, 101, 39};
    assertTrue(Arrays.equals(bumble00, model.getPixelAt(0, 0)));
  }

  @Test
  public void testLoadJpg() {
    model = new EnhancedProcessorImpl();
    model.loadImage("res/Bumblebee.jpg", "Bumblebee");
    int[] bumble00 = new int[]{53, 105, 39};
    assertTrue(Arrays.equals(bumble00, model.getPixelAt(0, 0)));
  }

  @Test
  public void testSaveAsJPG() throws IOException {
    model.saveImage("res/Bumblebee.jpg", "Bumblebee");
    assertTrue(new File("res/Bumblebee.jpg").exists());
  }

  @Test
  public void testSaveAsPNG() throws IOException {
    model.saveImage("res/Bumblebee.png", "Bumblebee");
    assertTrue(new File("res/Bumblebee.png").exists());
  }

  @Test
  public void testSaveAsPPM() throws IOException {
    model.saveImage("res/Bumblebee.ppm", "Bumblebee");
    assertTrue(new File("res/Bumblebee.ppm").exists());
  }

  @Test
  public void testSaveAsBMP() throws IOException {
    model.saveImage("res/Bumblebee.bmp", "Bumblebee");
    assertTrue(new File("res/Bumblebee.bmp").exists());
  }

  @Test
  public void testBlur() {
    Command blur = new Blur("Bumblebee", "Bumblebee-blurred");
    blur.execute(model);
    bumble00 = new int[] {33, 58, 23, 255};
    bumble149199 = new int[] {68, 46, 6, 255};
    assertTrue(Arrays.equals(bumble00, model.getPixelAt(0, 0)));
    assertTrue(Arrays.equals(bumble149199, model.getPixelAt(149, 199)));
  }

  @Test
  public void testSharpen() {
    Command sharpen = new Sharpen("Bumblebee", "Bumblebee-sharpened");
    sharpen.execute(model);
    bumble00 = new int[] {62, 112, 44, 255};
    bumble149199 = new int[] {30, 16, 8, 255};
    assertTrue(Arrays.equals(bumble00, model.getPixelAt(0, 0)));
    assertTrue(Arrays.equals(bumble149199, model.getPixelAt(149, 199)));
  }

  @Test
  public void testGreyscale() {
    Command greyscale = new Greyscale("Bumblebee", "Bumblebee-gs");
    greyscale.execute(model);
    bumble00 = new int[] {85, 85, 85, 255};
    bumble149199 = new int[] {43, 43, 43, 255};
    assertTrue(Arrays.equals(bumble00, model.getPixelAt(0, 0)));
    assertTrue(Arrays.equals(bumble149199, model.getPixelAt(149, 199)));
  }


  @Test
  public void testSepia() {
    Command sepia = new Sepia("Bumblebee", "Bumblebee-sepia");
    sepia.execute(model);
    bumble00 = new int[] {106, 94, 73, 255};
    bumble149199 = new int[] {58, 51, 39, 255};
    assertTrue(Arrays.equals(bumble00, model.getPixelAt(0, 0)));
    assertTrue(Arrays.equals(bumble149199, model.getPixelAt(149, 199)));
  }

}