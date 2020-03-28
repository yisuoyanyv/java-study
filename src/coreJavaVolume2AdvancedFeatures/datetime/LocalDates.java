package coreJavaVolume2AdvancedFeatures.datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

/**
 * @author zhangjinglong
 * @date 2020-03-28-12:11 下午
 */

public class LocalDates {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();//Today's date
        System.out.println("today:" + today);

        LocalDate alonzonsBirthDay = LocalDate.of(1903, 6, 14);
        alonzonsBirthDay = LocalDate.of(1903, Month.JUNE, 14);
        //Uses the Month enumeration
        System.out.println("alonzosBirthday:" + alonzonsBirthDay);

        LocalDate programersDay = LocalDate.of(2020, 1, 1).plusDays(255);
        //September 12 ,cause this year is a leap year
        System.out.println("programmersDay:" + programersDay);

        LocalDate independenceDay = LocalDate.of(2020, Month.JULY, 4);
        LocalDate chrismas = LocalDate.of(2020, Month.DECEMBER, 25);

        System.out.println("Util chrismas:" + independenceDay.until(chrismas));
        System.out.println("Util chrismas:" + independenceDay.until(chrismas, ChronoUnit.DAYS));

        System.out.println(LocalDate.of(2020, 1, 31).plusMonths(1));
        System.out.println(LocalDate.of(2020, 3, 31).minusMonths(1));

        DayOfWeek startOfLastMillennium = LocalDate.of(1900, 1, 1).getDayOfWeek();
        System.out.println("startOfLastMillennium:" + startOfLastMillennium);
        System.out.println(startOfLastMillennium.getValue());
        System.out.println(DayOfWeek.SATURDAY.plus(3));

    }
}
