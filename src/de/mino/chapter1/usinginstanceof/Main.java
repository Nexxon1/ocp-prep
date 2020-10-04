package de.mino.chapter1.usinginstanceof;

/**
 * The instanceof operator is commonly used to determine if an instance is a subclass of a
 * particular object before applying an explicit cast.
 */
public class Main {

  public static void main(String[] args) {

    // Syntax: obj instanceOf Class
    // a instanceOf B - Returns true if the reference to which a points is an instance of class B, a
    // subclass of of B or a class that implements the B interface.

    // Note: 'hippo' points to Hippo, not to HeavyAnimal. HeavyAnimal is just the reference type.
    HeavyAnimal hippo = new Hippo();
    boolean b1 = hippo instanceof Hippo; // true
    boolean b2 = hippo instanceof HeavyAnimal; // true
    boolean b3 = hippo instanceof Elephant; // false
    System.out.println("b1: " + b1 + "\t b2: " + b2 + "\t b3: " + b3);

    // x instanceOf Object is always true except when we have a null reference
    boolean b4 = hippo instanceof Object; // true
    Hippo nullHippo = null;
    boolean b5 = nullHippo instanceof Object; // false
    System.out.println("b4: " + b4 + "\t b5: " + b5);

    // Does not compile because there is no possible way for a Hippo variable reference to be an
    // Elephant.
    Hippo anotherHippo = new Hippo();
    // boolean b6 = anotherHippo instanceof Elephant;
    // This compilation check only applies when instanceof is called on a class. That's why the following compiles:
    boolean b7 = hippo instanceof Mother; // false
    System.out.println("b7: " + b7);
  }
}
