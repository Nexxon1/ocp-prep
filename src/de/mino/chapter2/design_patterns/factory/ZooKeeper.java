package de.mino.chapter2.design_patterns.factory;

public class ZooKeeper {
  public static void main(String[] args) {

    // We don't have to worry about what kind of food gets returned, as long as it implements the
    // Food interface. Thanks to the FoodFactory we have loose coupling of ZooKeeper and Food which
    // lets us easily change the rules in FoodFactory without having to update code anywhere else.
    final Food food = FoodFactory.getFood("polar bear");
    food.consumed();
  }
}
