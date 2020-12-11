package de.mino.chapter3.collections.map;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapMethods {

  private static void hashMap() {
    System.out.println("HashMap Demonstration");
    Map<String, String> map = new HashMap<>();
    map.put("koala", "bamboo");
    map.put("lion", "meat");
    map.put("giraffe", "leaf");

    String food = map.get("koala");
    System.out.println("Get value for key koala: " + food); // bamboo

    System.out.println("Printing keys with keySet. Java uses the hashCode() of the key to determine the order");
    for (String key : map.keySet()) {
      System.out.print(key + ","); // koala, giraffe, lion
    }

    System.out.println("\nDoing some boolean checks on the Map");
    // Careful! contains() is a method on the Collection interface but not the Map interface
    // map.contains("lion"); // Does not compile
    System.out.println(map.containsKey("lion"));    // true
    System.out.println(map.containsValue("lion"));  // false
    System.out.println(map.size()); //3
  }

  private static void treeMap() {
    System.out.println("\nTreeMap Demonstration");
    Map<String, String> map = new TreeMap<>();
    map.put("koala", "bamboo");
    map.put("lion", "meat");
    map.put("giraffe", "leaf");

    String food = map.get("koala");
    System.out.println("Get value for key koala: " + food); // bamboo

    System.out.println("Printing keys with keySet. Java sorts the String keys alphabetically.");
    for (String key : map.keySet()) {
      System.out.print(key + ","); // koala, giraffe, lion
    }
    System.out.println("\nPrinting the values. The order corresponds to the order of the keys.");
    for (String value: map.values()) {
      System.out.print(value + ",");
    }
  }

  public static void main(String[] args) {
    hashMap();
    treeMap();
  }
}
