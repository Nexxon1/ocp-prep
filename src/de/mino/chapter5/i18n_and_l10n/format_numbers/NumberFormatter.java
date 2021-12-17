package de.mino.chapter5.i18n_and_l10n.format_numbers;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class NumberFormatter {

  public static void main(String[] args) throws ParseException {
    format();
    parse();
  }

  private static void format() {
    System.out.println("Formatting a given number based on a Locale");
    int attendeesPerYear = 3_200_000;
    int attendeesPerMonth = attendeesPerYear / 12;
    NumberFormat us = NumberFormat.getInstance(Locale.US);
    System.out.println(Locale.US + ":\t" + us.format(attendeesPerMonth)); // en_US:	266,666

    NumberFormat ger = NumberFormat.getInstance(Locale.GERMANY);
    System.out.println(Locale.GERMANY + ":\t" + ger.format(attendeesPerMonth)); // de_DE:	266.666

    NumberFormat ca = NumberFormat.getInstance(Locale.CANADA_FRENCH);
    System.out.println(
        Locale.CANADA_FRENCH + ":\t" + ca.format(attendeesPerMonth)); // fr_CA:	266 666

    System.out.println("\nFormatting currency");
    double price = 48;
    NumberFormat usCurrency = NumberFormat.getCurrencyInstance(Locale.US);
    System.out.println(usCurrency.format(price)); // $48.00
    NumberFormat gerCurrency =
        NumberFormat.getCurrencyInstance(); // Using default locale. E.g. de_DE
    System.out.println(gerCurrency.format(price)); // 48,00 €

    System.out.println("\nFormatting percentages");
    double percentage = 0.75;
    NumberFormat usPercentage = NumberFormat.getPercentInstance(Locale.US);
    System.out.println(usPercentage.format(percentage)); // 75%
    NumberFormat gerPercentage = NumberFormat.getPercentInstance();
    System.out.println(gerPercentage.format(percentage)); // 75 %

    System.out.println("\nRounding to integer");
    NumberFormat integerFormatter = NumberFormat.getIntegerInstance();
    System.out.println(integerFormatter.format(0.51)); // 1
    System.out.println(integerFormatter.format(0.49)); // 0
  }

  private static void parse() throws ParseException {
    System.out.println("\n\nParse a given String based on a Locale");
    NumberFormat en = NumberFormat.getInstance(Locale.US);
    NumberFormat fr = NumberFormat.getInstance(Locale.FRANCE);
    NumberFormat ger = NumberFormat.getInstance(Locale.GERMANY);
    String enDecimal = "40.45";
    System.out.println("Parsing an US decimal");
    // parse() throws the checked Exception ParseException if it fails to parse
    System.out.println(Locale.US + "\t" + en.parse(enDecimal)); // 40.45
    System.out.println(Locale.FRANCE + "\t" + fr.parse(enDecimal)); // 40
    System.out.println(Locale.GERMANY + "\t" + ger.parse(enDecimal)); // 4045
    System.out.println("Parsing an german decimal");
    String gerDecimal = "40,45";
    System.out.println(Locale.US + "\t" + en.parse(gerDecimal)); // 4045
    System.out.println(Locale.FRANCE + "\t" + fr.parse(gerDecimal)); // 40.45
    System.out.println(Locale.GERMANY + "\t" + ger.parse(gerDecimal)); // 40.45

    System.out.println("\nParse a given currency String based on a Locale");
    String annualIncome = "$92,807.99";
    NumberFormat cf = NumberFormat.getCurrencyInstance(Locale.US);
    double value = (Double) cf.parse(annualIncome);
    System.out.println(value); // 92807.99

    System.out.println("\nThe parse() Method only parses the beginning of a String.");
    System.out.println("After reaching a character that cannot be parsed, the parsing stops");
    NumberFormat nf = NumberFormat.getInstance(Locale.US);
    String one = "456abc";
    String two = "-2.5165x10";
    String three = "x85.3";
    System.out.println(nf.parse(one)); // 456
    System.out.println(nf.parse(two)); // -2.5165
    // System.out.println(nf.parse(three)); // throws ParseException
  }
}
