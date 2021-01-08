package de.mino.chapter4.advancedstreamconcepts;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectingResults {

  public static void main(String[] args) {

    basicCollectors();

    moreBasicCollectors();

    collectingIntoMaps();

    collectingUsingGroupingPartitioningAndMapping();
  }

  private static void basicCollectors() {
    System.out.println("--- Collecting using basic Collectors ---");
    System.out.println("Concatenate Strings together");
    Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
    String result = ohMy.collect(Collectors.joining(", "));
    System.out.println(result); // lions, tigers, bears

    System.out.println("\n" + "Average length of the Strings in the Stream");
    Stream<String> ohMy2 = Stream.of("lions", "tigers", "bears");
    // averagingInt() expects a ToIntFunction which returns an int for each element in this stream.
    // In this case the length.
    Double result2 = ohMy2.collect(Collectors.averagingInt(String::length));
    System.out.println(result2); // 5.333333333333333

    System.out.println(
        "\n"
            + "Convert a Stream to a collection. (Convenient when interacting with code written before Java8)");
    Stream<String> ohMy3 = Stream.of("lions", "tigers", "bears");
    TreeSet<String> result3 =
        ohMy3.filter(s -> s.startsWith("t")).collect(Collectors.toCollection(TreeSet::new));
    System.out.println(result3); // [tigers]
  }

  private static void moreBasicCollectors() {
    List<String> ohMy = Arrays.asList("lions", "tigers", "bears");

    // Own examples - Not from the OCP book. This is just to cover all the predefined Collectors
    System.out.println("\nCounting the elements in a Stream");
    Long collect = ohMy.stream().collect(Collectors.counting());
    System.out.println(collect); // 3

    System.out.println("\nFinding the min/max by Comparator");
    Optional<String> maxLengthAnimal =
        ohMy.stream().collect(Collectors.maxBy((o1, o2) -> o1.length() - o2.length()));
    System.out.println("Max length: " + maxLengthAnimal.get()); // tigers
    Optional<String> minLengthAnimal =
        ohMy.stream().collect(Collectors.minBy((o1, o2) -> o1.length() - o2.length()));
    System.out.println("Min length: " + minLengthAnimal.get()); // lions

    System.out.println("\nSummingInt Collector - Sum of the length of the Strings in the Stream");
    Integer lengthSum = ohMy.stream().collect(Collectors.summingInt(String::length));
    System.out.println(lengthSum); // 16

    System.out.println(
        "\nSummarizingInt Collector - Summary on the length of the Strings in the stream (count, sum, min, avg, max)");
    IntSummaryStatistics intSummaryStatistics =
        ohMy.stream().collect(Collectors.summarizingInt(String::length));
    System.out.println(
        intSummaryStatistics); // IntSummaryStatistics{count=3, sum=16, min=5, average=5,333333,
    // max=6}

    System.out.println("\ntoSet Collector");
    List<String> ohMy2 = Arrays.asList("lions", "tigers", "bears", "tigers");
    System.out.println(ohMy2); // [lions, tigers, bears, tigers]
    Set<String> ohMy2Set = ohMy2.stream().collect(Collectors.toSet());
    System.out.println(ohMy2Set); // [lions, bears, tigers]
  }

  private static void collectingIntoMaps() {
    System.out.println("\n" + "--- Collecting into Maps ---");
    System.out.println("Creating a Map for a Stream - key=Name, value=Length");
    Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
    // We have to specify two Functions as parameter.
    // The first tells the collector how to create the key (Here we just use the provided String)
    // The second tells the collector how to create the value (Here we use the length of the String)
    Map<String, Integer> map = ohMy.collect(Collectors.toMap(s -> s, String::length));
    // Synonym (Function.identity() just returns the same value as passed):
    // Map<String, Integer> map = ohMy.collect(Collectors.toMap(Function.identity(),
    // String::length));
    System.out.println(map); // {lions=5, bears=5, tigers=6}

    System.out.println("\n" + "Reverse - key=Length, value=Name. Beware with duplicate keys!");
    Stream<String> ohMy2 = Stream.of("lions", "tigers", "bears");
    // IllegalStateException - Duplicate keys.
    // Map<Integer, String> map2 = ohMy2.collect(Collectors.toMap(String::length, s -> s));
    // 1 Solution: Concatenate the values if the key is the same
    // The third parameter here is a BinaryOperator which serves the purpose of a merge function,
    // used to resolve collisions between values associated with the same key.
    Map<Integer, String> map2 =
        ohMy2.collect(Collectors.toMap(String::length, k -> k, (s1, s2) -> s1 + "," + s2));
    System.out.println(map2); // {5=lions,bears, 6=tigers}
    System.out.println(map2.getClass());

    // With the example above we can't guarantee which type of Map gets returned. We can solve this:
    Stream<String> ohMy3 = Stream.of("lions", "tigers", "bears");
    TreeMap<Integer, String> map3 =
        ohMy3.collect(
            Collectors.toMap(String::length, k -> k, (s1, s2) -> s1 + "," + s2, TreeMap::new));
    System.out.println(map3.getClass()); // TreeMap
  }

  private static void collectingUsingGroupingPartitioningAndMapping() {
    System.out.println("\n" + "--- Collecting using Grouping, Partitioning and Mapping ---");

    System.out.println("GROUPING a Stream of names by their length");
    Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
    // groupingBy() groups all of the elements of the stream into lists, organizing them by the
    // function provided.
    // Produces a Map<K, List<T>>. The keys are determined by the Function we pass. The
    // corresponding values are a List containing the elements of the Stream that match to the
    // associated key.
    Map<Integer, List<String>> map = ohMy.collect(Collectors.groupingBy(String::length));
    System.out.println(map); // {5=[lions, bears], 6=[tigers]}
    // It is possible to change the type of Map returned and the Type of Collection that gets used
    // to store the values:
    Stream<String> ohMy2 = Stream.of("lions", "tigers", "bears", "lions");
    TreeMap<Integer, Set<String>> map2 =
        ohMy2.collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.toSet()));
    // Note that the duplicate "lions" entry of the Stream gets filtered because the we have a Set as value of the Map
    System.out.println(map2); // {5=[lions, bears], 6=[tigers]}

    // We could also use the downstream Collector from groupingBy() to see how many elements we have in each group:
    Stream<String> ohMy3 = Stream.of("lions", "tigers", "bears");
    Map<Integer, Long> map3 =
        ohMy3.collect(Collectors.groupingBy(String::length, Collectors.counting()));
    System.out.println(map3); // {5=2, 6=1}

    System.out.println(
        "\nPARTITIONING is a special case of grouping. There are only two possible groups (true and false). We basically split a list into 2 parts.");
    // Use case: We have signs to put outside each animal's exhibit. We got two sizes of signs.
    // First size for animal names with less than 5 characters and the second size for animal names
    // with more than 5 characters. We pass a Predicate with the logic for grouping the elements.
    Stream<String> ohMy4 = Stream.of("lions", "tigers", "bears");
    Map<Boolean, List<String>> map4 =
        ohMy4.collect(Collectors.partitioningBy(s -> s.length() <= 5));
    System.out.println(map4); // {false=[tigers], true=[lions, bears]}

    // As with groupingBy() we can also change the type of the List:
    Stream<String> ohMy5 = Stream.of("lions", "tigers", "bears", "lions");
    Map<Boolean, Set<String>> map5 =
        ohMy5.collect(Collectors.partitioningBy(s -> s.length() <= 5, Collectors.toSet()));
    System.out.println(map5); // {false=[tigers], true=[lions, bears]}

    // Fixme
    /*
    System.out.println(
        "MAPPING lets us go down a level and add another collector. "
            + "The specified mapper (Function) will be applied to the input elements. "
            + "The second parameter is another (downstream) Collector that will accept the mapped values");
    // Use case: We want the first letter of the first animal alphabetically of each length.
    Stream<String> ohMy6 = Stream.of("lions", "tigers", "bears");
    // Collectors.mapping() takes 2 params - First the Function for the value and second how to
    // group it further
    Map<?, ?> map6 =
        ohMy6.collect(
            Collectors.groupingBy(
                String::length,
                Collectors.mapping(s -> s.charAt(0), Collectors.minBy(Comparator.naturalOrder()))));
    System.out.println(map6); // {5=Optional[b], 6=Optional[t]}
    */

  }
}
