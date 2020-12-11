package de.mino.chapter3.collections.sets;

import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class SetMethods {

  private static void addingElements() {
    System.out.println("Adding elements to a set and printing");
    Set<Integer> hashSet = new HashSet<>();
    boolean b1 = hashSet.add(66); // true
    boolean b2 = hashSet.add(10); // true
    boolean b3 = hashSet.add(66); // false (That element is already in the Set --> Can't be added)
    boolean b4 = hashSet.add(8); // true
    System.out.println(b1 + " " + b2 + " " + b3 + " " + b4);
    // Note that the elements are printed in an arbitrary order!
    System.out.println("HashSet prints the elements in an arbitrary order:");
    for (Integer elem : hashSet) {
      System.out.print(elem + ", ");
    }

    System.out.println(
        "\nTreeSet prints the elements in their natural order. Numbers implement the Comparable interface which is used for sorting here");
    Set<Integer> treeSet = new TreeSet<>(hashSet);
    for (Integer elem : treeSet) {
      System.out.print(elem + ", ");
    }
  }

  private static void navigableSet() {
    System.out.println("\n\nNavigableSet Methods");
    NavigableSet<Integer> set = new TreeSet<>();
    for (int i = 0; i <= 10; i++) {
      set.add(i * 2);
    }
    System.out.println("lower returns the greatest element that is < e");
    System.out.println(set.lower(10));
    System.out.println("floor returns the greatest element that is <= e");
    System.out.println(set.floor(10));
    System.out.println("ceiling returns the smallest element that is >= e");
    System.out.println(set.ceiling(20));
    System.out.println("higher returns the smallest element that is > e");
    System.out.println(set.higher(20)); // null because there is no element that is > 20
  }

  public static void main(String[] args) {
    addingElements();
    navigableSet();
  }
}
