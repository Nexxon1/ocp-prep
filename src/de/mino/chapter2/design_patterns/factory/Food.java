package de.mino.chapter2.design_patterns.factory;

public abstract class Food {

  private int quantity;

  Food(int quantity) {
    this.quantity = quantity;
  }

  public int getQuantity() {
    return quantity;
  }

  public abstract void consumed();
}
