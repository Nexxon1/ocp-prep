package de.mino.chapter2.design_patterns.factory;

/**
 * The goal is that the FoodFactory must be used to create a Food. We set the constructors of the
 * Food implementation classes to package private so this can be enforced. Thanks to the FoodFactory
 * we have a loosely coupling between the Food class and the class that uses the FoodFactory,
 * allowing us to change the rules in the FoodFactory without having to touch any other code.
 */
public class FoodFactory {

  // Ofc in the real world it would be better to represent the Animals with actual classes instead
  // of just a String
  public static Food getFood(String animalName) {
    // Depending on the animal name we return different types of food.
    switch (animalName.toLowerCase()) {
      case "zebra":
        return new Hay(100);
      case "rabbit":
        return new Pellets(5);
      case "goat":
        return new Pellets(30);
      case "polar bear":
        return new Fish(10);
    }
    // Good practice to throw an exception if no matching subclass could be found
    throw new UnsupportedOperationException("Unsupported animal: " + animalName);
  }
}
