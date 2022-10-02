package model;

public interface ImageProcessorModelHistogram extends EnhancedImageProcessorModel {
  int[] getHistogram(HistValueType type, String imageName);
}
