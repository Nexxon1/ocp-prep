package de.mino.chapter4.streams.source;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Shows the SOURCE part of streams.
 */
public class StreamCreator {

    public static void main(String[] args) {
        // Creating FINITE STREAMS
        Stream<Integer> empty = Stream.empty();
        Stream<Integer> singleElem = Stream.of(1);
        Stream<Integer> fromArr = Stream.of(1, 2, 3);

        List<String> myList = Arrays.asList("Aa", "Bb", "Cc");
        Stream<String> streamFromList = myList.stream();
        // Parallel Streams can execute intermediate operations in parallel.
        // However this comes with an overhead. Sequential streams are faster for small streams.
        Stream<String> parallelStreamFromList = myList.parallelStream();


        // Creating INFINITE STREAMS
        System.out.println("Creating infinite Streams");
        // This creates a stream of random numbers. If you would call the terminal operation
        // forEach(System.out::println) the program would keep printing random numbers until you kill it
        Stream<Double> randoms = Stream.generate(Math::random);

        // The first parameter is the seed or starting value
        // The second parameter is a UnaryOperator that gets the previous value passed and creates the next value
        Stream<Integer> oddNumbers = Stream.iterate(1, n -> n + 2);
    }
}
