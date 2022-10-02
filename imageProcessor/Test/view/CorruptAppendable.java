package view;

import java.io.IOException;

/**
 * Appendable Mock that only throws IOExceptions, for testing purposes.
 */
public class CorruptAppendable implements Appendable {


  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException();
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException();
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException();
  }
}
