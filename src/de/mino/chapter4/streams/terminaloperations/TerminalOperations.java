package de.mino.chapter4.streams.terminaloperations;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Shows the TERMINAL OPERATION part of streams. Reductions are a special type of terminal operation
 * where all of the contents of the stream are combined into a single primitive or Object.
 */
public class TerminalOperations {

  /**
   * Method signature:
   *
   * <pre>{@code
   * long count();
   * }</pre>
   *
   * <p>Returns the number of elements in the stream. Is a reduction.
   */
  private static void count() {
    System.out.println("Terminal operation: count()");
    Stream<Integer> fromArr = Stream.of(1, 2, 3);
    System.out.println(fromArr.count());
    System.out.println();
  }

  /**
   * Method signatures:
   *
   * <pre>{@code
   * Optional<T> min(Comparator <? super T> comparator);
   * Optional<T> max(Comparator <? super T> comparator);
   * }</pre>
   *
   * <p>Pass a custom comparator. Returns an Optional to specify that no min/max value was found.
   * Hangs for infinite streams. Is a reduction.
   */
  private static void minMax() {
    System.out.println("Terminal operation: min() and max()");
    Stream<String> s = Stream.of("monkey", "ape", "bonobo");
    Comparator<String> comparator = (s1, s2) -> s1.length() - s2.length();

    Optional<String> min = s.min(comparator);
    min.ifPresent(System.out::println); // ape
    Optional<?> minEmpty = Stream.empty().min((s1, s2) -> 0);
    System.out.println(minEmpty.isPresent()); // false

    // We need a new stream because the first one has already be operated upon
    Stream<String> s2 = Stream.of("monkey", "ape", "bonobo");
    Optional<String> max = s2.max(comparator);
    max.ifPresent(System.out::println); // monkey
    System.out.println();
  }

  /**
   * Method signatures:
   *
   * <pre>{@code
   * Optional<T> findAny();
   * Optional<T> findFirst();
   * }</pre>
   *
   * <p>Returns an empty Optional if the Stream was empty. Not a reduction (because it doesn't
   * necessarily look at all elements of the stream) Works for infinite streams. findAny() makes
   * especially sense for parallel streams to just get a representative element.
   */
  private static void findAnyFirst() {
    System.out.println("Terminal operation: findAny() and findFirst()");
    Stream<String> s = Stream.of("monkey", "ape", "bonobo");
    s.findAny().ifPresent(System.out::println); // monkey

    Stream<String> infinite = Stream.generate(() -> "chimp");
    // Note: If we would take the stream from above which we already processed we would get an
    // java.lang.IllegalStateException: stream has already been operated upon or closed
    infinite.findAny().ifPresent(System.out::println); // chimp
    System.out.println();
  }

  /**
   * Method signatures:
   *
   * <pre>{@code
   * boolean allMatch(Predicate<? super T> predicate);
   * boolean anyMatch(Predicate<? super T> predicate);
   * boolean noneMatch(Predicate<? super T> predicate);
   * }</pre>
   *
   * <p>May or may not terminate on infinite streams. Not a reduction.
   */
  private static void allAnyNoneMatch() {
    System.out.println("Terminal operation: allMatch(), anyMatch() and noneMatch()");
    List<String> list = Arrays.asList("monkey", "2", "chimp");
    Predicate<String> pred = x -> Character.isLetter(x.charAt(0));

    System.out.println(list.stream().allMatch(pred)); // false
    System.out.println(list.stream().anyMatch(pred)); // true
    System.out.println(list.stream().noneMatch(pred)); // false

    Stream<String> infiniteStream = Stream.generate(() -> "chirp");
    System.out.println(infiniteStream.anyMatch(pred)); // true
    System.out.println();
  }

  /**
   * Method signature:
   *
   * <pre>{@code
   * void forEach(Consumer<? super T> action);
   * }</pre>
   *
   * <p>Does not terminate on infinite streams. Not a reduction.
   */
  private static void forEach() {
    System.out.println("Terminal operation: forEach()");
    Stream<String> s = Stream.of("Monkey", "Gorilla", "Bonbon");
    s.forEach(System.out::println);
    System.out.println();
  }

  /**
   * Method signatures:
   *
   * <pre>{@code
   * T reduce(T identity, BinaryOperator<T> accumulator)
   * Optional<T> reduce(BinaryOperator<T> accumulator)
   * <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner)
   * }</pre>
   *
   * <p>The reduce method combines a stream into a single object. Is a reduction.
   *
   * <ul>
   *   <li>When an identity is defined: Start with the initial value (identity) and keep merging it
   *       with the next element/ value of the stream. The accumulator combines the current value
   *       (starting with the identity) with the elements in the stream.
   *   <li>When no identity is defined: Optional is returned because there might not be any data.
   *       <ul>
   *         <li>Stream empty --> Return an empty optional
   *         <li>Stream has 1 elem --> Return that element
   *         <li>Stream has multiple elements --> Use the accumulator to combine them.
   *       </ul>
   *   <li>The third method signature is used when processing collections in parallel. It allows us
   *       to create intermediate reductions and combine them at the end.
   * </ul>
   */
  private static void reduce() {
    System.out.println("Terminal operation: reduce()");
    Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
    int multiply = stream.reduce(1, (a, b) -> a * b);
    System.out.println(multiply); // 120

    List<Integer> iList = Arrays.asList(3, 5, 6);
    BinaryOperator<Integer> op = (a, b) -> a * b;
    System.out.println(iList.stream().reduce(1, op));

    // Not the best example.. We are not using a parallel stream here.
    System.out.println(iList.stream().reduce(1, op, op)); // 90
    System.out.println();
  }

  /**
   * Method signatures:
   *
   * <pre>{@code
   * <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner);
   * <R, A> R collect(Collector<? super T, A, R> collector);
   * }</pre>
   *
   * <p>Mutable reduction. It is more efficient than a regular reduction because we use the same
   * mutable object while accumulating. Common mutable objects include StringBuilder and ArrayList.
   *
   * <ul>
   *   <li>First param: Supplier that creates the object that will store the results as we collect
   *       data.
   *   <li>Second parameter: BiConsumer, adds one more element to the data collection. (Here we
   *       append the next String to the StringBuilder)
   *   <li>Third parameter: BiConsumer which is responsible for taking two data collections and
   *       merges them. Useful when processing in parallel.
   * </ul>
   */
  private static void collect() {
    System.out.println("Terminal operation: collect()");
    List<String> list = Arrays.asList("w", "o", "l", "f");
    StringBuilder word =
        list.stream().collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
    System.out.println(word); // wolf
    TreeSet<String> set = list.stream().collect(TreeSet::new, TreeSet::add, TreeSet::addAll);
    System.out.println(set); // [f, l, o, w]

    // We can use the second implementation as well because Java provides an Interface with common
    // Collectors
    TreeSet<String> set2 = list.stream().collect(Collectors.toCollection(TreeSet::new));
    System.out.println(set2); // [f, l, o, w]
    Set<String> set3 = list.stream().collect(Collectors.toSet());
    System.out.println(
        set3); // [f, w, l, o] (output might be different, we are just likely to get a HashSet)
    System.out.println();

    // For an overview of predefined collectors see 'chapter4.advancedstreamconcepts.CollectingResults'
  }

  public static void main(String[] args) {
    count();

    minMax();

    findAnyFirst();

    allAnyNoneMatch();

    forEach();

    reduce();

    collect();
  }
}
