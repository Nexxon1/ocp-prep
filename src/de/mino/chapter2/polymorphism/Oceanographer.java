package de.mino.chapter2.polymorphism;

public class Oceanographer {

  /**
   * Accepts any object whose class implements the LivesInOcean interface.
   */
  public void checkSound(LivesInOcean animal) {
    animal.makeSound();
  }

  public static void main(String[] args) {
    Oceanographer o = new Oceanographer();
    o.checkSound(new Dolphin()); // Dolphin - whistle
    o.checkSound(new Whale()); // Whale - sing
  }
}

interface LivesInOcean {
  public void makeSound();
}

class Dolphin implements LivesInOcean {
  @Override
  public void makeSound() {
    System.out.println("Dolphin - whistle");
  }
}

class Whale implements LivesInOcean {
  @Override
  public void makeSound() {
    System.out.println("Whale - sing");
  }
}
