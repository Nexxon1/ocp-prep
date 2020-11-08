package de.mino.chapter2.design_patterns.builder;

import de.mino.chapter2.design_patterns.immutable.Animal;

import java.util.List;

/**
 * Builder pattern for the immutable Animal class we built earlier in
 * 'design_patterns.immutable.Animal'
 */
// Note that the Builder itself is mutable. However the result of the build() Method is an immutable
// object
public class AnimalBuilder {
  private String species;
  private int age;
  private List<String> favoriteFoods;

  // The setters return an instance of the builder object 'this'. That way they can be chained
  // together and be called in any order.
  public AnimalBuilder setSpecies(String species) {
    this.species = species;
    return this;
  }

  public AnimalBuilder setAge(int age) {
    this.age = age;
    return this;
  }

  public AnimalBuilder setFavoriteFoods(List<String> favoriteFoods) {
    this.favoriteFoods = favoriteFoods;
    return this;
  }

  // In the build method we can also do some validation to check if all required fields are set.
  public Animal build() {
    return new Animal(species, age, favoriteFoods);
  }
}
