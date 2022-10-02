package controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import model.EnhancedImageProcessorModel;
import model.EnhancedProcessorImpl;
import model.ImageProcessorHistogramImpl;
import view.ImageGuiView;
import view.ImageGuiViewImpl;
import view.ImageProcessorView;
import view.TextView;


/**
 * class to run TextController using main.
 */
public class ProgramMain {

  /**
   * main method.
   * @param args string args
   */
  public static void main(String[] args) {
    Appendable out = new PrintStream(System.out);
    Readable in = new InputStreamReader(System.in);
    ImageProcessorView v1 = new TextView(out);
    EnhancedImageProcessorModel m1 = new EnhancedProcessorImpl();
    ImageProcessController controller;
    if (args.length > 0) {
      if (args[0].contains("-file")) {
        try {
          in = new FileReader(args[1]);
        } catch (FileNotFoundException e) {
          throw new IllegalArgumentException("The file could not be found");
        }
        controller = new TextController(m1, v1, in);
      }
      else {
        if (args[0].contains("-text")) {
          controller = new TextController(m1, v1, in);
        }
        else {
          throw new IllegalArgumentException("Invalid command-line arguments.");
        }
      }
    }
    else {
      ImageGuiView guiView = new ImageGuiViewImpl(new ImageProcessorHistogramImpl(m1));
      controller = new ImageGuiController(m1, guiView);
    }
    controller.run();
  }

}
