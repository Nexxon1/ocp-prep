package de.mino.chapter3.collections.lists;

import java.util.ArrayList;
import java.util.List;

public class ListMethods {

  private static void commonMethods() {
    System.out.println("\nCommon methods for adding and removing items from a list");
    List<String> list = new ArrayList<>();
    list.add("SD"); // [SD]
    list.add(0, "NY"); // [NY, SD]
    list.set(1, "FL"); // [NY, FL]
    list.remove("NY"); // [FL]
    list.remove(0); // []

    list.add("OH"); // [OH]
    list.add("CO"); // [OH, CO]
    list.add("NJ"); // [OH, CO, NJ]
    String state = list.get(0); // OH
    int index = list.indexOf("NJ"); // 2
    System.out.println(state + index);
  }

  public static void main(String[] args) {
    commonMethods();
  }
}
