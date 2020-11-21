package de.mino.chapter3.generics;

import java.util.ArrayList;
import java.util.List;

public class Advantage {

  public static void main(String[] args) {
    List names = new ArrayList();
    names.add(new StringBuilder("Webby")); // All okay because no type specified
    printNamesNoGeneric(names); // Runtime Exception because the method tries to cast to String

    // Better: Use Generics
    List<String> namesSave = new ArrayList<>();
    namesSave.add("Webby");
    // namesSave.add(new StringBuilder("Webby")); // Error already at compile time :)
  }

  private static void printNamesNoGeneric(List list) {
    for (int i = 0; i < list.size(); i++) {
      // potential class cast exception because we didn't specify the type that can be put inside
      // the list
      String name = (String) list.get(i);
      System.out.println(name);
    }
  }
}
