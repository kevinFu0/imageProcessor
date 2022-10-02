package commands;

import java.io.IOException;

import model.ImageProcessorModel;

/**
 * represents a command for saving an image.
 */
public class SaveImage implements Command {

  private String filePath;
  private String imageName;

  /**
   * saveImage command constructor.
   *
   * @param filePath  the filepath
   * @param imageName the imageName
   * @throws IllegalArgumentException if the filepath or imageName is null
   */
  public SaveImage(String filePath, String imageName) throws IllegalArgumentException {
    if (filePath == null || imageName == null) {
      throw new IllegalArgumentException("can't have a null filePath or imageName");
    }
    this.filePath = filePath;
    this.imageName = imageName;
  }

  @Override
  public void execute(ImageProcessorModel im) {
    try {
      im.saveImage(filePath, imageName);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
