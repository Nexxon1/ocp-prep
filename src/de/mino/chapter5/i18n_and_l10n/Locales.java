package de.mino.chapter5.i18n_and_l10n;

import java.util.Locale;

public class Locales {

  public static void main(String[] args) {
    gettingLocales();

    waysToCreateLocales();

    switchingTheDefaultLocale();
  }

  private static void gettingLocales() {
    System.out.println("--- Getting Locales ---");
    System.out.println("Locales have the format 'language_COUNTRY' or just 'language'");
    System.out.println("Getting the user's current Locale");
    Locale locale = Locale.getDefault();
    System.out.println(locale); // de_DE
    System.out.println(
        "de_DE means the computer is using german as language and is sitting in germany");

    System.out.println("Getting a specific locale");
    System.out.println(Locale.ENGLISH); // en
    System.out.println(Locale.US); // en_US
  }

  private static void waysToCreateLocales() {
    System.out.println("\n--- Different ways to create Locales ---");
    System.out.println(Locale.US); // en_US
    System.out.println(new Locale("fr")); // fr
    System.out.println(new Locale("hi", "IN")); // hi_IN
    System.out.println(new Locale.Builder().setRegion("US").setLanguage("en").build()); // en_US
  }

  private static void switchingTheDefaultLocale() {
    System.out.println("\n--- Switching the default Locale ---");
    System.out.println(Locale.getDefault()); // de_DE
    Locale.setDefault(Locale.US);
    System.out.println(Locale.getDefault()); // en_US
    System.out.println(
        "The Locale only gets switched for that one Java program for the current run.");
  }
}
