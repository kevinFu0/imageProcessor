package model;

import java.io.IOException;

import commands.Command;

/**
 * Represents an image processor compatible with commands.
 */
public interface ImageProcessorWithCommand extends ImageProcessorModel {

  /**
   * Applies a command to this image processor model.
   * @param command a command.
   */

  void apply(Command command) throws IOException;

}
