package de.mino.chapter1.enums;

/**
 * Enums with a constructor. In this example to keep track of traffic patterns for each season in a
 * zoo.
 */
public enum SeasonConstructor {
  SPRING("Medium"),
  SUMMER("High"),
  FALL("Medium"),
  WINTER("Low");

  String expectedVisitors;

  // The constructor can only be private or package private because it can only be called from
  // within the enum. Protected or public would not work.
  private SeasonConstructor(String expectedVisitors) {
    System.out.println(
        "The enum constructor gets called only on the first time. " + expectedVisitors);
    this.expectedVisitors = expectedVisitors;
  }

  void printExpectedVisitors() {
    System.out.println(expectedVisitors);
  }
}

class Starter2 {
  public static void main(String[] args) {
    constructorBehavior();
  }

  private static void constructorBehavior() {
    System.out.println("--- Enum constructor behavior ---");
    SeasonConstructor firstCall =
        SeasonConstructor.SUMMER; // calls the constructor for all enum values
    SeasonConstructor secondCall =
        SeasonConstructor
            .SUMMER; // Doesn't call the constructor - just returns the already constructed enum
    // value.
  }
}
