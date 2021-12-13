package de.mino.chapter5.i18n_and_l10n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class UsingResourceBundle {

  public static void main(String[] args) {
    Locale us = new Locale("en", "US");
    Locale france = new Locale("fr", "FR");
    // Note that we are actually searching for a file named 'Zoo_en_US.properties' but since that
    // doesn't exist we use 'Zoo_en.properties'
    // If the country-specific resource bundle isn't found, the language-specific one is being used.
    printProperties(us);
    System.out.println();
    printProperties(france);

    convertResourceBundleToProperties();
  }

  private static void printProperties(Locale locale) {
    System.out.println("--- Print the properties from ResourceBundle ---");
    ResourceBundle rb = ResourceBundle.getBundle("resources/Zoo", locale);
    System.out.println("Getting a specific value from the Resource Bundle " + locale);
    System.out.println(rb.getString("hello"));
    System.out.println("Substitute a variable in a resource bundle value");
    System.out.println(MessageFormat.format(rb.getString("helloByName"), "Tim"));

    System.out.println("Loop through all key/value pairs");
    rb.keySet().stream().map(key -> "Key: "  + key + " \t\t Value: " + rb.getString(key)).forEach(System.out::println);

  }

  private static void convertResourceBundleToProperties() {
    System.out.println("\n--- Convert ResourceBundle to Properties ---");
    Properties properties = new Properties();
    ResourceBundle rb = ResourceBundle.getBundle("resources/Zoo", new Locale("en", "US"));
    rb.keySet().forEach(key -> properties.put(key, rb.getString(key)));
    System.out.println("A property key that doesn't exist");
    System.out.println(properties.getProperty("notReallyAProperty")); // null
    System.out.println("A property key that doesn't exist but a default value is defined");
    System.out.println(properties.getProperty("notReallyAProperty", "defaultValue")); // defaultValue
  }
}
