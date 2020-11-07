package de.mino.chapter2.design_principles.composition;

public class Penguin {
  private final Flossen flippers;
  private final Schwimmhaeute webbedFeet;

  // Penguin is composed of instances of 'Flossen' and 'Schwimmheaute'
  public Penguin() {
    this.flippers = new Flossen();
    this.webbedFeet = new Schwimmhaeute();
  }

  // The logic of these methods is delegated to the actual class that defines it
  public void flap() {
    this.flippers.flap();
  }

  public void kick() {
    this.webbedFeet.kick();
  }
}
