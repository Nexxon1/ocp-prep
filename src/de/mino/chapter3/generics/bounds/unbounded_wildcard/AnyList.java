package de.mino.chapter3.generics.bounds.unbounded_wildcard;

import java.util.ArrayList;
import java.util.List;

public class AnyList {

  public static void main(String[] args) {
    List<String> keywords = new ArrayList<>();
    keywords.add("Java");
    printList(keywords);

    // A little off topic - The history why the wildcards were implemented:
    // printListCritical(keywords);  // Does not compile
    /*
    Why??? String is a subclass of Object.
    The reason is that otherwise we could write code like this:
    1) List<Integer> numbers = new ArrayList<>();
    2) numbers.add(new Integer(42));
    3) List<Object> objects = numbers; // Does not compile
    4) objects.add("forty two");
    --> Line 1) We promised that numbers only has Integer objects.
    If Line 3) would compile, we would break that promise in Line 4) by putting a String in there, since 'numbers' and 'objects' are references to the same object.
     */
  }

  // Takes a List of "whatever"
  private static void printList(List<?> list) {
    for (Object elem : list) {
      System.out.println(elem);
    }
  }

  // A little off topic - The history why the wildcards were implemented:
  private static void printListCritical(List<Object> list) {
    for (Object elem : list) {
      System.out.println(elem);
    }
  }
}
