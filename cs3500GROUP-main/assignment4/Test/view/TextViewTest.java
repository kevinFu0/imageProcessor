package view;

import org.junit.Before;
import org.junit.Test;



import java.io.IOException;


import static org.junit.Assert.assertEquals;


/**
 * test class for text view.
 */
public class TextViewTest {

  ImageProcessorView view;
  Appendable ap;

  Appendable badAp;

  ImageProcessorView badApView;


  @Before
  public void init() {
    ap = new StringBuilder();
    view = new TextView(ap);
    badAp = new CorruptAppendable();
    badApView = new TextView(badAp);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadConstructor() {
    view = new TextView(null);
  }

  @Test(expected = IOException.class)
  public void testBadAppendableMessage() throws IOException {
    this.badApView.renderMessage("Hello");
  }

  @Test
  public void testRenderMessage() {
    String msg = "load images/koala.ppm koala";
    try {
      view.renderMessage(msg);
      assertEquals(msg, ap.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }


  }




}