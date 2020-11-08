package de.mino.chapter2.design_patterns.immutable;

import java.util.Arrays;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    Animal lion = new Animal("lion", 5, Arrays.asList("meat", "more meat"));

    // We can "modify" immutable objects by creating another immutable object containing the same
    // information plus whatever we wanted to change.
    // So in fact we can't modify immutable objects itself
    List<String> updatedFavoriteFoods = lion.getFavoriteFoods();
    updatedFavoriteFoods.add("even more meat");
    Animal updatedLion = new Animal(lion.getSpecies(), lion.getAge() + 1, updatedFavoriteFoods);

    System.out.println(lion);
    System.out.println(updatedLion);
  }
}
