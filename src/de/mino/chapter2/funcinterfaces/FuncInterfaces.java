package de.mino.chapter2.funcinterfaces;

/** A functional interface has exactly 1 abstract method. */
public class FuncInterfaces {

  public class Animal {}

  @FunctionalInterface
  public interface Sprint {
    public void sprint(Animal animal);
  }

  // Since it extends 'Sprint' this interface is a functional interface because it inherits the
  // method.
  public interface Run extends Sprint {}

  // Functional interface because the 'sprint' method of the parent class gets overridden, so there
  // is still only one functional interface
  public interface SprintFaster extends Sprint {
    public void sprint(Animal animal);
  }

  // Functional interface because it has only 1 abstract method defined (comes from the parent
  // interface 'Sprint')
  public interface Skip extends Sprint {
    public default int getHopCount(String abc) {
      return 10;
    }

    public static void skip(int speed) {}
  }
}
