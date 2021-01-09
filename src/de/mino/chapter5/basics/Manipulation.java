package de.mino.chapter5.basics;

import java.time.*;

public class Manipulation {

  public static void main(String[] args) {
    addingToDate();

    subtractingToDateTime();
  }

  private static void addingToDate() {
    System.out.println("--- Adding to a LocalDate ---");

    LocalDate date = LocalDate.of(2014, Month.JANUARY, 20);
    System.out.println(date); // 2014-01-20
    // The reassignment is essential because date and time classes are immutable!
    date = date.plusDays(2);
    System.out.println(date); // 2014-01-22
    date = date.plusWeeks(1);
    System.out.println(date); // 2014-01-29
    date = date.plusMonths(1);
    System.out.println(
        date); // 2014-02-28 --> Note there is no 29th February in 2014 (since it isn't a leap year)
    date = date.plusYears(5);
    System.out.println(date); // 2019-02-28

    date = date.plus(Period.ofDays(3));
    System.out.println(date); // 2019-03-03
  }

  private static void subtractingToDateTime() {
    System.out.println("\n--- Subtracting from a LocalDateTime ---");

    LocalDate date = LocalDate.of(2020, Month.JANUARY, 20);
    LocalTime time = LocalTime.of(5, 10);
    LocalDateTime dateTime = LocalDateTime.of(date, time);
    System.out.println(dateTime); // 2020-01-20T05:10
    dateTime = dateTime.minusDays(1);
    System.out.println(dateTime); // 2020-01-19T05:10
    dateTime = dateTime.minusHours(10);
    System.out.println(dateTime); // 2020-01-18T19:10
    dateTime = dateTime.minusSeconds(30);
    System.out.println(
        dateTime); // 2020-01-18T19:09:30  --> Now Java starts showing seconds. Java just hides the
    // seconds and nanoseconds when we aren't using them.

    dateTime = dateTime.minus(Duration.ofSeconds(2));
    System.out.println(dateTime); // 2020-01-18T19:09:28

    // It is common for date and time methods to be chained:
    LocalDateTime chainedDateTime =
        LocalDateTime.of(date, time)
            .minusDays(1)
            .minusHours(10)
            .minusSeconds(30)
            .minus(Duration.ofSeconds(2));
    System.out.println(chainedDateTime); // 2020-01-18T19:09:28
  }
}
