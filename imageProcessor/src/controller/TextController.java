package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.Function;

import commands.Blur;
import commands.Greyscale;
import commands.Sepia;
import commands.Sharpen;
import model.EnhancedImageProcessorModel;
import commands.Command;
import commands.LoadImage;
import commands.SaveImage;
import commands.Brighten;
import commands.Darken;
import commands.FlipHorizontal;
import commands.FlipVertical;
import commands.VisualizeIntensity;
import commands.VisualizeLuma;
import commands.VisualizeRGB;
import commands.VisualizeValue;
import view.ImageProcessorView;

/**
 * represents a controller for text-based image processor.
 */
public class TextController implements ImageProcessController {

  private EnhancedImageProcessorModel model;
  private ImageProcessorView view;
  private Readable in;

  /**
   * constructor for controller throws an exception if the model, view, or readable is in.
   *
   * @param model image processor model
   * @param view  image processor view
   * @param in    readable
   * @throws IllegalArgumentException if any of the arguments are null
   */
  public TextController(EnhancedImageProcessorModel model, ImageProcessorView view, Readable in)
          throws IllegalArgumentException {
    if (model == null || view == null || in == null) {
      throw new IllegalArgumentException("The arguments can't be null");
    }
    this.model = model;
    this.view = view;
    this.in = in;
  }


  /**
   * we using the command design pattern for the controller.
   *
   * @throws IllegalStateException if the controller can't process input or transmit output.
   */

  @Override
  public void run() throws IllegalStateException {
    try {
      this.view.renderMessage("Welcome to Image Processor!\n");
      this.view.renderMessage("Type commands or enter q or quit to exit the program.\n");
    } catch (IOException e) {
      e.printStackTrace();
    }

    // storing commands in a map of <Scanner,Commands>
    Scanner scan = new Scanner(this.in);
    Stack<Command> commands = new Stack<>();
    // function takes in a scanner and returns a command
    Map<String, Function<Scanner, Command>> knownCommands = new HashMap<>();

    //putting all the commands into the map
    knownCommands.put("save", (Scanner s) -> new SaveImage(s.next(), s.next()));
    knownCommands.put("load", (Scanner s) -> new LoadImage(s.next(), s.next()));
    knownCommands.put("visualizeRGB",
        (Scanner s) -> new VisualizeRGB(s.next(), s.next(), s.next()));
    knownCommands.put("visualize-value", (Scanner s) -> new VisualizeValue(s.next(), s.next()));
    knownCommands.put("visualize-intensity",
        (Scanner s) -> new VisualizeIntensity(s.next(), s.next()));
    knownCommands.put("visualize-luma", (Scanner s) -> new VisualizeLuma(s.next(), s.next()));
    knownCommands.put("vertical-flip", (Scanner s) -> new FlipVertical(s.next(), s.next()));
    knownCommands.put("horizontal-flip", (Scanner s) -> new FlipHorizontal(s.next(), s.next()));
    knownCommands.put("brighten", (Scanner s) -> new Brighten(s.nextInt(), s.next(), s.next()));
    knownCommands.put("darken", (Scanner s) -> new Darken(s.nextInt(), s.next(), s.next()));
    knownCommands.put("blur", (Scanner s) -> new Blur(s.next(), s.next()));
    knownCommands.put("sharpen", (Scanner s) -> new Sharpen(s.next(), s.next()));
    knownCommands.put("greyscale", (Scanner s) -> new Greyscale(s.next(), s.next()));
    knownCommands.put("sepia", (Scanner s) -> new Sepia(s.next(), s.next()));

    //loop until scanner reads q or quit
    while (scan.hasNext()) {
      Command c;
      String in = scan.next();
      if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")) {
        try {
          this.view.renderMessage("Program quit\n");
        } catch (IOException e) {
          e.printStackTrace();
        }
        return;
      }
      Function<Scanner, Command> cmd = knownCommands.getOrDefault(in, null);
      if (cmd == null) {
        try {
          this.view.renderMessage("Invalid command!\n");
        } catch (IOException e) {
          e.printStackTrace();
        }
      } else {
        c = cmd.apply(scan);
        c.execute(model);
        try {
          this.view.renderMessage(in + " applied successfully!\n");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
