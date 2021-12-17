package de.mino.chapter5.i18n_and_l10n.format_date_time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class DateTimeFormatting {

  private static final LocalDate date = LocalDate.of(2020, Month.JANUARY, 20);
  private static final LocalTime time = LocalTime.of(11, 12, 34);
  private static final LocalDateTime dateTime = LocalDateTime.of(date, time);

  public static void main(String[] args) {
    isoFormatter();

    localizedDateFormatter();

    predefinedFormatters();

    parseFromString();
  }

  private static void isoFormatter() {
    System.out.println("Formatting using the ISO Format");
    LocalDateTime dateTime = LocalDateTime.of(date, time);
    System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_DATE)); // 2020-01-20
    System.out.println(time.format(DateTimeFormatter.ISO_LOCAL_TIME)); // 11:12:34
    System.out.println(
        dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)); // 2020-01-20T11:12:34
  }

  private static void localizedDateFormatter() {
    System.out.println("\nFormatting using ofLocalizedDate");
    DateTimeFormatter shortDate =
        DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(Locale.GERMANY);
    System.out.println(shortDate.format(dateTime)); // 20.01.20
    System.out.println(shortDate.format(date)); // 20.01.20
    // A time cannot be formatted ias a date
    // System.out.println(shortDate.format(time)); // UnsupportedTemporalTypeException Unsupported
    // field: DayOfMonth

    // Note: The format() method is declared on both, the formatter and the date/time objects so
    // this is the same:
    System.out.println(dateTime.format(shortDate));
    System.out.println(date.format(shortDate));
    // System.out.println(time.format(shortDate)); // UnsupportedTemporalTypeException Unsupported
    // field: DayOfMonth
  }

  private static void predefinedFormatters() {
    System.out.println("\nComparing the different predefined DateTimeFormatter Styles");
    System.out.println("Short Style");
    DateTimeFormatter shortF =
        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(Locale.GERMANY);
    System.out.println(shortF.format(dateTime)); // 1/20/20 11:12 AM

    System.out.println("Medium Style");
    DateTimeFormatter mediumF =
        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.GERMANY);
    System.out.println(mediumF.format(dateTime)); // Jan 20, 2020 11:12:34 AM

    // Long and Full Style requires a zone
    System.out.println("Long Style");
    DateTimeFormatter longF =
        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).withLocale(Locale.GERMANY);
    ZonedDateTime zonedDateTime = dateTime.atZone(ZoneId.of("Europe/Berlin"));
    System.out.println(longF.format(zonedDateTime));

    System.out.println("Full Style");
    DateTimeFormatter fullF =
        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).withLocale(Locale.GERMANY);
    System.out.println(fullF.format(zonedDateTime));

    System.out.println("\nCustom Pattern");
    DateTimeFormatter f =
        DateTimeFormatter.ofPattern("MMMM dd, yyyy, hh:mm").withLocale(Locale.GERMANY);
    System.out.println(dateTime.format(f)); // Januar 20, 2020, 11:12
  }

  private static void parseFromString() {
    System.out.println("\nParsing dates and times from a String");
    DateTimeFormatter f = DateTimeFormatter.ofPattern("MM dd yyyy");
    LocalDate date = LocalDate.parse("01 02 2015", f);
    LocalTime time = LocalTime.parse("11:22");
    System.out.println(date); // 2015–01–02
    System.out.println(time); // 11:22
  }
}
