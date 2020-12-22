package de.mino.chapter3.java8_additions;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Shows an overview of the new methods on the Collections interface that were introduced with Java
 * 8
 */
public class NewMethods {

  public static void main(String[] args) {
    usingRemoveIf();
    usingReplaceAll();
    usingForEach();

    System.out.println("\n--- New APIs in Map with Java 8 ---");
    usingPutIfAbsent();
    usingMerge();
    usingComputeIfPresent();
    usingComputeIfAbsent();
  }

  private static void usingRemoveIf() {
    System.out.println("--- removeIf() ---");
    List<String> list = new ArrayList<>();
    list.add("Magician");
    list.add("Assistant");
    System.out.println(list); // [Magician, Assistant]
    System.out.println("Removing all elements starting with 'A'");
    list.removeIf(s -> s.startsWith("A"));
    System.out.println(list); // [Magician]
  }

  private static void usingReplaceAll() {
    System.out.println("\n--- replaceAll() ---");
    List<Integer> list = Arrays.asList(1, 2, 3);
    System.out.println(list); // [1, 2, 3]
    System.out.println("Replace each element to their value multiplied by 2");
    list.replaceAll(x -> x * 2);
    System.out.println(list); // [2, 4, 6]
  }

  private static void usingForEach() {
    System.out.println("\n--- forEach() ---");
    System.out.println("Using the forEach Method to print all elements in a list");
    List<String> cats = Arrays.asList("Annie", "Ripley");
    cats.forEach(System.out::println);
    // Without method reference:
    // cats.forEach(c -> System.out.println(c));

  }

  private static void usingPutIfAbsent() {
    System.out.println("\n--- putIfAbsent() ---");
    Map<String, String> favorites = new HashMap<>();
    System.out.println("Using the old API put() to add or update a value in the Map");
    favorites.put("Jenny", "Bus Tour"); // {Jenny=Bus Tour}
    // If the map previously contained a mapping for the key, the old value is replaced by the
    // specified value.
    favorites.put("Jenny", "Tram"); // {Jenny=Tram}
    System.out.println(favorites);

    System.out.println(
        "Using putIfAbsent() to only add the key value pair if the key is not already associated with a value or if the value is mapped to null");
    favorites.putIfAbsent("Jenny", "Tram2");
    favorites.putIfAbsent("Tom", null);
    favorites.putIfAbsent("Sam", "Tram");
    favorites.putIfAbsent("Tom", "Tram");
    System.out.println(favorites); // {Tom=Tram, Jenny=Tram, Sam=Tram}
  }

  private static void usingMerge() {
    System.out.println("\n--- merge() ---");
    System.out.println("The merge function expects a BiFunction to determine which value to use");
    Map<String, String> favorites = new HashMap<>();
    favorites.put("Jenny", "Bus Tour");
    favorites.put("Tom", "Tram");
    System.out.println(favorites); // {Tom=Tram, Jenny=Bus Tour}

    // If the passed value for the key is longer than the existing one, replace.
    BiFunction<String, String, String> mapper = (v1, v2) -> v1.length() > v2.length() ? v1 : v2;
    favorites.merge(
        "Jenny", "Skyride", mapper); // Key exists but value Skyride is smaller - not replaced
    favorites.merge("Tom", "Skyride", mapper); // Key exists and value Skyride is bigger - replaced
    System.out.println(favorites); // {Tom=Skyride, Jenny=Bus Tour}

    System.out.println("When nulls as values or missing keys are involved merge simply uses the new value:");
    favorites.put("Sam", null);
    // Notice that the mapping function isn't called (Otherwise we had a NPE). It is only called when there are two actual values to decide between.
    favorites.merge("Sam", "A", mapper);
    favorites.merge("NewKey", "B", mapper);
    System.out.println(favorites); // {Tom=Skyride, Jenny=Bus Tour, Sam=A, NewKey=B}

    System.out.println("When the mapping function is called and returns null the key is removed from the map:");
    favorites.merge("Tom", "Skyride2", (v1, v2) -> null);
    // If the key didn't exist yet it is still being added
    favorites.merge("AnotherNewKey", "C", (v1, v2) -> null);
    // Note that Tom doesn't exist in the Map anymore
    System.out.println(favorites); // {AnotherNewKey=C, Jenny=Bus Tour, Sam=A, NewKey=B}
  }

  private static void usingComputeIfPresent() {
    System.out.println("\n--- computeIfPresent() ---");
    Map<String, Integer> counts = new HashMap<>();
    counts.put("Jenny", 2);

    BiFunction<String, Integer, Integer> mapper = (k, v) -> v + 1;
    System.out.println("When the specified key is found (and non null) it calls the BiFunction to compute a new mapping given the key and its current mapped value ");
    Integer jenny = counts.computeIfPresent("Jenny", mapper); // 2 --> Key is found so we execute the BinaryFunction and update the value accordingly
    Integer sam = counts.computeIfPresent("Sam", mapper); // null --> Key is not found so we don't do anything
    System.out.println(jenny);
    System.out.println(sam);
    System.out.println(counts); // {Jenny=3}

    System.out.println("If the mapping function returns null, the key is removed from the map");
    counts.computeIfPresent("Jenny", (k, v) -> null);
    System.out.println(counts); // {}
  }

  private static void usingComputeIfAbsent() {
    System.out.println("\n--- computeIfAbsent() ---");
    Map<String, Integer> counts = new HashMap<>();
    counts.put("Jenny", 15);
    counts.put("Tom", null);

    Function<String, Integer> mapper = k -> 1;
    System.out.println("When the specified key is absent or the value null the Function is called");
    Integer jenny = counts.computeIfAbsent("Jenny", mapper); // 15 --> key is present --> Don't change
    Integer tom = counts.computeIfAbsent("Tom", mapper); // 1 --> key is null --> Updated because the value was null
    Integer sam = counts.computeIfAbsent("Sam", mapper); // 1 --> key is absent --> Updated because the key didn't exist yet
    System.out.println(jenny);
    System.out.println(tom);
    System.out.println(sam);
    System.out.println(counts); // {Tom=1, Jenny=15, Sam=1}

    System.out.println("If the mapping function returns null, the key isn't even added to the map");
    counts.computeIfAbsent("Timmy", k -> null);
    System.out.println(counts); // {Tom=1, Jenny=15, Sam=1}
  }
}
