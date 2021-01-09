package de.mino.chapter5.basics;

import java.time.*;

public class PeriodPlayground {

  public static void main(String[] args) {
    LocalDate start = LocalDate.of(2015, Month.JANUARY, 1);
    LocalDate end = LocalDate.of(2015, Month.MARCH, 30);
    Period period = Period.ofMonths(1);
    performAnimalEnrichment(start, end, period);

    waysToCreatePeriods();

    usingPeriodWithDateTimeClasses();

    convertingToLong();
  }

  /**
   * Animals at the zoo get a new toy every month during the {@code period} between {@code start}
   * and {@code end} date.
   */
  private static void performAnimalEnrichment(LocalDate start, LocalDate end, Period period) {
    LocalDate upTo = start;
    while (upTo.isBefore(end)) {
      System.out.println("Give new toy. Date: " + upTo);
      upTo = upTo.plus(period);
    }
  }

  private static void waysToCreatePeriods() {
    System.out.println("\n--- There are 5 ways to create a Period ---");
    Period annually = Period.ofYears(1); // every 1 year
    Period quarterly = Period.ofMonths(3); // every 3 months
    Period every3Weeks = Period.ofWeeks(3); // every 3 weeks
    Period every2Days = Period.ofDays(2); // every 2 days
    Period everyYearAndWeek = Period.of(1, 0, 7); // every year and 7 days

    // You can't chain the creation of Periods!
    Period wrong =
        Period.ofYears(1).ofDays(3); // This creates a period of 3 days, not 1 year and 3 days!
    System.out.println(wrong); // P3D
    // It is equivalent to writing
    Period wrong2 = Period.ofYears(1);
    wrong2 = Period.ofDays(3);

    // This works:
    Period right = Period.ofYears(1).plusDays(3); // This creates a period of 1 year and 3 days!
    System.out.println(right); // P1Y3D

    // Java displays all non zero parts of the period
    // P = Period
    // Y = Years
    // M = Months
    // D = Days
    System.out.println(Period.of(1, 2, 3)); // P1Y2M3D
    System.out.println(Period.of(0, 20, 47)); // P20M47D
    System.out.println(Period.ofWeeks(3)); // P21D
  }

  private static void usingPeriodWithDateTimeClasses() {
    System.out.println("\n--- Using Periods with the different Date and Time classes ---");
    LocalDate date = LocalDate.of(2015, 1, 20);
    LocalTime time = LocalTime.of(6, 15);
    LocalDateTime dateTime = LocalDateTime.of(date, time);
    Period period = Period.ofMonths(1);

    System.out.println(
        "You can use Period with LocalDate and LocalDateTime/ ZonedDateTime but not with LocalTime");
    System.out.println(date.plus(period)); // 2015-02-20
    System.out.println(dateTime.plus(period)); // 2015-02-20T06:15
    // You cannot add Months to a Time
    // UnsupportedTemporalTypeException: Unsupported unit: Months
    // System.out.println(time.plus(period)); // Exception
  }

  private static void convertingToLong() {
    System.out.println("\n--- Converting Date and DateTime to a long ---");
    System.out.println(
        "You can convert LocalDate and LocalDateTime/ ZonedDateTime to a long equivalent in relation to 1.1.1970. "
            + "This special date is called epoch. This is the date that Unix started using for date standards so Java reused it.");

    LocalDate localDate = LocalDate.of(2020, Month.MAY, 29);
    LocalTime localTime = LocalTime.of(0, 0);
    LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
    ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());

    System.out.println("toEpochDay() returns the days since 1.1.1970");
    System.out.println(localDate.toEpochDay()); // 18411
    System.out.println("toEpochSecond() returns the seconds since 1.1.1970");
    System.out.println(localDateTime.toEpochSecond(ZoneOffset.UTC)); // 1590710400
    System.out.println(zonedDateTime.toEpochSecond()); // 1590703200
  }
}
