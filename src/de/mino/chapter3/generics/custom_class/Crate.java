package de.mino.chapter3.generics.custom_class;

/**
 * The Crate class works with any type of class.
 *
 * <p>Before generics, we would have needed Crate to use the Object class for its instance variable,
 * which would have put the burden on the caller of needing to cast the object it receives on
 * emptying the crate.
 */
public class Crate<T> {

  private T contents;

  public T emptyCrate() {
    return contents;
  }

  public void packCrate(T contents) {
    this.contents = contents;
  }
}
