package de.mino.chapter2.design_patterns.builder;

import de.mino.chapter2.design_patterns.immutable.Animal;

import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    Animal duck =
        new AnimalBuilder()
            .setAge(4)
            .setFavoriteFoods(Arrays.asList("grass", "fish"))
            .setSpecies("duck")
            .build();
    System.out.println(duck);
  }
}
