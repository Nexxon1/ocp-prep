package de.mino.chapter5.i18n_and_l10n.resource_bundle;

import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.ResourceBundle;

public class Zoo_en_US extends ListResourceBundle {

  public static void main(String[] args) {
    String classNameWithoutLocale = Zoo_en_US.class.getPackageName() + ".Zoo";
    // Note: This loads the properties of the Bundle class 'Zoo' with the ending 'en_US'.
    // In this case it is the class itself. In the real world we would have multiple Resource Bundle
    // classes for different languages that would be loaded based on the users Locale.
    System.out.println("Loading the Class Resource Bundle");
    ResourceBundle rb = ResourceBundle.getBundle(classNameWithoutLocale, Locale.US);
    System.out.println(rb.getString("open"));

    System.out.println("\nLoading the Resource Bundle Property File");
    ResourceBundle rbEnglish = ResourceBundle.getBundle("resources/Zoo", Locale.ENGLISH);
    System.out.println(rbEnglish.getString("open"));
  }


  @Override
  protected Object[][] getContents() {
    return new Object[][] {
        {"hello", "Hello again"},
        {"open", "The zoo is still open"}
    };
  }
}
