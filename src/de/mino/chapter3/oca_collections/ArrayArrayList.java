package de.mino.chapter3.oca_collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayArrayList {

  public static void main(String[] args) {
    List<String> list = new ArrayList<>(); // [] --> empty list
    list.add("Fluffy"); // [Fluffy]
    list.add("Webby"); // [Fluffy, Webby]
    System.out.println("ArrayList: " + list);

    String[] array = new String[list.size()]; // [null, null] --> empty array
    array[0] = list.get(0); // [Fluffy, null]
    array[1] = list.get(1); // [Fluffy, Webby]
    System.out.println("Array: " + Arrays.toString(array));

    linkArrayAndList();

    searchingAndSorting();

    wrapperClassesAndAutoBoxing();

    diamondOperator();
  }

  private static void linkArrayAndList() {
    System.out.println("\nLink created when converting between array and ArrayList");
    String[] array2 = {"arrayElem0", "arrayElem1"};
    List<String> list2 =
        Arrays.asList(
            array2); // returns a fixed size list - Changes to the List write through to the array
    // and vice versa
    System.out.println(Arrays.toString(array2) + "\t" + list2); // [arrayElem0, arrayElem1]
    array2[0] = "arrayElem0Changed";
    System.out.println(Arrays.toString(array2) + "\t" + list2); // [arrayElem0Changed, arrayElem1]
    list2.set(1, "arrayElem1Changed");
    System.out.println(
        Arrays.toString(array2) + "\t" + list2); // [arrayElem0Changed, arrayElem1Changed]
    // list2.remove(1); // Not allowed - When calling asList() the returned List is not resizable
    // because it is backed by the underlying array
  }

  private static void searchingAndSorting() {
    System.out.println("\n Sorting with Array");
    int[] numbers = {6, 9, 1, 8};
    Arrays.sort(numbers); // [1, 6, 8, 9]
    // Binary search assumes the input to be sorted
    System.out.println(Arrays.binarySearch(numbers, 6)); // 1 --> index where the match is found
    System.out.println(
        Arrays.binarySearch(
            numbers,
            3)); // -2 --> negative index -1 where the requested value would need to be inserted for
    // the correct order. So number 3 would need to be inserted at index 1

    System.out.println("Sorting with List");
    List<Integer> list = Arrays.asList(9, 7, 5, 3);
    Collections.sort(list); // [3, 5, 7, 9]
    System.out.println(Collections.binarySearch(list, 3)); // 0
    System.out.println(Collections.binarySearch(list, 2)); // -1
  }

  /**
   * Each primitive has a corresponding wrapper class.
   *
   * <p>Autoboxing automatically converts a primitive to the corresponding wrapper class when
   * needed.
   *
   * <p>Unboxing automatically converts a wrapper class back to a primitive.
   */
  private static void wrapperClassesAndAutoBoxing() {
    System.out.println("\nBe aware with the overloaded remove method for Integer lists");
    List<Integer> numbers = new ArrayList<>();
    numbers.add(1);
    numbers.add(new Integer(3));
    numbers.add(new Integer(5));
    numbers.remove(1); // Removes the element at index one
    numbers.remove(new Integer(5)); // Removes the (first) Integer 5 from the List
    System.out.println(numbers); // [1]
    // Java also automatically converts the wrapper classes to primitives via unboxing:
    int num = numbers.get(0);
  }

  private static void diamondOperator() {
    // Before Java 5 you couldn't specify what exact Objects you wanted in a List:
    List oldSchool = new ArrayList();
    // Thanks to Generics and the Diamond Operator you can enforce the type:
    List<String> withJava5 = new ArrayList<String>();
    // In Java 7 it was possible to shorten that and just use the Diamond operator:
    List<String> withJava7 = new ArrayList<>();
  }
}
