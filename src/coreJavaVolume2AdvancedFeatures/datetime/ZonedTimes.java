package coreJavaVolume2AdvancedFeatures.datetime;

import java.time.*;

/**
 * @author zhangjinglong
 * @date 2020-03-28-5:45 下午
 */

public class ZonedTimes {
    public static void main(String[] args) {
        ZonedDateTime apollolllaunch = ZonedDateTime.of(1969, 7, 16,
                9, 32, 0, 0, ZoneId.of("America/New_York"));
        //1969-07-16T09:32-04:00[America/New_York]
        System.out.println("appollolllaunch:" + apollolllaunch);

        Instant instant = apollolllaunch.toInstant();
        System.out.println("instant:" + instant);

        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("UTC"));
        System.out.println("zonedDateTime: " + zonedDateTime);

        ZonedDateTime skipped = ZonedDateTime.of(LocalDate.of(2013, 3, 31),
                LocalTime.of(2, 30), ZoneId.of("Europe/Berlin"));
        //Constructs March 31 3:30
        System.out.println("skipped:" + skipped);

        ZonedDateTime ambiguous = ZonedDateTime.of(LocalDate.of(2013, 10, 27),
                //End of daylight saving time
                LocalTime.of(2, 30), ZoneId.of("Europe/Berlin"));
        ZonedDateTime anHourLater = ambiguous.plusHours(1);

        //ambiguous:2013-10-27T02:30+02:00[Europe/Berlin]  这个是返回的比较早的时间
        System.out.println("ambiguous:" + ambiguous);
        //夏令时恢复的时候，时间会调慢一小时，此时会出现两个相同的时间
//        anHourLater:2013-10-27T02:30+01:00[Europe/Berlin]   这个是一小时后夏令时恢复的时间
        System.out.println("anHourLater:" + anHourLater);

        ZonedDateTime meeting = ZonedDateTime.of(LocalDate.of(2013, 10, 31),
                LocalTime.of(14, 30), ZoneId.of("America/Los_Angeles"));
        System.out.println("meeting:" + meeting);

        ZonedDateTime nextMeeting = meeting.plus(Duration.ofDays(7));
        //Caution! Won't work with daylight savings time
        System.out.println("false nextMeeting:" + nextMeeting);
        nextMeeting = meeting.plus(Period.ofDays(7));//OK   Period会将夏令时考虑进去   目前夏令时在国内暂时不采用
        System.out.println("true nextMeeting:" + nextMeeting);
    }
}
