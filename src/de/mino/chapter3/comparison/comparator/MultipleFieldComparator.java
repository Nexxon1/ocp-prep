package de.mino.chapter3.comparison.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/** Comparing Ducks by name. If the name is the same compare by weight. */
public class MultipleFieldComparator {

  private static Comparator<Duck> theOldWay() {
    Comparator<Duck> byNameAndWeight =
        new Comparator<Duck>() {
          @Override
          public int compare(Duck d1, Duck d2) {
            // Using the String compareTo() method
            int result = d1.getName().compareTo(d2.getName());
            if (result != 0) {
              return result;
            } else {
              return d1.getWeight() - d2.getWeight();
            }
          }
        };
    return byNameAndWeight;
  }

  private static Comparator<Duck> theNewWay() {
    Comparator<Duck> byNameAndWeight =
        (d1, d2) -> {
          Comparator<Duck> c =
              Comparator.comparing(Duck::getName).thenComparingInt(Duck::getWeight);
          return c.compare(d1, d2);
        };
    return byNameAndWeight;
  }

  public static void main(String[] args) {
    List<Duck> ducks = new ArrayList<>();
    ducks.add(new Duck("Puddles", 10));
    ducks.add(new Duck("Quack", 7));
    ducks.add(new Duck("Puddles", 9));
    System.out.println(ducks); // [Puddles 10, Quack 7, Puddles 9]

    Collections.sort(ducks, theNewWay());
    System.out.println(ducks); // [Puddles 9, Puddles 10, Quack 7]
    Collections.sort(ducks, theOldWay());
    System.out.println(ducks); // [Puddles 9, Puddles 10, Quack 7]
  }
}
