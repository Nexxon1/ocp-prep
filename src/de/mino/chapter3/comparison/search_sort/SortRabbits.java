package de.mino.chapter3.comparison.search_sort;

import de.mino.chapter3.comparison.comparable.Duck;

import java.util.*;

public class SortRabbits {

  public static void main(String[] args) {
    List<Rabbit> rabbits = new ArrayList<>();
    rabbits.add(new Rabbit(2));
    rabbits.add(new Rabbit(1));
    // Collections.sort(rabbits); // Does not compile because Rabbit does not implement Comparable.

    // However, the Comparator's compare method can be used for sorting
    // Comparator to sort the Rabbits by id ASC
    Comparator<Rabbit> comparator = (r1, r2) -> r1.id - r2.id;
    Collections.sort(rabbits, comparator);
    System.out.println(rabbits);

    sortAndBinarySearch();

    comparableAndTreeSet();
  }

  private static void sortAndBinarySearch() {
    System.out.println("Binary Search only works when the list is in the natural order");
    List<String> names = Arrays.asList("Fluffy", "Hoppy");
    int index = Collections.binarySearch(names, "Hoppy", Comparator.naturalOrder());
    System.out.println(index);
    System.out.println(
        "When the list is in the wrong order, the index returned from the binary search can't be determined:");
    int indexUndefined = Collections.binarySearch(names, "Hoppy", Comparator.reverseOrder());
    System.out.println(indexUndefined);
  }

  private static void comparableAndTreeSet() {
    System.out.println("Only classes that implement the Comparable interface can be used in a TreeSet");
    Set<Duck> comparableDucks = new TreeSet<>();
    // This class implements the Comparable interface
    comparableDucks.add(new Duck("Donald"));

    // This class doesn't implement the Comparable interface --> Exception is thrown
    Set<Rabbit> rabbits = new TreeSet<>();
    //rabbits.add(new Rabbit(1)); // Throws Exception

    // You can avoid this by passing a custom Comparator
    // This is a solution when you work with Collections that require sorting but the elements in the Collection don't implement the Comparable intejrface
    Set<Rabbit> rabbitsModified = new TreeSet<>(new Comparator<Rabbit>() {
      @Override
      public int compare(Rabbit r1, Rabbit r2) {
        return r1.id - r2.id;
      }
    });
    rabbitsModified.add(new Rabbit(1));
    System.out.println(rabbitsModified);
  }
}
