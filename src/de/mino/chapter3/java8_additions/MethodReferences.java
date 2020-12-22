package de.mino.chapter3.java8_additions;

import de.mino.chapter3.comparison.comparator.Duck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class MethodReferences {

  public static void main(String[] args) {
    // Option 1 to write a Comparator
    Comparator<Duck> byWeight1 = (d1, d2) -> MethodReferences.compareByWeight(d1, d2);

    // Option 2 to write a Comparator - shorter via Method reference
    // The :: operator tells Java to pass the parameters automatically into the method
    Comparator<Duck> byWeight2 = MethodReferences::compareByWeight;
    // Note that it returns a functional interface and not an int. The '::' is like lambdas, and is
    // typically used for deferred (aufgeschobene) execution

    System.out.println("The 4 formats for method references");
  }

  public static int compareByWeight(Duck d1, Duck d2) {
    return d1.getWeight() - d2.getWeight();
  }

  public static int compareByName(Duck d1, Duck d2) {
    return d1.getName().compareTo(d2.getName());
  }

  private static void staticMethods() {
    // A Consumer takes 1 parameter and has void as return type
    Consumer<List<Integer>> lambda = l -> Collections.sort(l);
    Consumer<List<Integer>> methodRef = Collections::sort;
  }

  private static void instanceMethodOnParticularInstance() {
    String str = "abc";
    // A Predicate takes 1 parameter and returns a boolean
    Predicate<String> lambda = s -> str.startsWith(s);
    Predicate<String> methodRef = str::startsWith;
  }

  private static void instanceMethodWithoutKnowingTheInstance() {
    Predicate<String> lambda = s -> s.isEmpty();
    // The method that we want to call is declared in String. It looks like a static method but it
    // isn't.
    // Instead, Java knows that isEmpty is an instance method that does not take any parameters
    // Java uses the parameter supplied at runtime as the instance on which the method is called.
    Predicate<String> methodRef = String::isEmpty;
  }

  private static void constructorReference() {
    // A Supplier takes no parameter and returns any type
    Supplier<ArrayList<String>> lambda = () -> new ArrayList<>();
    // The constructor reference is a special type of method reference that uses new instead of a
    // method and creates a new object
    Supplier<ArrayList<String>> methodRef = ArrayList::new;
  }
}
