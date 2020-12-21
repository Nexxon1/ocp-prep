package de.mino.chapter3.comparison.comparable;

/** Custom class Animal. we want to compare them by id */
public class Animal implements Comparable<Animal> {

  private int id;

  public Animal(int id) {
    this.id = id;
  }

  /**
   * <ul>
   *   <li>0 if the current object is equal to the argument
   *   <li><0 if the current object is smaller than the argument
   *   <li>>0 if the current object is larger than the argument
   * </ul>
   */
  @Override
  public int compareTo(Animal animal) {
    // Sorting by id ASC
    // With animal.id - id the sort would be DESC
    return id - animal.id;
  }

  public static void main(String[] args) {
    Animal a1 = new Animal(5);
    Animal a2 = new Animal(7);

    System.out.println(a1.compareTo(a2)); // -2 because the current object is smaller than the argument
    System.out.println(a1.compareTo(a1)); // 0 because the current object is the same as the argument
    System.out.println(a2.compareTo(a1)); // 2 because the current object is bigger than the argument
  }
}
