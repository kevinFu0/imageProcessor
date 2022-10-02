package view;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

import model.ImageProcessorModelHistogram;

public class ImageGuiViewImpl extends JFrame implements ImageGuiView {
  ImageProcessorModelHistogram model;

  JLabel imageLabel;

  public ImageGuiViewImpl(ImageProcessorModelHistogram model) {
    this.model = model;
  }


  public void initializeView() {
    this.setSize(800, 600);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.imageLabel = new JLabel();
    this.setVisible(true);
  }

  private BufferedImage createBufferedImage() {
    int imageWidth = this.model.getImageWidth();
    int imageHeight = this.model.getImageHeight();
    int numChannels = 3;
    int imageType = BufferedImage.TYPE_INT_ARGB;
    BufferedImage buffImage =
            new BufferedImage(imageWidth, imageHeight, imageType);
    for (int r = 0; r < imageHeight; r++) {
      for (int c = 0; c < imageWidth; c++) {
        Color colorInfo = new Color(this.model.getPixelAt(r, c)[0], this.model.getPixelAt(r, c)[1],
                this.model.getPixelAt(r, c)[2]);
        if (numChannels == 4) {
          if (this.model.getPixelAt(0, 0).length == 3) {
            colorInfo = new Color(this.model.getPixelAt(r, c)[0], this.model.getPixelAt(r, c)[1],
                    this.model.getPixelAt(r, c)[2], 255);
          }
          else {
            colorInfo = new Color(this.model.getPixelAt(r, c)[0], this.model.getPixelAt(r, c)[1],
                    this.model.getPixelAt(r, c)[2], this.model.getPixelAt(r, c)[3]);
          }
        }
        buffImage.setRGB(c, r, colorInfo.getRGB());
      }
    }
    return buffImage;
  }



  @Override
  public void refreshImage() {
    this.imageLabel.setIcon(new ImageIcon(this.createBufferedImage()));
  }

  @Override
  public void refreshHistogram() {

  }

  @Override
  public void renderMessage() {

  }
}
