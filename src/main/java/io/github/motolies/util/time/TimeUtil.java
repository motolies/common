package io.github.motolies.util.time;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeUtil {

    public static Timestamp getUtcTimestamp() {
        LocalDateTime ldt = LocalDateTime.now();
        ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
        ZonedDateTime gmt = zdt.withZoneSameInstant(ZoneId.of("GMT"));
        return Timestamp.valueOf(gmt.toLocalDateTime());
    }

}
