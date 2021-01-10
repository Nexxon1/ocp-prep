package de.mino.chapter5.basics;

import java.time.*;

public class DaylightSavingsTime {

  public static void main(String[] args) {
    doingMathDuringTimeChange();

    creatingNonExistentTime();
  }

  private static void doingMathDuringTimeChange() {
    System.out.println("--- Doing math with DateTimes during the time change ---");
    System.out.println("March - spring forward: 1:00-1:59 a.m. --> 3:00-4:00 a.m.");
    LocalDate date1 = LocalDate.of(2016, Month.MARCH, 13);
    LocalTime time = LocalTime.of(1, 30);
    ZoneId zoneId = ZoneId.of("US/Eastern");
    ZonedDateTime dateTimeMarch = ZonedDateTime.of(date1, time, zoneId);
    System.out.println(dateTimeMarch); // 2016-03-13T01:30-05:00[US/Eastern]
    System.out.println("Adding one hour - note how the time and the offset to UTC changes");
    dateTimeMarch = dateTimeMarch.plusHours(1);
    System.out.println(dateTimeMarch); // 2016-03-13T03:30-04:00[US/Eastern]

    System.out.println(
        "November - fall backwards: 1:00-1:59 a.m. --> 1:00-1:59 a.m. --> 2:00-4:00 a.m.");
    LocalDate date2 = LocalDate.of(2016, Month.NOVEMBER, 6);
    ZonedDateTime dateTimeNov = ZonedDateTime.of(date2, time, zoneId);
    System.out.println(dateTimeNov); // 2016-11-06T01:30-04:00[US/Eastern]
    System.out.println("Adding one hour - note how the time and the offset to UTC changes");
    dateTimeNov = dateTimeNov.plusHours(1);
    System.out.println(dateTimeNov); // 2016-11-06T01:30-05:00[US/Eastern]
  }

  private static void creatingNonExistentTime() {
    System.out.println("\n--- Creating a non existent due to daylight saving time change ---");
    System.out.println("Java notices the time doesn't exist and just rolls forward");
    LocalDate date = LocalDate.of(2016, Month.MARCH, 13);
    LocalTime time = LocalTime.of(2, 30);
    ZoneId zoneId = ZoneId.of("US/Eastern");
    ZonedDateTime dateTime = ZonedDateTime.of(date, time, zoneId);
    System.out.println(dateTime); // 2016-03-13T03:30-04:00[US/Eastern]
  }
}
