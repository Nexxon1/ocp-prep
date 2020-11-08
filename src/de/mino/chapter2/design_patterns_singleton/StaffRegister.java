package de.mino.chapter2.design_patterns_singleton;

/** Example 2 to create a singleton. Here via static initialization block. */
public class StaffRegister {

  private static final StaffRegister instance;

  // The static initialization block creates a singleton when the class is loaded.
  static {
    instance = new StaffRegister();
    // An advantage of this approach is that we could allow additional steps to be taken here to set
    // up the singleton after it has been created.
  }
}
