package de.mino.chapter3.generics.bounds.examples;

import java.util.ArrayList;
import java.util.List;

public class Examples {

  public static void main(String[] args) {
    // ArrayList that can hold instances of class A. It is stored in a variable with an unbounded
    // wildcard. Any generic types can be references from an unbounded wildcard.
    List<?> list1 = new ArrayList<A>();

    // Storing a list in a variable declaration with an upper-bounded wildcard. We could store an
    // ArrayList<A>, <B> or <C> in that variable.
    List<? extends A> list2 = new ArrayList<A>();

    // Here we use a lower-bounded wildcard. The lowest type we can reference is A so this is fine.
    List<? super A> list3 = new ArrayList<A>();

    // Does not compile. That upper-bound wildcard allows ArrayList<B> or <C> to be referenced.
    // List<? extends B> list4 = new ArrayList<A>();

    // The lower-bounded wildcard allows a reference of ArrayList<A>, <B> or <Object> to be stored
    // in that variable.
    List<? super B> list5 = new ArrayList<A>();

    // Here we have a reference to a generic type. When instantiating the ArrayList you need to
    // specify the type (or leave it empty) to compile. It also wouldn't make sense because the
    // upper bound wildcard makes the ArrayList practically immutable so we can't add elements.
    // List<?> list6 = new ArrayList<? extends A>();
  }

  /**
   * Valid. Method specific type parameter T. Return type T. Parameter of {@code List<T>} or some
   * subclass of T and return a single object of that T type. (e.g. calling it with a {@code
   * List<Number>} would return a Number.
   */
  <T> T method1(List<? extends T> list) {
    return list.get(0);
  }

  // Does not compile --> The return type here isn't actually a type.
  /*
  <T> <? extends T> method2(List<? extends T> list) {
    return list.get(0);
  }
   */

  /**
   * {@code <B extends A>} says you want to use B as type parameter just for this method and that it
   * needs to extend the A class. This is tricky because the type parameter B could be confused with
   * the class B. As a result the B parameter can represent the classes A, B or C. Since B no longer
   * refers to the B class in the method, you cant instantiated it with {@code new B();}
   */
  <B extends A> B method3(List<B> list) {
    // return new B();
    return list.get(0);
  }

  /** You can pass types {@code List<B>, List<A> or List<Object>} */
  void method4(List<? super B> list) {}

  // This method is trying to mix a method specific type parameter {@code <X>} with a wildcard. A
  // wildcard must have an ? in it. We would need to replace the X in the parameter with a ? to make
  // it compile.
  // <X> void method5(List<X super B> list) {}
}

class A {}

class B extends A {}

class C extends B {}
