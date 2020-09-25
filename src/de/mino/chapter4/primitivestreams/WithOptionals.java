package de.mino.chapter4.primitivestreams;

import java.util.OptionalDouble;
import java.util.stream.IntStream;

/** Shows how to use Optional with Primitive Streams */
public class WithOptionals {

  private static void optionalDouble() {
    IntStream stream = IntStream.rangeClosed(1, 10);
    // Why does OptionalDouble exist and not just use Optional<Double>?
    // The difference is that OptionalDouble is for a primitive double and
    // Optional<Double> is for the Double wrapper class
    OptionalDouble optionalDouble = stream.average();

    // Working with a primitive Optional is similar to a regular Optional
    // ifPresent() takes a DoubleConsumer instead of Consumer
    optionalDouble.ifPresent(System.out::println);
    // getAsDouble() instead of get()
    System.out.println(optionalDouble.getAsDouble());
    // orElseGet() takes a DoubleSupplier instead of Supplier
    System.out.println(optionalDouble.orElseGet(() -> Double.NaN));
  }

  public static void main(String[] args) {
    optionalDouble();
  }
}
