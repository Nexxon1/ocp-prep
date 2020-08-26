package de.mino;

import java.util.Optional;

public class MOptional {

    public static void main(String[] args) {
        Optional<Integer> o1 = Optional.of(95);
        Optional<Integer> o2 = Optional.empty();
        System.out.println(o1.orElseGet(() -> 1));
        System.out.println(o2.orElseGet(() -> 1));
        System.out.println(o2.orElse(3));


        Optional<Double> opt1 = Optional.of(95.5);
        if (opt1.isPresent())
            System.out.println(opt1.get());
        // The same but more readable. ifPresent() requires a Consumer to be passed.
        opt1.ifPresent(System.out::println);
        opt1.ifPresent((x) -> System.out.println(x));


        Optional<Double> opt = Optional.empty();
        System.out.println(opt.orElse(Double.NaN));
        System.out.println(opt.orElseGet(() -> Math.random()));
        System.out.println(opt.orElseThrow(() -> new IllegalStateException()));

    }
}
