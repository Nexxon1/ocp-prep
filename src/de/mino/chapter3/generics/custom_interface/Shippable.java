package de.mino.chapter3.generics.custom_interface;

/** Generic interface and three ways to implement the interface */
public interface Shippable<T> {
  void ship(T t);
}

// 1. Option - Use a concrete type
class Robot {}

class ShippableRobotCrate implements Shippable<Robot> {
  @Override
  public void ship(Robot robot) {}
}

// 2. Option - Make the class generic itself. Note: Any type parameter would be allowed, also 'T'
class ShippableAbstractCrate<U> implements Shippable<U> {
  @Override
  public void ship(U u) {}
}

// 3. Option - Don't use Generics at all (not recommended)
class ShippableCrate implements Shippable {
  @Override
  public void ship(Object o) {}
}
