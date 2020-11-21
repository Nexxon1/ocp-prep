package de.mino.chapter3.generics.custom_class;

public class Elephant {
  private String name;

  public Elephant(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Elephant{" +
        "name='" + name + '\'' +
        '}';
  }

  public static void main(String[] args) {
    Elephant fluffy = new Elephant("Fluffy");
    Crate<Elephant> elephantCrate = new Crate<>();
    elephantCrate.packCrate(fluffy);
    System.out.println(elephantCrate.emptyCrate());
  }
}
