package de.mino.chapter2.design_patterns_singleton;

/**
 * Note: There might be multiple LlamaTrainers at the Zoo but only one HayStorage location. With
 * this data model we can have many LlamaTrainer instances but only a single instance of HayStorage
 */
public class LlamaTrainer {

  public boolean feedLlamas(int numberOfLlamas) {
    int amountNeeded = 5 * numberOfLlamas;
    HayStorage hayStorage = HayStorage.getInstance();
    if (hayStorage.getHayQuantity() < amountNeeded) {
      hayStorage.addHay(amountNeeded + 10);
    }
    boolean fed = hayStorage.removeHay(amountNeeded);
    // Check the return type of fed because it might be possible that someone else could have taken
    // the food that we just restocked before we had a chance to use it.
    if (fed) {
      System.out.println("Llamas have been fed with " + amountNeeded + " hay");
    }
    return fed;
  }

  public static void main(String[] args) {
    LlamaTrainer llamaTrainer = new LlamaTrainer();
    llamaTrainer.feedLlamas(3);
  }
}
