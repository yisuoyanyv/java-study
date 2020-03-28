package coreJavaVolume2AdvancedFeatures.datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * @author zhangjinglong
 * @date 2020-03-28-6:09 下午
 */

public class Formatting {
    public static void main(String[] args) {
        ZonedDateTime appllollanuch = ZonedDateTime.of(1969, 7, 16,
                9, 32, 0, 0, ZoneId.of("America/New_York"));

        String formatted = DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(appllollanuch);
        //1969-07-16T09:32:00-04:00
        System.out.println(formatted);

        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
        formatted = formatter.format(appllollanuch);
        //1969年7月16日
        System.out.println(formatted);

        formatted = formatter.withLocale(Locale.FRANCE).format(appllollanuch);
        //16 juillet 1969
        System.out.println(formatted);

        formatter = DateTimeFormatter.ofPattern("E yyyy-MM-dd HH:mm");
        formatted = formatter.format(appllollanuch);
        //星期三 1969-07-16 09:32
        System.out.println(formatted);

        LocalDate churchsBirthday = LocalDate.parse("1903-06-14");
        System.out.println("churchsBirthday:" + churchsBirthday);
        appllollanuch = ZonedDateTime.parse("1969-07-16 03:32:00-0400",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssxx"));
        System.out.println("appollolllaunch:" + appllollanuch);

        for (DayOfWeek w : DayOfWeek.values()) {
            System.out.print(w.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " ");
        }

    }
}
