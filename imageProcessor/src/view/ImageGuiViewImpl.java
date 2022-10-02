package view;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Component;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import controller.Features;

/**
 * Represents a GUI view containing the methods necessary for displaying an image processor program.
 */
public class ImageGuiViewImpl extends JFrame implements ImageGuiView {
  private JLabel imageLabel;
  private JPanel histogramsPanel;
  private JLabel messageLabel;
  private JButton saveButton;
  private JButton loadButton;
  private JButton blurButton;
  private JButton sharpenButton;
  private JButton greyscaleButton;
  private JButton darkenButton;
  private JButton brightenButton;
  private JButton flipHorizButton;
  private JButton flipVertButton;
  private JButton sepiaButton;
  private JButton intensityButton;
  private JButton valueButton;
  private JButton lumaButton;
  private JButton redButton;
  private JButton greenButton;
  private JButton blueButton;
  private Features features;
  private JPanel barGraphRed;
  private JPanel barGraphGreen;
  private JPanel barGraphBlue;
  private JPanel barGraphIntensity;

  /**
   * Constructor for initializeView.
   */
  public ImageGuiViewImpl() {
    this.initializeView();
  }

  @Override
  public void initializeView() {
    this.setSize(1200, 900);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setTitle("Image Processor");
    JPanel featureButtonsPanel = new JPanel();
    featureButtonsPanel.setLayout(new BoxLayout(featureButtonsPanel, BoxLayout.Y_AXIS));
    featureButtonsPanel.setBorder(
            BorderFactory.createTitledBorder("Operation buttons:"));
    ((TitledBorder) featureButtonsPanel.getBorder()).setTitleFont(
            new Font("Arial", Font.BOLD, 14));
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    mainPanel.add(featureButtonsPanel, BorderLayout.WEST);
    this.histogramsPanel = new JPanel();
    this.histogramsPanel.setLayout(new BoxLayout(histogramsPanel, BoxLayout.Y_AXIS));
    this.histogramsPanel.setPreferredSize(new Dimension(600, 900));
    this.histogramsPanel.setBorder(
            BorderFactory.createTitledBorder("Histograms of rgb and intensity components:"));
    ((TitledBorder) this.histogramsPanel.getBorder()).setTitleFont(
            new Font("Arial", Font.BOLD, 14));
    mainPanel.add(this.histogramsPanel, BorderLayout.EAST);
    this.add(mainPanel);
    JPanel imagePanel = new JPanel();
    this.imageLabel = new JLabel();
    this.imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
    JScrollPane imageScrollPane = new JScrollPane(this.imageLabel);
    imageScrollPane.setPreferredSize(new Dimension(600, 450));
    imageScrollPane.setMaximumSize(new Dimension(600, 450));
    imagePanel.setPreferredSize(new Dimension(600, 450));
    imagePanel.setMaximumSize(new Dimension(600, 450));
    imagePanel.add(imageScrollPane);
    imagePanel.setBorder(
            BorderFactory.createTitledBorder("Image:"));
    ((TitledBorder) imagePanel.getBorder()).setTitleFont(
            new Font("Arial", Font.BOLD, 14));
    mainPanel.add(imagePanel, BorderLayout.CENTER);
    mainPanel.add(this.messageLabel = new JLabel("", SwingConstants.CENTER),
            BorderLayout.NORTH);
    this.messageLabel.setFont(new Font("Arial", Font.BOLD, 24));
    this.histogramsPanel.add(this.barGraphRed = new JPanel());
    this.histogramsPanel.add(this.barGraphGreen = new JPanel());
    this.histogramsPanel.add(this.barGraphBlue = new JPanel());
    this.histogramsPanel.add(this.barGraphIntensity = new JPanel());
    this.histogramsPanel.setPreferredSize(new Dimension(400, 900));
    this.histogramsPanel.setMaximumSize(new Dimension(400, 900));
    featureButtonsPanel.add(this.loadButton = new JButton("load"));
    featureButtonsPanel.add(this.saveButton = new JButton("save"));
    featureButtonsPanel.add(this.blurButton = new JButton("blur"));
    featureButtonsPanel.add(this.sharpenButton = new JButton("sharpen"));
    featureButtonsPanel.add(this.greyscaleButton = new JButton("greyscale"));
    featureButtonsPanel.add(this.darkenButton = new JButton("darken"));
    featureButtonsPanel.add(this.brightenButton = new JButton("brighten"));
    featureButtonsPanel.add(this.flipHorizButton = new JButton("flip horizontally"));
    featureButtonsPanel.add(this.flipVertButton = new JButton("flip vertically"));
    featureButtonsPanel.add(this.sepiaButton = new JButton("sepia"));
    featureButtonsPanel.add(this.intensityButton = new JButton("visualize intensity"));
    featureButtonsPanel.add(this.valueButton = new JButton("visualize value"));
    featureButtonsPanel.add(this.lumaButton = new JButton("visualize luma"));
    featureButtonsPanel.add(this.redButton = new JButton("visualize red"));
    featureButtonsPanel.add(this.greenButton = new JButton("visualize green"));
    featureButtonsPanel.add(this.blueButton = new JButton("visualize blue"));
    this.setVisible(true);
  }

  @Override
  public void addFeatures(Features features) {
    this.features = features;

    this.loadButton.addActionListener((ActionEvent e) -> {
      final JFileChooser fchooser = new JFileChooser(".");
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
              "Images file types", "jpg", "png", "ppm", "bmp");
      fchooser.setFileFilter(filter);

      int retvalue = fchooser.showOpenDialog(this);
      if (retvalue == JFileChooser.APPROVE_OPTION) {
        File f = fchooser.getSelectedFile();
        this.features.loadImage(f.getAbsolutePath());
      }
    });

    this.saveButton.addActionListener((ActionEvent e) -> {
      final JFileChooser fchooser = new JFileChooser();
      int retvalue = fchooser.showSaveDialog(this);
      if (retvalue == JFileChooser.APPROVE_OPTION) {
        File f = fchooser.getSelectedFile();
        System.out.println(f.getAbsolutePath());
        this.features.saveImage(f.getAbsolutePath());
      }
    });

    this.blurButton.addActionListener((ActionEvent e) -> features.blur());
    this.sharpenButton.addActionListener((ActionEvent e) -> features.sharpen());
    this.greyscaleButton.addActionListener((ActionEvent e) -> features.greyscale());
    this.darkenButton.addActionListener((ActionEvent e) -> features.darken());
    this.brightenButton.addActionListener((ActionEvent e) -> features.brighten());
    this.flipHorizButton.addActionListener((ActionEvent e) -> features.flipHorizontal());
    this.flipVertButton.addActionListener((ActionEvent e) -> features.flipVertical());
    this.sepiaButton.addActionListener((ActionEvent e) -> features.sepia());
    this.intensityButton.addActionListener((ActionEvent e) -> features.visualizeIntensity());
    this.valueButton.addActionListener((ActionEvent e) -> features.visualizeValue());
    this.lumaButton.addActionListener((ActionEvent e) -> features.visualizeLuma());
    this.redButton.addActionListener((ActionEvent e) -> features.visualizeRed());
    this.greenButton.addActionListener((ActionEvent e) -> features.visualizeGreen());
    this.blueButton.addActionListener((ActionEvent e) -> features.visualizeBlue());

  }


  @Override
  public void refreshImage(BufferedImage image) {
    this.imageLabel.setIcon(new ImageIcon(image));
  }

  @Override
  public void refreshHistogram(int[][] histograms) {
    this.histogramsPanel.remove(barGraphRed);
    this.histogramsPanel.remove(barGraphGreen);
    this.histogramsPanel.remove(barGraphBlue);
    this.histogramsPanel.remove(barGraphIntensity);
    this.barGraphRed = this.createHistogramGraph(Color.red, histograms[0]);
    this.barGraphGreen = this.createHistogramGraph(Color.green, histograms[1]);
    this.barGraphBlue = this.createHistogramGraph(Color.blue, histograms[2]);
    this.barGraphIntensity = this.createHistogramGraph(Color.gray, histograms[3]);
    this.histogramsPanel.add(this.barGraphRed);
    this.histogramsPanel.add(this.barGraphGreen);
    this.histogramsPanel.add(this.barGraphBlue);
    this.histogramsPanel.add(this.barGraphIntensity);
    this.histogramsPanel.revalidate();
  }

  //a panel representing a bar char made up of smaller panels
  private class BarChart extends JPanel {
    Color color;
    int[] histogram;

    BarChart(Color color, int[] histogram) {
      this.color = color;
      this.histogram = histogram;
      this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
      this.setAlignmentY(Component.BOTTOM_ALIGNMENT);
      double maxFrequency = 0;
      for (int i = 0; i < histogram.length; i++) {
        maxFrequency = Math.max(maxFrequency, histogram[i]);
      }
      double barWidth = 0.25;
      for (int i = 0; i < histogram.length; i++) {
        JPanel bar = new JPanel();
        int frequency = histogram[i];
        Double height =  (frequency / maxFrequency) * 175;
        Dimension dimension = new Dimension();
        dimension.setSize(barWidth, height);
        bar.setBackground(color);
        bar.setPreferredSize(dimension);
        bar.setMaximumSize(dimension);
        bar.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        this.add(bar);
      }
    }
  }

  //Creates a bar graph in the given color based on the given histogram
  private JPanel createHistogramGraph(Color color, int[] histogram) {
    JPanel barGraph = new BarChart(color, histogram);
    return barGraph;
  }

  @Override
  public void renderMessage(String message) {
    this.messageLabel.setText(message);
  }
}
