package commands;


import model.ImageProcessorModel;

/**
 * represents a command.
 */
public interface Command {
  /**
   * executes a command on this image processor.
   *
   * @param im image processor model
   */
  void execute(ImageProcessorModel im);


}
