package de.mino.chapter5.basics;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

public class InstantPlayground {

  public static void main(String[] args) throws InterruptedException {

    stopwatch();

    zonedDateTimeToInstant();

    doingMath();
  }

  private static void stopwatch() throws InterruptedException {
    System.out.println("--- Using Instant as a Stopwatch ---");
    Instant now = Instant.now();
    TimeUnit.MILLISECONDS.sleep(555);
    Instant later = Instant.now();

    Duration duration = Duration.between(now, later);
    System.out.println(duration.toMillis()); // 555
  }

  private static void zonedDateTimeToInstant() {
    System.out.println("\n--- Converting a ZonedDateTime into an Instant ---");
    LocalDate date = LocalDate.of(2021, 1, 10);
    LocalTime time = LocalTime.of(10, 40);
    ZoneId zoneId = ZoneId.of("US/Eastern");

    ZonedDateTime zonedDateTime = ZonedDateTime.of(date, time, zoneId);
    Instant instant = zonedDateTime.toInstant();
    System.out.println(
        "These two DateTimes represent the same moment in time - one with time zone and one with the time zone 0 (GMT)");
    System.out.println(zonedDateTime); // 2021-01-10T10:40-05:00[US/Eastern]
    System.out.println(
        "Instant gets rid of the time zone and turns it into an Instant of time in GMT");
    System.out.println(instant); // 2021-01-10T15:40:00Z
  }

  private static void doingMath() {
    System.out.println("\n--- Adding and subtracting from an Instant ---");
    LocalDate date = LocalDate.of(2021, 1, 10);
    LocalTime time = LocalTime.of(10, 40);
    ZoneId zoneId = ZoneId.of("US/Eastern");
    ZonedDateTime zonedDateTime = ZonedDateTime.of(date, time, zoneId);

    System.out.println(
        "If you have Epoch seconds (seconds since 1.1.1970) you can create an instant from that");
    long epochSeconds = zonedDateTime.toEpochSecond();
    System.out.println(epochSeconds); // 1610293200
    Instant instant = Instant.ofEpochSecond(epochSeconds);
    System.out.println(instant); // 2021-01-10T15:40:00Z

    System.out.println("Instant allows you to add or subtract any unit day or smaller");
    Instant instantPlus = instant.plus(11, ChronoUnit.DAYS);
    System.out.println(instantPlus); // 2021-01-21T15:40:00Z
    Instant instantMinus = instant.minus(5, ChronoUnit.SECONDS);
    System.out.println(instantMinus); // 2021-01-10T15:39:55Z

    // UnsupportedTemporalTypeException: Unsupported unit: Weeks
    // Instant instantTooMuchPlus = instant.plus(1, ChronoUnit.WEEKS); // Exception
  }
}
