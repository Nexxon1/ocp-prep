package de.mino.chapter1.enums;

/**
 * You can use abstract methods in enums. This is basically like an abstract class with a bunch of
 * tiny subclasses implementing the abstract method.
 * If the enum declares an abstract method, each enum value must implement it.
 */
public enum SeasonAbstract {
  WINTER {
    public void printHours() {
      System.out.println("9am-3pm");
    }
  },
  SPRING {
    public void printHours() {
      System.out.println("9am-5pm");
    }
  },
  SUMMER {
    public void printHours() {
      System.out.println("9am-7pm");
    }
  },
  FALL {
    public void printHours() {
      System.out.println("9am-5pm");
    }
  };

  public abstract void printHours();
}

/** The same works with a default implementation. */
enum SeasonDefault {
  WINTER {
    public void printHours() {
      System.out.println("short hours");
    }
  },
  SUMMER {
    public void printHours() {
      System.out.println("long hours");
    }
  },
  SPRING,
  FALL;

  public void printHours() {
    System.out.println("default hours");
  }
}
