package de.mino.chapter5.basics;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class DurationPlayground {

  public static void main(String[] args) {
    waysToCreateDurations();

    determineTemporalDifferences();

    usingDurationWithDateTimeClasses();
  }

  private static void waysToCreateDurations() {
    System.out.println("\n--- There are different ways to create a Duration ---");
    // Java displays all non zero parts of Duration.
    // The number of seconds include fractional seconds
    System.out.println(Duration.ofDays(1)); // PT24H
    System.out.println(Duration.ofHours(1)); // PT1H
    System.out.println(Duration.ofMinutes(1)); // PT1M
    System.out.println(Duration.ofSeconds(10)); // PT10S
    System.out.println(Duration.ofMillis(1)); // PT0.001S
    System.out.println(Duration.ofNanos(1)); // PT0.000000001S

    System.out.println(
        Duration.ofSeconds(3600)); // PT1H --> Java makes it more readable. 3600 seconds are 1 hour

    System.out.println(
        "We can rewrite that with the more generic factory method that expects a TemporalUnit as parameter.");
    System.out.println(Duration.of(1, ChronoUnit.DAYS)); // PT24H
    System.out.println(Duration.of(1, ChronoUnit.HOURS)); // PT1H
    System.out.println(Duration.of(1, ChronoUnit.MINUTES)); // PT1M
    System.out.println(Duration.of(10, ChronoUnit.SECONDS)); // PT10S
    System.out.println(Duration.of(1, ChronoUnit.MILLIS)); // PT0.001S
    System.out.println(Duration.of(1, ChronoUnit.NANOS)); // PT0.000000001S

    System.out.println("This enum also has some convenient units such as HALF_DAYS for 12 hours");
    System.out.println(Duration.of(1, ChronoUnit.HALF_DAYS)); // PT12H
  }

  private static void determineTemporalDifferences() {
    System.out.println(
        "\n--- ChronoUnit is great to determine how far two Temporal values (LocalDate, LocalTime, LocalDateTime/ ZonedDateTime) are apart ---");
    LocalTime time1 = LocalTime.of(5, 15);
    LocalTime time2 = LocalTime.of(6, 30);
    LocalDate date = LocalDate.of(2016, 1, 20);

    System.out.println(
        ChronoUnit.HOURS.between(
            time1, time2)); // 1 --> The between method truncates rather than rounds
    System.out.println(ChronoUnit.MINUTES.between(time1, time2)); // 75
    System.out.println(ChronoUnit.MINUTES.between(time2, time1)); // -75
    // Don't mix time and date up
    // DateTimeException: Unable to obtain LocalTime from TemporalAccessor: 2016-01-20 of type
    // java.time.LocalDate
    // System.out.println(ChronoUnit.MINUTES.between(time1, date)); //Exception
  }

  private static void usingDurationWithDateTimeClasses() {
    System.out.println("\n--- Using Duration with the different Date and Time classes ---");
    LocalDate date = LocalDate.of(2015, 1, 20);
    LocalTime time = LocalTime.of(6, 15);
    LocalDateTime dateTime = LocalDateTime.of(date, time);
    Duration duration = Duration.ofHours(6);

    System.out.println(
        "You can use Duration with LocalTime and LocalDateTime/ ZonedDateTime but not with LocalDate");
    System.out.println(time.plus(duration)); // 12:15
    System.out.println(dateTime.plus(duration)); // 2015-01-20T12:15
    // You cannot add hours to a LocalDate (because it doesn't contain a Time)
    // UnsupportedTemporalTypeException: Unsupported unit: Seconds
    // System.out.println(date.plus(duration)); // Exception

    System.out.println("Adding a Duration so we move forward a day");
    Duration duration23H = Duration.ofHours(23);
    System.out.println(time.plus(duration23H)); // 05:15 --> Like a real clock
    System.out.println(dateTime.plus(duration23H)); // 2015-01-21T05:15 --> wraps to the next day

    System.out.println(
        "Period and Duration are not equivalent! You can never use a Duration with LocalDate");
    Period period1Day = Period.ofDays(1);
    Duration duration1Day = Duration.ofDays(1);
    System.out.println(date.plus(period1Day)); //2015-01-21
    // System.out.println(date.plus(duration1Day)); // UnsupportedTemporalTypeException
  }
}
