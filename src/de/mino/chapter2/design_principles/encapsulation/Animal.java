package de.mino.chapter2.design_principles.encapsulation;

/**
 * Thanks to encapsulation we can fulfill the requirements that each Animal object has a non-null,
 * non-empty species field and each animal has an age field that is greater or equal to 0. If we
 * just had public instance variables it would be possible to create 'invalid' Animals.
 */
public class Animal {

  private String species;
  private int age;

  public Animal(String species) {
    this.setSpecies(species);
  }

  public String getSpecies() {
    return species;
  }

  public void setSpecies(String species) {
    if (species == null || species.trim().length() == 0) {
      throw new IllegalArgumentException("Species is required");
    }
    this.species = species;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    if (age < 0) {
      throw new IllegalArgumentException("Age cannot be a negative number");
    }
    this.age = age;
  }
}
