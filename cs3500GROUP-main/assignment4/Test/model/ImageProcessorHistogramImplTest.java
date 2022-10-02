package model;

import org.junit.Before;
import org.junit.Test;

import static model.HistValueType.blue;
import static model.HistValueType.green;
import static model.HistValueType.intensity;
import static model.HistValueType.red;
import static org.junit.Assert.assertEquals;


public class ImageProcessorHistogramImplTest {

  EnhancedImageProcessorModel em;
  ImageProcessorModelHistogram histM;
  @Before
  public void initCondition() {
    em = new EnhancedProcessorImpl();
    em.loadImage("res/SunnyDay.ppm", "SunnyDay");
    em.loadImage("res/Bumblebee.png", "Bumblebee");
    histM = new ImageProcessorHistogramImpl(em);
  }

  @Test
  public void testGetHistogramRed() {
    int[] result = histM.getHistogram(red, "SunnyDay");
    assertEquals(256, result.length);
    assertEquals(0, result[0]);
    assertEquals(2, result[250]);
  }

  @Test
  public void testGetHistogramGreen() {
    int[] result = histM.getHistogram(green, "SunnyDay");
    assertEquals(256, result.length);
    assertEquals(0, result[0]);
    assertEquals(4, result[200]);
  }

  @Test
  public void testGetHistogramBlue() {
    int[] result = histM.getHistogram(blue, "Bumblebee");
    assertEquals(256, result.length);
    assertEquals(274, result[0]);
    assertEquals(469, result[100]);
  }

  @Test
  public void testGetHistogramIntensity() {
    int[] result = histM.getHistogram(intensity, "Bumblebee");
    assertEquals(256, result.length);
    assertEquals(0, result[0]);
    assertEquals(577, result[100]);
  }

}