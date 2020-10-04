package de.mino.chapter1.enums;

/** An enumeration is like a fixed sets of static final constants */
public enum Season {
  SPRING,
  SUMMER,
  FALL,
  WINTER
}

class Starter {

  private static void loopEnum() {
    System.out.println("\n--- Loop through enum ---");
    for (Season season : Season.values()) {
      System.out.println(season.name() + " " + season.ordinal());
    }
  }

  private static void convertToEnum() {
    System.out.println("\n--- Convert String to enum ---");
    Season s1 = Season.valueOf("SUMMER"); // SUMMER
    System.out.println(s1);
    // The case sensitivity is important:
    /*
    Season s2 =
        Season.valueOf(
            "summer"); // IllegalArgumentException: No enum constant
                       // de.mino.chapter1.enums.Season.summer
     */
  }

  private static void switchCase() {
    System.out.println("\n--- Enum in switch case ---");
    Season summer = Season.SUMMER;
    switch (summer) {
      case WINTER:
        System.out.println("It's winter.");
        break;
      case SUMMER:
        System.out.println("It's summer.");
        break;
      default:
        System.out.println("Is it summer yet?");
    }
  }

  public static void main(String[] args) {

    Season summer = Season.SUMMER;
    System.out.println(Season.SUMMER); // SUMMER
    System.out.println(summer == Season.SUMMER); // true

    loopEnum();

    convertToEnum();

    switchCase();
  }
}
