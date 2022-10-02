package controller;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;

import model.EnhancedImageProcessorModel;
import model.EnhancedProcessorImpl;
import view.ImageGuiView;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Tests and examples for ImageGuiController.
 */
public class ImageGuiControllerTest {

  EnhancedImageProcessorModel model;
  ImageGuiView view;
  Appendable log;

  Features controller;


  @Before
  public void initConditions() {
    this.model = new EnhancedProcessorImpl();
    this.log = new StringBuilder();
    this.view = new MockGUIView(this.log);
    this.controller = new ImageGuiController(this.model, this.view);
    ImageProcessController c = (ImageProcessController) controller;
    c.run();
  }

  @Test
  public void saveImage() {
    this.controller.loadImage("res/River.jpg");
    this.controller.saveImage("res/River2.jpg");
    assertEquals("added features\nrefreshed image\nrefreshed histograms\nrendered message\n" +
            "refreshed image\nrefreshed histograms\nrendered message\n", log.toString());
    assertTrue(new File("res/River2.jpg").exists());
  }

  @Test
  public void loadImage() {
    this.controller.loadImage("res/River.jpg");
    assertEquals("added features\nrefreshed image\nrefreshed histograms\nrendered message\n",
            log.toString());
    int[] river00 = new int[]{87, 145, 218};
    this.model.selectCurrentImage("image");
    assertTrue(Arrays.equals(river00, this.model.getPixelAt(0, 0)));
  }

  @Test
  public void darken() {
    this.controller.loadImage("res/SunnyDay.ppm");
    this.controller.darken();
    int[] darker00 = {225, 225, 81};
    this.model.selectCurrentImage("image");
    assertEquals("added features\nrefreshed image\nrefreshed histograms\nrendered message\n" +
            "refreshed image\nrefreshed histograms\nrendered message\n", log.toString());
    assertTrue(Arrays.equals(darker00, this.model.getPixelAt(0, 0)));
  }

  @Test
  public void brighten() {
    this.controller.loadImage("res/SunnyDay.ppm");
    this.controller.brighten();
    int[] darker00 = {255, 255, 121};
    this.model.selectCurrentImage("image");
    assertEquals("added features\nrefreshed image\nrefreshed histograms\nrendered message\n" +
            "refreshed image\nrefreshed histograms\nrendered message\n", log.toString());
    assertTrue(Arrays.equals(darker00, this.model.getPixelAt(0, 0)));
  }

  @Test
  public void blur() {
    this.controller.loadImage("res/Bumblebee.png");
    this.controller.blur();
    int[] bumble00 = new int[]{33, 58, 23, 255};
    this.model.selectCurrentImage("image");
    assertEquals("added features\nrefreshed image\nrefreshed histograms\nrendered message\n" +
            "refreshed image\nrefreshed histograms\nrendered message\n", log.toString());
    assertTrue(Arrays.equals(bumble00, model.getPixelAt(0, 0)));

  }

  @Test
  public void sharpen() {
    this.controller.loadImage("res/Bumblebee.png");
    this.controller.sharpen();
    int[] bumble00 = new int[]{62, 112, 44, 255};
    this.model.selectCurrentImage("image");
    assertEquals("added features\nrefreshed image\nrefreshed histograms\nrendered message\n" +
            "refreshed image\nrefreshed histograms\nrendered message\n", log.toString());
    assertTrue(Arrays.equals(bumble00, model.getPixelAt(0, 0)));
  }

  @Test
  public void flipHorizontal() {
    this.controller.loadImage("res/SunnyDay.ppm");
    this.controller.flipHorizontal();
    int[] sunnyDay00 = new int[]{245, 245, 101};
    int[] sunnyDay1419 = new int[]{119, 200, 118};
    this.model.selectCurrentImage("image");
    assertEquals("added features\nrefreshed image\nrefreshed histograms\nrendered message\n" +
            "refreshed image\nrefreshed histograms\nrendered message\n", log.toString());
    assertTrue(Arrays.equals(sunnyDay1419, model.getPixelAt(14, 0)));
    assertTrue(Arrays.equals(sunnyDay00, model.getPixelAt(0, 19)));
  }

  @Test
  public void flipVertical() {
    this.controller.loadImage("res/SunnyDay.ppm");
    this.controller.flipVertical();
    int[] sunnyDay00 = new int[]{245, 245, 101};
    int[] sunnyDay1419 = new int[]{119, 200, 118};
    this.model.selectCurrentImage("image");
    assertEquals("added features\nrefreshed image\nrefreshed histograms\nrendered message\n" +
            "refreshed image\nrefreshed histograms\nrendered message\n", log.toString());
    assertTrue(Arrays.equals(sunnyDay1419, model.getPixelAt(0, 19)));
    assertTrue(Arrays.equals(sunnyDay00, model.getPixelAt(14, 0)));
  }

  @Test
  public void greyscale() {
    int[] bumble00 = new int[]{85, 85, 85, 255};
    int[] bumble149199 = new int[]{43, 43, 43, 255};
    this.controller.loadImage("res/Bumblebee.png");
    this.controller.greyscale();
    this.model.selectCurrentImage("image");
    assertEquals("added features\nrefreshed image\nrefreshed histograms\nrendered message\n" +
            "refreshed image\nrefreshed histograms\nrendered message\n", log.toString());
    assertTrue(Arrays.equals(bumble00, model.getPixelAt(0, 0)));
    assertTrue(Arrays.equals(bumble149199, model.getPixelAt(149, 199)));
  }

  @Test
  public void sepia() {
    int[] bumble00 = new int[]{106, 94, 73, 255};
    int[] bumble149199 = new int[]{58, 51, 39, 255};
    this.controller.loadImage("res/Bumblebee.png");
    this.controller.sepia();
    this.model.selectCurrentImage("image");
    assertEquals("added features\nrefreshed image\nrefreshed histograms\nrendered message\n" +
            "refreshed image\nrefreshed histograms\nrendered message\n", log.toString());
    assertTrue(Arrays.equals(bumble00, model.getPixelAt(0, 0)));
    assertTrue(Arrays.equals(bumble149199, model.getPixelAt(149, 199)));
  }

  @Test
  public void visualizeIntensity() {
    int[] intensity1419 = {146, 146, 146};
    this.controller.loadImage("res/SunnyDay.ppm");
    this.controller.visualizeIntensity();
    this.model.selectCurrentImage("image");
    assertEquals("added features\nrefreshed image\nrefreshed histograms\nrendered message\n" +
            "refreshed image\nrefreshed histograms\nrendered message\n", log.toString());
    assertTrue(Arrays.equals(intensity1419, model.getPixelAt(14, 19)));

  }

  @Test
  public void visualizeLuma() {
    int[] luma1419 = {177, 177, 177};
    this.controller.loadImage("res/SunnyDay.ppm");
    this.controller.visualizeLuma();
    this.model.selectCurrentImage("image");
    assertEquals("added features\nrefreshed image\nrefreshed histograms\nrendered message\n" +
            "refreshed image\nrefreshed histograms\nrendered message\n", log.toString());
    assertTrue(Arrays.equals(luma1419, model.getPixelAt(14, 19)));
  }

  @Test
  public void visualizeRed() {
    int[] pixel1419r = {119, 119, 119};
    this.controller.loadImage("res/SunnyDay.ppm");
    this.controller.visualizeRed();
    this.model.selectCurrentImage("image");
    assertEquals("added features\nrefreshed image\nrefreshed histograms\nrendered message\n" +
            "refreshed image\nrefreshed histograms\nrendered message\n", log.toString());
    assertTrue(Arrays.equals(pixel1419r, model.getPixelAt(14, 19)));
  }

  @Test
  public void visualizeGreen() {
    int[] pixel1419g = {200, 200, 200};
    this.controller.loadImage("res/SunnyDay.ppm");
    this.controller.visualizeGreen();
    this.model.selectCurrentImage("image");
    assertEquals("added features\nrefreshed image\nrefreshed histograms\nrendered message\n" +
            "refreshed image\nrefreshed histograms\nrendered message\n", log.toString());
    assertTrue(Arrays.equals(pixel1419g, model.getPixelAt(14, 19)));
  }

  @Test
  public void visualizeBlue() {
    this.controller.loadImage("res/SunnyDay.ppm");
    this.controller.visualizeBlue();
    int[] pixel1419b = {118, 118, 118};
    this.model.selectCurrentImage("image");
    assertEquals("added features\nrefreshed image\nrefreshed histograms\nrendered message\n" +
            "refreshed image\nrefreshed histograms\nrendered message\n", log.toString());
    assertTrue(Arrays.equals(pixel1419b, model.getPixelAt(14, 19)));
  }

  @Test
  public void visualizeValue() {
    this.controller.loadImage("res/SunnyDay.ppm");
    this.controller.visualizeValue();
    int[] value1419 = {200, 200, 200};
    this.model.selectCurrentImage("image");
    assertEquals("added features\nrefreshed image\nrefreshed histograms\nrendered message\n" +
            "refreshed image\nrefreshed histograms\nrendered message\n", log.toString());
    assertTrue(Arrays.equals(value1419, model.getPixelAt(14, 19)));
  }

  @Test
  public void multipleOperationsAndImages() {
    int[] bumble00 = new int[]{106, 94, 73, 255};
    int[] bumble149199 = new int[]{58, 51, 39, 255};
    this.controller.loadImage("res/Bumblebee.png");
    this.controller.sepia();
    this.model.selectCurrentImage("image");
    assertTrue(Arrays.equals(bumble00, model.getPixelAt(0, 0)));
    assertTrue(Arrays.equals(bumble149199, model.getPixelAt(149, 199)));
    this.controller.blur();
    this.model.selectCurrentImage("image");
    bumble00 = new int[]{62, 55, 42, 255};
    bumble149199 = new int[]{62, 56, 43, 255};
    assertTrue(Arrays.equals(bumble00, model.getPixelAt(0, 0)));
    assertTrue(Arrays.equals(bumble149199, model.getPixelAt(149, 199)));
    this.controller.loadImage("res/River.jpg");
    this.controller.flipVertical();
    this.model.selectCurrentImage("image");
    this.controller.saveImage("res/UpsideDownRiver.png");
    assertTrue(new File("res/UpsideDownRiver.png").exists());

  }
}