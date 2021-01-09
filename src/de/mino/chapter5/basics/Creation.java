package de.mino.chapter5.basics;

import java.time.*;

public class Creation {

  public static void main(String[] args) {
    System.out.println("--- Using static method now() to create dates and times ---");
    // Only date: Year Month Day
    System.out.println(LocalDate.now());      // e.g. 2021-01-08
    // Only time: Hours, Minutes, Seconds, Fractional Seconds
    System.out.println(LocalTime.now());      // e.g. 14:12:04.224736700
    // Date and Time. Java uses 'T' to separate date & time when converting it to a String
    System.out.println(LocalDateTime.now());  // e.g. 2021-01-08T14:12:04.224736700
    // Date, Time and Timezone. It prints the time zone offset and the time zone
    // Europe/Berlin is 1 time zone away from Greenwich Mean Time (GMT)
    // GMT is a time zone in Europe that is used as time zone zero when discussing offsets. GMT and UTC is the same.
    System.out.println(ZonedDateTime.now());  // e.g. 2021-01-08T14:12:04.225714500+01:00[Europe/Berlin]

    System.out.println("\n--- Using static method of() to create dates ---");
    // Month is an enum.
    // For months we start counting at 1 for January
    LocalDate ld1 = LocalDate.of(2020, Month.FEBRUARY, 20);
    LocalDate ld2 = LocalDate.of(2020, 2, 20);
    System.out.println(ld1); // 2020-02-20
    System.out.println(ld2); // 2020-02-20

    System.out.println("\n--- Using static method of() to create times ---");
    // You can choose how detailed you want to be:
    LocalTime lt1 = LocalTime.of(6, 15); // hour and minute
    LocalTime lt2 = LocalTime.of(6, 15, 30); // + seconds
    LocalTime lt3 = LocalTime.of(6, 15, 30, 200); // + nanoseconds
    System.out.println(lt1); // 06:15
    System.out.println(lt2); // 06:15:30
    System.out.println(lt3); // 06:15:30.000000200

    System.out.println("\n--- Combine dates and times into LocalDateTime---");
    LocalDateTime ldt1 = LocalDateTime.of(2020, Month.FEBRUARY, 20, 6, 15, 30);
    LocalDateTime ldt2 = LocalDateTime.of(ld1, lt2);
    System.out.println(ldt1); // 2020-02-20T06:15:30
    System.out.println(ldt2); // 2020-02-20T06:15:30

    System.out.println("\n--- Creating ZonedDateTimes with the time zone US/Eastern ---");
    ZoneId zoneId = ZoneId.of("US/Eastern");
    ZonedDateTime zdt1 = ZonedDateTime.of(ld1, lt2, zoneId);
    ZonedDateTime zdt2 = ZonedDateTime.of(ldt2, zoneId);
    System.out.println(zdt1); // 2020-02-20T06:15:30-05:00[US/Eastern]
    System.out.println(zdt2); // 2020-02-20T06:15:30-05:00[US/Eastern]

    System.out.println("\n--- Finding out your time zone ---");
    ZoneId myTimeZone = ZoneId.systemDefault();
    System.out.println(myTimeZone); // Europe/Berlin
    System.out.println("Available time zones: " + ZoneId.getAvailableZoneIds());

    System.out.println("\n--- Comparing Time zones ---");
    LocalDateTime now = LocalDateTime.now();
    ZonedDateTime euTime = ZonedDateTime.of(now, ZoneId.of("Europe/Paris"));
    ZonedDateTime usEastTIme = ZonedDateTime.of(now, ZoneId.of("US/Eastern"));
    System.out.println(euTime); // e.g. 2021-01-08T14:34:02.048621+01:00[Europe/Paris]
    System.out.println(usEastTIme); // e.g. 2021-01-08T14:34:02.048621-05:00[US/Eastern]
    System.out.println(Duration.between(usEastTIme, euTime)); // PT-6H --> usEastTime is 6h in the past
  }
}
