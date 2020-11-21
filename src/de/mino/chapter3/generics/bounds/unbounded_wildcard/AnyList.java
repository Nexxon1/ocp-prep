package de.mino.chapter3.generics.bounds.unbounded_wildcard;

import java.util.ArrayList;
import java.util.List;

public class AnyList {

  public static void main(String[] args) {
    List<String> keywords = new ArrayList<>();
    keywords.add("Java");
    printList(keywords);
  }

  // Takes a List of "whatever"
  private static void printList(List<?> list) {
    for (Object elem : list) {
      System.out.println(elem);
    }
  }
}
