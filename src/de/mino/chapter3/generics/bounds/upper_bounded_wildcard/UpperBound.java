package de.mino.chapter3.generics.bounds.upper_bounded_wildcard;

import java.util.ArrayList;
import java.util.List;

public class UpperBound {

  // ArrayList<Number> list = new ArrayList<Integer>(); // Does not compile

  // Instead use the upper-bounded wildcard --> Any class that extends Number or Number itself can
  // be used as the formal type parameter
  ArrayList<? extends Number> list = new ArrayList<Integer>();

  public static long total(List<? extends Number> list) {
    long count = 0;
    for (Number number : list) {
      count += number.longValue();
    }
    return count;
  }

  public static void main(String[] args) {
    System.out.println("Lists with upper bound or unbounded wildcards result in logically immutable Lists");
    // When working with upper bound or unbounded wildcards in Lists, the List becomes logically
    // immutable. (Technically you can still remove elements but adding becomes impossible).
    List<? extends Number> numbers = new ArrayList<Integer>();
    // numbers.add(new Integer(42)); // Does not compile

    System.out.println("Demonstrating the Advantage of Upper Bounds");
    List<Flyer> flyers = new ArrayList<>();
    List<HangGlider> hangGliders = new ArrayList<>();
    anyFlyer(flyers);
    // anyFlyer(hangGliders); // Does not compile
    groupOfFlyers(flyers);
    groupOfFlyers(hangGliders);
  }

  private static void anyFlyer(List<Flyer> flyers) {}

  private static void groupOfFlyers(List<? extends Flyer> flyers) {}
}

interface Flyer {
  void fly();
}

class HangGlider implements Flyer {
  @Override
  public void fly() {}
}
