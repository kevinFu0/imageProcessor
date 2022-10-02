package controller;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;

import commands.Command;
import model.EnhancedImageProcessorModel;
import model.EnhancedProcessorImpl;
import model.ImageComponent;
import model.ImageUtil;
import view.ImageProcessorView;
import view.TextView;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


/**
 * tests for the controller.
 */
public class TextControllerTest {

  /**
   * mock class for imageProcessorModel to test the model is given the correct input.
   */
  public class MockImageProcessor implements EnhancedImageProcessorModel {

    private final StringBuilder log;

    public MockImageProcessor(StringBuilder log) {
      this.log = log;
    }

    @Override
    public void loadImage(String filePath, String imageName) throws IllegalArgumentException {
      log.append("loading image file: " + filePath + " name: " + imageName + "\n");
    }

    @Override
    public void saveImage(String filePath, String imageName) throws IOException {
      log.append("saving image file: " + filePath + " name: " + imageName + "\n");
    }

    @Override
    public void visualizeRGB(ImageComponent component) throws IllegalArgumentException {
      log.append("visualingRGB with component: " + component.toString() + "\n");
    }

    @Override
    public void visualizeValue() {
      log.append("in visualizeValue\n");
    }

    @Override
    public void visualizeIntensity() {
      log.append("in visualizeIntensity\n");
    }

    @Override
    public void visualizeLuma() {
      log.append("in visualizeLuma\n");
    }

    @Override
    public void flipHorizontally() {
      log.append("in flip horizontal\n");
    }

    @Override
    public void flipVertically() {
      log.append("in flip vertical\n");
    }

    @Override
    public void brighten(int magnitude) {
      log.append("in brighten with magnitude: " + magnitude + "\n");
    }

    @Override
    public void darken(int magnitude) {
      log.append("in darken with magnitude: " + magnitude + "\n");
    }

    @Override
    public int[] getPixelAt(int row, int col) {
      log.append("in getPixelAt with row: " + row + "col: " + col + "\n");
      return new int[1];
    }

    @Override
    public int getImageWidth() {
      log.append("in getImageWidth\n");
      return 0;
    }

    @Override
    public int getImageHeight() {
      log.append("in getImageHeight\n");
      return 0;
    }

    @Override
    public void selectCurrentImage(String imageName) {
      log.append("in selectCurrentImage with imageName: " + imageName + "\n");
    }

    @Override
    public void storeCurrentImageAs(String imageName) {
      log.append("in storeCurrentImage with imageName: " + imageName + "\n");
    }

    @Override
    public void applyFilter(double[][] kernel) {
      log.append("in applyFilter with kernel: " + kernel.toString() + "\n");
    }

    @Override
    public void applyTransformation(double[][] transformation) {
      log.append("in applyTransformation with transformation: " + transformation.toString() + "\n");
    }

    @Override
    public void apply(Command command) throws IOException {
      log.append("in apply with command: " + command.toString() + "\n");
    }
  }


  private EnhancedImageProcessorModel model = new EnhancedProcessorImpl();
  private Appendable out = new StringBuilder();
  private ImageProcessorView view = new TextView(out);
  private ImageProcessController controller;

  /**
   * testing null model.
   */
  @Test(expected = IllegalArgumentException.class)
  public void NullModelConstructor() {
    Readable in = new StringReader("doesn't matter");
    EnhancedImageProcessorModel m1 = new EnhancedProcessorImpl();
    ImageProcessorView v1 = new TextView(out);
    ImageProcessController controller = new TextController(null, v1, in);
  }

  /**
   * testing null view.
   */
  @Test(expected = IllegalArgumentException.class)
  public void NullViewConstructor() {
    Readable in = new StringReader("doesn't matter");
    EnhancedImageProcessorModel m1 = new EnhancedProcessorImpl();
    ImageProcessorView v1 = new TextView(out);
    ImageProcessController controller = new TextController(m1, null, in);
  }

  /**
   * testing null Readable.
   */
  @Test(expected = IllegalArgumentException.class)
  public void NullReadableConstructor() {
    Readable in = new StringReader("doesn't matter");
    EnhancedImageProcessorModel m1 = new EnhancedProcessorImpl();
    ImageProcessorView v1 = new TextView(out);
    ImageProcessController controller = new TextController(m1, v1, null);
  }

  // testing load brighten and save
  @Test
  public void testValidOutput1() throws FileNotFoundException {
    Readable in = new StringReader("load res/SunnyDay.ppm newSunny " +
            "brighten 60 newSunny newSunny2 save res/newSunny2.ppm newSunny2 q");
    controller = new TextController(model, view, in);
    controller.run();

    int[][][] image1 = ImageUtil.readPPM2("res/newSunny2.ppm");
    int[][][] image2 = ImageUtil.readPPM2("res/SunnyDay-brighter-by-60.ppm");

    assertArrayEquals(image1, image2);

  }

  @Test
  public void testInvalidInput() {
    Readable in = new StringReader("bad input q");
    controller = new TextController(model, view, in);
    controller.run();

    assertEquals("Welcome to Image Processor!\n" +
            "Type commands or enter q or quit to exit the program.\n" +
            "Invalid command!\n" +
            "Invalid command!\n" +
            "Program quit\n", out.toString());
  }

  // command can't execute because the image names do not exist in the model
  @Test(expected = NullPointerException.class)
  public void testInvalidInput2() {
    Readable in2 = new StringReader("vertical-flip kola kola2 q");
    controller = new TextController(model, view, in2);
    controller.run();
    assertEquals("", out.toString());
  }

  @Test
  public void testMockLoad() {
    Readable in = new StringReader("load src/SunnyDay.ppm sunny q");
    StringBuilder log = new StringBuilder();
    EnhancedImageProcessorModel model = new MockImageProcessor(log);
    controller = new TextController(model, view, in);
    controller.run();
    assertEquals("loading image file: src/SunnyDay.ppm name: sunny\n", log.toString());
  }

  @Test
  public void testMockSave() {
    Readable in = new StringReader("save src/SunnyDay.ppm sunny q");
    StringBuilder log = new StringBuilder();
    EnhancedImageProcessorModel model = new MockImageProcessor(log);
    controller = new TextController(model, view, in);
    controller.run();
    assertEquals("saving image file: src/SunnyDay.ppm name: sunny\n", log.toString());
  }

  @Test
  public void testMockVisualizeRBG() {
    Readable in = new StringReader("visualizeRGB green sunny newSunny q");
    StringBuilder log = new StringBuilder();
    EnhancedImageProcessorModel model = new MockImageProcessor(log);
    controller = new TextController(model, view, in);
    controller.run();
    String[] output = log.toString().split("\n");
    assertEquals("visualingRGB with component: green", output[1]);
  }

  @Test
  public void testMockBrighten() {
    Readable in = new StringReader("brighten 10 SunnyDay newsunny q");
    StringBuilder log = new StringBuilder();
    EnhancedImageProcessorModel model = new MockImageProcessor(log);
    controller = new TextController(model, view, in);
    controller.run();
    String[] output = log.toString().split("\n");
    assertEquals("in brighten with magnitude: 10", output[1]);
  }

  @Test
  public void testMockDarken() {
    Readable in = new StringReader("darken 10 SunnyDay newsunny q");
    StringBuilder log = new StringBuilder();
    EnhancedImageProcessorModel model = new MockImageProcessor(log);
    controller = new TextController(model, view, in);
    controller.run();
    String[] output = log.toString().split("\n");
    assertEquals("in darken with magnitude: 10", output[1]);
  }


}