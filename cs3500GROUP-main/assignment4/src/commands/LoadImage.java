package commands;


import model.ImageProcessorModel;

/**
 * load image command.
 */
public class LoadImage implements Command {

  private String filePath;
  private String imageName;


  /**
   * load image constructor.
   *
   * @param filePath  the file path
   * @param imageName the image name
   * @throws IllegalArgumentException if the filepath or imagename is null
   */
  public LoadImage(String filePath, String imageName) throws IllegalArgumentException {
    if (filePath == null || imageName == null) {
      throw new IllegalArgumentException("can't have a null filePath or imageName");
    }
    this.filePath = filePath;
    this.imageName = imageName;
  }

  @Override
  public void execute(ImageProcessorModel im) {
    im.loadImage(filePath, imageName);

  }
}
