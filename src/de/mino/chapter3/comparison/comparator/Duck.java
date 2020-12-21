package de.mino.chapter3.comparison.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Duck {
  private String name;
  private int weight;

  public Duck(String name, int weight) {
    this.name = name;
    this.weight = weight;
  }

  public String getName() {
    return name;
  }

  public int getWeight() {
    return weight;
  }

  @Override
  public String toString() {
    return name + " " + weight;
  }

  public static void main(String[] args) {
    Comparator<Duck> byWeightLongVersion = new Comparator<Duck>() {
      @Override
      public int compare(Duck d1, Duck d2) {
        return d1.getWeight() - d2.getWeight();
      }
    };
    // Since Comparator is a functional interface we can use lambdas

    Comparator<Duck> byWeight = (d1, d2) -> d1.getWeight() - d2.getWeight();
    // Another way is to use a helper method:
    // Comparator<Duck> byWeight = Comparator.comparingInt(Duck::getWeight);

    List<Duck> ducks = new ArrayList<>();
    ducks.add(new Duck("Puddles", 10));
    ducks.add(new Duck("Quack", 7));
    System.out.println(ducks); // [Puddles 10, Quack 7]

    // You can also pass a Comparator to the sort method. Without that the passed objects need to implement
    // the Comparable interface - see `chapter3.comparison.comparable.Duck`
    Collections.sort(ducks, byWeight);
    System.out.println(ducks); // [Quack 7, Puddles 10]
  }
}
