package de.mino.chapter3.comparison.comparable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Custom class Duck. We want to be able to sort the Ducks by name. */
public class Duck implements Comparable<Duck> {

  private String name;

  public Duck(String name) {
    this.name = name;
  }

  /**
   * <ul>
   *   <li>0 if the current object is equal to the argument
   *   <li><0 if the current object is smaller than the argument
   *   <li>>0 if the current object is larger than the argument
   * </ul>
   */
  @Override
  public int compareTo(Duck duck) {
    // Call String's compareTo method
    return name.compareTo(duck.name);
  }

  @Override
  public String toString() {
    return "Duck{" + "name='" + name + '\'' + '}';
  }

  public static void main(String[] args) {
    List<Duck> ducks = new ArrayList<>();
    Duck quack = new Duck("Quack");
    Duck puddles = new Duck("Puddles");
    ducks.add(quack);
    ducks.add(puddles);

    System.out.println(quack.compareTo(puddles)); // 1 because the current object (Quack) is "bigger" than the passed argument (Puddles)

    // Only objects that implement Comparable can be passed to the sort method.
    // Internally it uses the overridden compareTo method to sort the elements accordingly
    Collections.sort(ducks);
    System.out.println(ducks);
  }
}
