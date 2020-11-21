package de.mino.chapter3.generics.legacy;

import java.util.ArrayList;
import java.util.List;

public class LegacyCode {}

class Dragon {}

class Unicorn {}

class LegacyDragon {
  public static void main(String[] args) {
    List unicorns = new ArrayList();
    unicorns.add(new Unicorn());
    // We can still pass the unicorns List because 'Object' is a superclass of Dragon
    // However, this method actually only expects Dragons, so this will result in a
    // ClassCastException at runtime
    // Note that there is no explicit cast in the code, with generic types, Java writes the casts
    // for us. More on that in the ReadMe.
    printDragons(unicorns); // ClassCastException
  }

  private static void printDragons(List<Dragon> dragons) {
    for (Dragon dragon : dragons) {
      System.out.println(dragon);
    }
  }
}

class LegacyUnicorns {
  public static void main(String[] args) {
    List<Unicorn> unicorns = new ArrayList<>();
    addUnicorns(unicorns);
    Unicorn unicorn =
        unicorns.get(
            0); // ClassCastException - we added a dragon.. And we cant cast that Dragon to an
                // Unicorn
  }

  // No formal type parameter specified. We are actually adding a Dragon.
  private static void addUnicorns(List unicorns) {
    unicorns.add(new Dragon());
  }
}

class LegacyAutoboxing {
  public static void main(String[] args) {
    List numbers = new ArrayList();
    numbers.add(5); // Java autoboxes the int to Integer
    // Java doesn't know that the list contains an Integer, it just knows we have an Object and an
    // Object can't be unboxed to an int.
    // int result = numbers.get(0); // Does not compile
  }
}
