package com.java.lessons.java8;

import java.time.*;

public class DateTimeApi {

    public static void main(String[] args) {
        ZoneId london = ZoneId.of("Europe/London");
        LocalDate july4 = LocalDate.of(2014, Month.JULY, 4);
        LocalTime early = LocalTime.parse("08:45");
        ZonedDateTime flightDeparture = ZonedDateTime.of(july4, early,
                london);
        System.out.println("flightDeparture==>"+flightDeparture);
        LocalTime from = LocalTime.from(flightDeparture);
        System.out.println("from==>"+from);
        ZonedDateTime touchDown
                = ZonedDateTime.of(july4,
                LocalTime.of (11, 35),
                ZoneId.of("Europe/Stockholm"));
        Duration flightLength = Duration.between(flightDeparture, touchDown);
        System.out.println("flightLength==>"+flightLength);
// How long have I been in continental Europe?
        ZonedDateTime now = ZonedDateTime.now();
        Duration timeHere = Duration.between(touchDown, now);
        System.out.println("timeHere==>"+timeHere);
    }
}
