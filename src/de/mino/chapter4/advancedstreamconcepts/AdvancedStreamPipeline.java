package de.mino.chapter4.advancedstreamconcepts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class AdvancedStreamPipeline {

  public static void main(String[] args) throws IOException {

    lazyStreamEvaluation();

    System.out.println();
    chainingOptionals(Optional.of(123));
    chainingOptionals(Optional.of(1));
    chainingOptionals(Optional.empty());

    optionalAndFlatMap();

    checkedExceptionsAndFuncInterfaces();
  }

  private static void lazyStreamEvaluation() {
    System.out.println("Lazy Stream evaluation");
    List<String> cats = new ArrayList<>();
    cats.add("Annie");
    cats.add("Ripley");
    // At this moment of time the ArrayList contains 2 elements.
    Stream<String> stream = cats.stream();
    cats.add("KC");
    System.out.println(stream.count()); // 3

    // Explanation:
    // Streams are lazily evaluated. The stream isn't actually created when calling cats.stream().
    // An object is created that knows where to look for the data when it is needed. Then we add a
    // new element to the list. Finally we call count() so the stream pipeline actually runs. First
    // it looks at the source, which is the ArrayList and sees that it contains 3 elements.
  }

  /** A few intermediate operations for streams are also available for Optional. */
  private static void chainingOptionals(Optional<Integer> maybeThreeDigit) {
    System.out.println("Chaining Optionals");

    // Code to print the value of an Optional<Integer> if it contains a three digit number.

    // Suppose we pass an Optional.of(123):
    // map() maps the number 123 to the String "123". The filter() method returns that
    // Optional.of("123") because we match the predicate and ifPresent() calls the Consumer
    // parameter because the Optional contains a value.

    // Suppose we pass an Optional.of(1):
    // map() maps the number 1 to the String "1". The filter() Method returns an empty Optional
    // since the filter doesn't match and ifPresent() sees an empty Optional so it doesn't call the
    // Consumer parameter.

    // Suppose we pass an empty Optional:
    // It gets passed through map, returning an empty Optional and through filter, again returning
    // an empty Optional. Then ifPresent() sees an empty Optional and doesn't call the Consumer
    // parameter.
    maybeThreeDigit
        .map(threeDigit -> threeDigit.toString())
        .filter(threeDigitStr -> threeDigitStr.length() == 3)
        .ifPresent(System.out::println);
  }

  private static void optionalAndFlatMap() {
    System.out.println("\n" + "Optional and flatMap");
    // Does not compile with map because it would result in an Optional<Optional<Integer>>
    // Optional<Integer> res = Optional.of("1234").map(AdvancedStreamPipeline::calculator);

    Optional<Integer> res = Optional.of("1234").flatMap(AdvancedStreamPipeline::calculator);
    System.out.println(res);
  }

  /** @return Returns the length of the passed String */
  private static Optional<Integer> calculator(String s) {
    Optional<Integer> res = Optional.ofNullable(s).map(String::length);
    return res;
  }

  /**
   * Functional interfaces do not declare checked exceptions.
   *
   * <p>(Checked Exceptions include Exception and its subclasses that do not extend
   * RuntimeException. They need to be handled or declared in the method signature)
   *
   * <p>That results in a problem when working with methods that declare checked exceptions.
   */
  private static void checkedExceptionsAndFuncInterfaces() throws IOException {
    System.out.println("\n" + "Checked Exceptions and Functional Interfaces");
    iThrowACheckedException().stream().count();

    // Doesn't compile. The Supplier interface doesn't allow checked exception:
    // Supplier<List<String>> s = AdvancedStreamPipeline::iThrowACheckedException;

    // Option 1: Catch the exception & turn it into an unchecked exception. Problem: ugly code:
    Supplier<List<String>> s2 =
        () -> {
          try {
            return iThrowACheckedException();
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        };

    // Option 2: Create a wrapper Method that handles the try/catch
    Supplier<List<String>> s3 = AdvancedStreamPipeline::iHandleTheException;
  }

  private static List<String> iThrowACheckedException() throws IOException {
    throw new IOException();
  }

  private static List<String> iHandleTheException() {
    try {
      return iThrowACheckedException();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
