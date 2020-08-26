package de.mino.chapter4.streams;

import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class UsingStreams {

    public static void main(String[] args) {
        Stream<Integer> empty = Stream.empty();
        Stream<Integer> singleElem = Stream.of(1);
        Stream<Integer> fromArr = Stream.of(1, 2, 3);

        System.out.println(fromArr.count());

        Stream<Double> randoms = Stream.generate(Math::random);
        Stream<Integer> oddNumbers = Stream.iterate(1, n -> n + 2);

        Stream<String> s = Stream.of("monkey", "ape", "bonobo");
        Optional<String> min = s.min((s1, s2) -> {
            int x = s1.length();
            int y = s2.length();
            return x - y;
        });
        min.ifPresent(System.out::println); // ape
        Optional<?> minEmpty = Stream.empty().min((s1, s2) -> 0);
        System.out.println(minEmpty.isPresent()); // false


        Stream<String> s2 = Stream.of("monkey", "ape", "bonobo");
        Stream<String> infinite = Stream.generate(() -> "chimp");
        // Note: If we would take the stream from above which we already processed we would get an
        // java.lang.IllegalStateException: stream has already been operated upon or closed
        s2.findAny().ifPresent(System.out::println); // monkey
        infinite.findAny().ifPresent(System.out::println); // chimp


        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
        int multiply = stream.reduce(1, (a, b) -> a * b);
        System.out.println(multiply); // 120

        BinaryOperator<Integer> op = (a, b) -> a * b;
        BinaryOperator<Integer> op2 = (a, b) -> a * 56;
        Stream<Integer> iStream = Stream.of(3, 5, 6);
        System.out.println(iStream.reduce(1, op, op2)); // 90

    }
}
