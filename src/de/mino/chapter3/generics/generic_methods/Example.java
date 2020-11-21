package de.mino.chapter3.generics.generic_methods;

import de.mino.chapter3.generics.custom_class.Crate;

public class Example {

  // We have to specify the formal parameter type <T> before the return type.
  public static <T> Crate<T> ship(T t) {
    return new Crate<T>();
  }
}

class Example2<T> {

  // We don't have to specify the formal parameter type in methods when it can obtain it from the
  // class/ interface. Note that that's only possible if the method is not static.<
  public Crate<T> ship(T t) {
    return new Crate<T>();
  }
}
