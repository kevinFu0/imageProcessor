package model;

import org.junit.Before;
import org.junit.Test;

import commands.Blur;

import static model.HistValueType.blue;
import static model.HistValueType.green;
import static model.HistValueType.intensity;
import static model.HistValueType.red;
import static org.junit.Assert.assertEquals;

/**
 * Tests and examples for ImageProcessorHistogramImpl.
 */
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

  @Test
  public void testGetHistogramRedBeforeAfterBlur() {
    int[] result = histM.getHistogram(red, "SunnyDay");
    assertEquals(256, result.length);
    assertEquals(0, result[0]);
    assertEquals(2, result[110]);
    new Blur("SunnyDay", "SunnyDay").execute(histM);
    int[] result2 = histM.getHistogram(red, "SunnyDay");
    assertEquals(256, result.length);
    assertEquals(0, result2[0]);
    assertEquals(2, result2[110]);
    new Blur("SunnyDay", "SunnyDay").execute(histM);
    int[] result3 = histM.getHistogram(red, "SunnyDay");
    assertEquals(256, result.length);
    assertEquals(0, result3[0]);
    assertEquals(4, result3[110]);
  }

}