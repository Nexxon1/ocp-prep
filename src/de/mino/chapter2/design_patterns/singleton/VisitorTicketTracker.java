package de.mino.chapter2.design_patterns.singleton;

/**
 * Example 3: Lazy instantiation of Singletons. Delaying the creation of the singleton until the
 * first time the getInstance() method is called.
 */
public class VisitorTicketTracker {

  private static VisitorTicketTracker instance;

  private VisitorTicketTracker() {}

  public static VisitorTicketTracker getInstance() {
    if (instance == null) {
      // We do not create the singleton object when the class is loaded but rather the first time it
      // is requested by a client.
      instance = new VisitorTicketTracker(); // NOT THREAD-SAFE!
      // In this approach two threads could call the getInstance() method at the same time,
      // resulting in two objects being created. With the other approaches this is avoided since we
      // create the singleton when the class is first loaded and mark the instance as final. There
      // would be one simple solution to guarantee Thread safety here, by adding the 'synchronized'
      // modifier to the getInstance() method.
    }
    return instance;
  }

  // The best approach however would be double-checked locking. The synchronization is costly and
  // actually only needed the first time the object is created so the following would be best for
  // thread safety and performance:
  // The volatile modifier prevents a subtle case where the compiler tries to optimize the code such
  // that the object is accessed before it is finished being constructed.
  private static volatile VisitorTicketTracker instanceSafe;
  public static VisitorTicketTracker getInstanceThreadSafe() {
    if (instanceSafe == null) {
      synchronized (VisitorTicketTracker.class) {
        if (instanceSafe == null) {
          instanceSafe = new VisitorTicketTracker();
        }
      }
    }
    return instanceSafe;
  }

  // Data access methods
  // ...
}
