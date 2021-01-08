package de.mino.chapter4.streams.intermediateoperations;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/** Shows the INTERMEDIATE OPERATION part of streams. */
public class IntermediateOperations {

  /**
   * Method signature:
   *
   * <pre>{@code
   * Stream<T> filter(Predicate<? super T> predicate);
   * }</pre>
   *
   * <p>Returns a Stream with elements that match the specified expression.
   */
  private static void filter() {
    System.out.println("Intermediate operation: filter()");
    Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
    s.filter(x -> x.startsWith("m")).forEach(System.out::print); // monkey
    System.out.println("\n");
  }

  /**
   * Method signature:
   *
   * <pre>{@code
   * Stream<T> distinct();
   * }</pre>
   *
   * <p>Returns a Stream with duplicate values removed. Java calls equals() to determine equality.
   */
  private static void distinct() {
    System.out.println("Intermediate operation: distinct()");
    Stream<String> s = Stream.of("duck", "duckling", "duck", "goose");
    s.distinct().forEach(x -> System.out.print(x + " ")); // duck duckling goose
    System.out.println("\n");
  }

  /**
   * Method signature:
   *
   * <pre>{@code
   * Stream<T> skip(long n);
   * Stream<T> limit(long maxSize);
   * }</pre>
   *
   * <p>These methods make a Stream smaller. Limit limits the stream by the specified maxSize. Skip
   * skips the first n elements.
   */
  private static void limitSkip() {
    System.out.println("Intermediate operation: limit() and skip()");
    Stream<Integer> s = Stream.iterate(1, n -> n + 1);
    s.skip(5).limit(2).forEach(System.out::print); // 67
    System.out.println("\n");
  }

  /**
   * Method signature:
   *
   * <pre>{@code
   * <R> Stream<R> map(Function<? super T, ? extends R> mapper);
   * }</pre>
   *
   * <p>Represents a one-to-one mapping from elements in the stream to elements of the next step in
   * the stream. Can also change the type of the Stream.
   */
  private static void map() {
    System.out.println("Intermediate operation: map()");
    Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
    // Convert a Stream with String elements to a Stream with Integer elements
    s.map(String::length).forEach(System.out::print); // 676
    System.out.println("\n");
  }

  /**
   * Method signature:
   *
   * <pre>{@code
   * <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);
   * }</pre>
   *
   * <p>Takes each element in the stream & makes any elements it contains top level elements in a
   * single stream --> flattens the structure
   *
   * <p>The method signature basically says it returns a Stream of the type that the function
   * contains at a lower level.
   *
   * <p>Helpful to remove empty elements from a stream or combine a stream of lists
   */
  private static void flatMap() {
    System.out.println("Intermediate operation: flatMap()");
    List<String> zero = Arrays.asList();
    List<String> one = Arrays.asList("Bonobo");
    List<String> two = Arrays.asList("Mama Gorilla", "Baby Gorilla");
    Stream<List<String>> animals = Stream.of(zero, one, two);
    // With .map the result would be Stream<Stream<String>>. With flatMap the result is a
    // Stream<String>.
    // This gets all of the animals into the same level and gets rid of the empty List.
    animals.flatMap((List<String> l) -> l.stream()).forEach(System.out::println);
    System.out.println();
  }

  /**
   * Method signature:
   *
   * <pre>{@code
   * Stream<T> sorted();
   * Stream<T> sorted(Comparator<? super T> comparator);
   * }</pre>
   *
   * <p>Returns a stream with the elements sorted by their natural ordering or ordered by the
   * specified comparator.
   */
  private static void sorted() {
    System.out.println("Intermediate operation: sorted()");

    List<String> list = Arrays.asList("brown-", "bear-");
    list.stream().sorted().forEach(System.out::print);
    System.out.println();
    list.stream().sorted(Comparator.reverseOrder()).forEach(System.out::print);

    System.out.println("\n");
  }

  /**
   * Method signature:
   *
   * <pre>{@code
   * Stream<T> peek(Consumer<? super T> action);
   * }</pre>
   *
   * <p>Useful for debugging. Often to output the contents of the stream as it goes by. Peek looks
   * at each element that goes through that part of the stream pipeline.
   */
  private static void peek() {
    System.out.println("Intermediate operation: peek()");
    Stream<String> stream = Stream.of("black bear", "brown bear", "grizzle");
    stream.filter(s -> s.startsWith("g")).peek(System.out::print).count();
    System.out.println();
  }

  public static void main(String[] args) {
    filter();

    distinct();

    limitSkip();

    map();

    flatMap();

    sorted();

    peek();
  }
}
