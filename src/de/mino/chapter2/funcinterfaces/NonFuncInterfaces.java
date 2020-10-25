package de.mino.chapter2.funcinterfaces;

/** These aren't functional interfaces (except 'Sprint') because they don't have exactly 1 abstract method.*/
public class NonFuncInterfaces {

  public class Animal {}

  @FunctionalInterface
  public interface Sprint {

    public void sprint(Animal animal);
  }

  // Doesn't define any abstract methods
  public interface Walk {}

  // Has 2 abstract methods because it inherits the 'sprint' Method
  public interface Dance extends Sprint {
    public void dance(Animal animal);
  }

  // Also has 2 abstract methods
  public interface Crawl {
    public void crawl();
    public void getCount();
  }
}
