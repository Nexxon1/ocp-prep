package de.mino.chapter4.primitivestreams;

import java.util.IntSummaryStatistics;
import java.util.OptionalDouble;
import java.util.function.BooleanSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Shows examples with primitive Streams
 *
 * <p>We can use wrapper classes when we need primitives to go into streams (We did this with the
 * Collections API).
 *
 * <p>However, with streams we have an easier way
 */
public class MIntStream {

  public static void main(String[] args) {
    sum();

    average();

    range();

    mapToIntStream();

    summaryStatistics();

    booleanSupplier();
  }

  /**
   * Method signature:
   *
   * <pre>{@code
   * int sum();
   * }</pre>
   *
   * <p>Terminal operation (special case of reduction) that returns the sum of elements in this
   * stream.
   */
  private static void sum() {
    System.out.println("IntStream - Calculating the sum");
    // With wrapper type:
    Stream<Integer> stream1 = Stream.of(1, 2, 3);
    System.out.println(stream1.reduce(0, (s, n) -> s + n));

    // Easier: convert the Stream<Integer> to an IntStream
    Stream<Integer> stream2 = Stream.of(1, 2, 3);
    System.out.println(stream2.mapToInt(x -> x).sum());

    // Or just use a primitive Stream (IntStream) in the first place
    IntStream stream3 = IntStream.of(1, 2, 3);
    System.out.println(stream3.sum());
    System.out.println();
  }

  /**
   * Method signature:
   *
   * <pre>{@code
   * OptionalDouble average();
   * }</pre>
   *
   * <p>Terminal operation (special case of reduction) that returns the average of elements in this
   * stream or an empty optional if the stream is empty.
   */
  private static void average() {
    System.out.println("IntStream - Calculating the avg");
    IntStream stream4 = IntStream.of(1, 2, 3);
    OptionalDouble avg = stream4.average();
    System.out.println(avg.getAsDouble());
    System.out.println();
  }

  /**
   * Method signature:
   *
   * <pre>{@code
   * IntStream range(int startInclusive, int endExclusive);
   * IntStream rangeClosed(int startInclusive, int endInclusive);
   * }</pre>
   *
   * <p>Returns a sequential {@code IntStream} for the specified range of {@code int} elements
   */
  private static void range() {
    System.out.println("IntStream - Range of numbers");
    IntStream range = IntStream.range(1, 6);
    range.forEach(System.out::print);
    System.out.println();

    IntStream rangeClosed = IntStream.rangeClosed(1, 6);
    rangeClosed.forEach(System.out::print);
    System.out.println("\n");
  }

  /**
   * Method signature:
   *
   * <pre>{@code
   * IntStream mapToInt(ToIntFunction<? super T> mapper);
   * }</pre>
   *
   * <p>Maps {@code Stream} --> {@code IntStream}
   *
   * <p>ToIntFunction maps the elements of the Stream to an int. Method signature:
   *
   * <pre>{@code
   * int applyAsInt(T value);
   * }</pre>
   */
  private static void mapToIntStream() {
    System.out.println("IntStream - Mapping");
    Stream<String> objStream = Stream.of("penguin", "fish");
    // mapToInt() Takes a generic data type that specifies the values in the Stream, which is String
    // in this case and returns an int.
    // Mapping functions for the different types of Streams (Stream, IntStream, DoubleStream,
    // LongStream) are intuitive. They take the source type and return the target type.
    IntStream intStream = objStream.mapToInt(String::length);
    intStream.forEach(System.out::println);
    System.out.println();
  }

  /**
   * Method signature:
   *
   * <pre>{@code
   * IntSummaryStatistics summaryStatistics();
   * }</pre>
   *
   * <p>Terminal operation (special case of reduction) that returns an {@code IntSummaryStatistics}
   * that describes the count, sum, min, max and avg of the elements in the stream.
   */
  private static void summaryStatistics() {
    System.out.println("IntStream - Summarizing statistics (count, sum, min, max, avg)");
    IntStream stream = IntStream.rangeClosed(1, 3);
    IntSummaryStatistics stats = stream.summaryStatistics();
    System.out.println(stats + "\n");
  }

  /**
   * Method signature:
   *
   * <pre>{@code
   * boolean getAsBoolean();
   * }</pre>
   *
   * {@code BooleanSupplier} is one of the Functional Interfaces for primitive Streams. It takes no
   * argument and returns a boolean. There is a set of Functional Interfaces for primitive streams:
   *
   * <ul>
   *   <li>IntSupplier
   *   <li>IntConsumer
   *   <li>IntPredicate
   *   <li>IntFunction
   *   <li>IntUnaryOperator
   *   <li>IntBinaryOperator
   *   <li>IntSupplier
   *   <li>Same for Long and Double Streams
   * </ul>
   */
  private static void booleanSupplier() {
    System.out.println("Func Interface BooleanSupplier");
    BooleanSupplier b1 = () -> true;
    BooleanSupplier b2 = () -> Math.random() > 0.5;
    System.out.println(b1.getAsBoolean());
    System.out.println(b2.getAsBoolean());
  }
}
