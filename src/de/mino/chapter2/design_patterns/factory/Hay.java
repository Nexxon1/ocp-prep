package de.mino.chapter2.design_patterns.factory;

public class Hay extends Food {

  // It's best to make the constructor package private so it forces any class outside the package
  // into using the FoodFactory class to create an instance of a Food object.
  public Hay(int quantity) {
    super(quantity);
  }

  @Override
  public void consumed() {
    System.out.println("Hay eaten: " + getQuantity());
  }
}
