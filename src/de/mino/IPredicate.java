package de.mino;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class IPredicate {

    public static void main(String[] args) {
        Predicate<String> p1 = String::isEmpty;
        Predicate<String> p2 = x -> x.isEmpty();

        System.out.println(p1.test(""));
        System.out.println(p2.test(""));


        BiPredicate<String, String> b1 = String::startsWith;
        BiPredicate<String, String> b2 = (string, prefix) -> string.startsWith(prefix);

        System.out.println(b1.test("chicken", "chick"));
        System.out.println(b2.test("chicken", "chick"));


        System.out.println("More complex predicate stuff");
        Predicate<String> egg = s -> s.contains("egg");
        Predicate<String> brown = s -> s.contains("brown");
        Predicate<String> brownEggs = s -> s.contains("egg") && s.contains("brown");
        Predicate<String> otherEggs = s -> s.contains("egg") && ! s.contains("brown");

        System.out.println(brownEggs.test("i have brown eggs so this is true"));
        System.out.println(brownEggs.test("i have other eggs so this is false"));

        System.out.println("Using the built in default methods");
        Predicate<String> brownEggs2 = egg.and(brown);
        Predicate<String> otherEggs2 = egg.and(brown.negate());
        System.out.println(brownEggs2.test("i have brown eggs so this is true"));
        System.out.println(brownEggs2.test("i have other eggs so this is false"));

    }

}
