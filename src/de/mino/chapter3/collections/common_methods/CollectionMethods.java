package de.mino.chapter3.collections.common_methods;

import java.util.*;

public class CollectionMethods {

  private static void add() {
    System.out.println("Adding Elements to a List");
    List<String> list = new ArrayList<>();
    System.out.println(list.add("Sparrow")); // true --> Adding was successful
    System.out.println(list.add("Sparrow")); // true --> Adding was successful

    System.out.println("Adding Elements to a Set");
    Set<String> set = new HashSet<>();
    System.out.println(set.add("Sparrow")); // true --> Adding was successful
    System.out.println(
        set.add(
            "Sparrow")); // false --> Adding was not successful - Sets can't have duplicate elements
  }

  private static void remove() {
    System.out.println("\nRemoving Elements from a List");
    List<String> birds = new ArrayList<>();
    birds.add("hawk"); // [hawk]
    birds.add("hawk"); // [hawk, hawk]
    System.out.println(
        birds.remove(
            "cardinal")); // false --> there is no such element in that collection so nothing is
    // removed
    System.out.println(
        birds.remove("hawk")); // true --> Notice that only the first match is removed
    System.out.println(birds);
    /*
    System.out.println(
        birds.remove(100)); // IndexOutOfBoundsException - There is no element at index 100
     */
  }

  private static void isEmptyAndSize() {
    System.out.println("\nChecking the amount of elements inside a Collection");
    List<String> birds = new ArrayList<>();
    System.out.println(birds.isEmpty()); // true
    System.out.println(birds.size()); // 0

    birds.add("hawk"); // [hawk]
    birds.add("hawk"); // [hawk, hawk]
    System.out.println(birds.isEmpty()); // false
    System.out.println(birds.size()); // 2
  }

  private static void clear() {
    System.out.println("\nDiscard all elements of a Collection");
    List<String> birds = new ArrayList<>();
    birds.add("hawk"); // [hawk]
    birds.add("hawk"); // [hawk, hawk]
    System.out.println(birds.isEmpty()); // false
    System.out.println(birds.size()); // 2
    birds.clear();
    System.out.println(birds.isEmpty()); // true
    System.out.println(birds.size()); // 0
  }

  private static void contains() {
    System.out.println("\nCheck if a certain value is in the Collection");
    List<String> birds = new ArrayList<>();
    birds.add("hawk"); // [hawk]
    System.out.println(birds.contains("hawk")); // true
    System.out.println(birds.contains("robin")); // false

    LinkedList<String> foo = new LinkedList<>();
    foo.addFirst("a");
  }

  public static void main(String[] args) {
    add();
    remove();
    isEmptyAndSize();
    clear();
    contains();
  }
}
