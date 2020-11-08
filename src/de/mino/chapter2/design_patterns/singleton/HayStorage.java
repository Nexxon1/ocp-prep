package de.mino.chapter2.design_patterns.singleton;

/** Example 1 for the singleton pattern. */
public class HayStorage {

  private int quantity = 0;

  // Private constructor, which ensures that no other class is capable of instantiation another
  // version of the class. That also makes the class effectively final. (Recall that the first line
  // of any constructor is a call to the parent constructor via super(). This is not possible for a
  // subclass since the constructor here is private. So this class is effectively final.
  private HayStorage() {}

  // 'instance' is the singleton Object, available throughout the entire application.
  // The singleton Object is created when the class is first loaded.
  // The final keyword also ensures that only that one instance is created within our application
  private static final HayStorage instance = new HayStorage();

  // We get access to the singleton object via this method.
  public static HayStorage getInstance() {
    return instance;
  }

  // Methods have the synchronized modifier to prevent two processes from running the same method at
  // the same time
  public synchronized void addHay(int amount) {
    quantity += amount;
  }

  public synchronized boolean removeHay(int amount) {
    if (quantity < amount) {
      return false;
    }
    quantity -= amount;
    return true;
  }

  public synchronized int getHayQuantity() {
    return quantity;
  }
}
