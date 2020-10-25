package de.mino.chapter2.simple_lambdas;

public class FindMatchingAnimals {

  private static void print(Animal animal, CheckTrait trait) {
    if (trait.test(animal)) {
      System.out.println(animal);
    }
  }

  public static void main(String[] args) {
    Animal fish = new Animal("fish", false, true);
    // We are passing a lambda as second parameter of the print() method. That method expects a
    // 'CheckTrait' as second parameter. Since we are passing a lambda instead, Java treats
    // CheckTrait as functional interface and tries to map it to the single abstract method called
    // 'boolean test(Animal a);'. Since that method expects anAnimal, it means that the lambda
    // parameter has to be an animal. And since it returns a boolean, we know that the lambda
    // returns a boolean.
    print(fish, a -> a.canHop());

    Animal kangaroo = new Animal("kangaroo", true, false);
    print(kangaroo, a -> a.canHop());
  }
}
