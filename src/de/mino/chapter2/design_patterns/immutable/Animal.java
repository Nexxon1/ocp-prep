package de.mino.chapter2.design_patterns.immutable;

import java.util.ArrayList;
import java.util.List;

/** Example for an immutable class */
// Class marked final so methods cannot bne overridden by a subclass.
public final class Animal {

  // All fields are private and final
  private final String species;
  private final int age;
  private final List<String> favoriteFoods;

  // Everything is set with the Constructor and nothing can change afterwards.
  public Animal(String species, int age, List<String> favoriteFoods) {
    this.species = species;
    this.age = age;
    this.favoriteFoods = new ArrayList<>(favoriteFoods); // !!!
    // Important to not just write 'this.favoriteFoods = favoriteFoods' because otherwise the caller
    // that creates the object is using the same reference as the immutable object, which means we
    // could change the List because List itself is mutable.
  }

  public String getSpecies() {
    return species;
  }

  public int getAge() {
    return age;
  }

  public int getFavoriteFoodsCount() {
    return favoriteFoods.size();
  }

  public String getFavoriteFood(int index) {
    return favoriteFoods.get(index);
  }

  // Don't create a getter for 'favoriteFoods' otherwise the class wouldn't be immutable since we
  // could change the contents in the List (A List itself is mutable)
  // However we could create a copy like that:
  public List<String> getFavoriteFoods() {
    return new ArrayList<>(favoriteFoods);
  }

  @Override
  public String toString() {
    return "Animal{"
        + "species='"
        + species
        + '\''
        + ", age="
        + age
        + ", favoriteFoods="
        + favoriteFoods
        + '}';
  }
}
