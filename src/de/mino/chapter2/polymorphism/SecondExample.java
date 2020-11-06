package de.mino.chapter2.polymorphism;

public class SecondExample {
  /**
   * Polymorphism allows one object to take on many different forms. A Java object may be accessed
   * using a reference with the same type as the object, a reference that is a superclass of the
   * object, or a reference that defines an interface that the object implements. A cast is not
   * required if the object is being reassigned to a supertype or interface of the object.
   */
  public static void main(String[] args) {

    // Only one object (Lemur) is created.
    Lemur lemur = new Lemur();
    System.out.println(lemur.age); // 10

    // Only the Method from this class is available for that reference even though the actual object
    // is a Lemur.
    HasTail hasTail = lemur;
    System.out.println(hasTail.isTailStriped()); // false
    // If you use a variable to refer to an object, then only the methods or variables that are part
    // of the variable's reference type can be called without explicit cast.
    // So this is not possible, even though the actual object has that property:
    // hasTail.age;

    // The reference type is now Primate. The object itself is still a Lemur. We only have access to
    // the Method(s) defined in Primate
    Primate primate = lemur;
    System.out.println(primate.hasHair()); // true

    // We can reclaim access to all methods and fields by casting the object back to the specific subclass it came from:
    Lemur lemur2 = (Lemur) primate;
    System.out.println(lemur2.age); // 10
  }
}

class Primate {
  public boolean hasHair() {
    return true;
  }
}

interface HasTail {
  public boolean isTailStriped();
}

class Lemur extends Primate implements HasTail {
  public int age = 10;

  @Override
  public boolean isTailStriped() {
    return false;
  }
}
