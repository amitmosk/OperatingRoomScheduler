package com.BusinessLayer;
import java.time.LocalTime;

public class Constants {
    public static final LocalTime START_HOUR = LocalTime.of(10, 0);
    public static final LocalTime END_HOUR = LocalTime.of(18, 0);
    public static final int MAX_DAYS_AHEAD = 7;
    public static final int DAEMON_INTERVALS = 24 * 60 * 60 * 1000; // 1 day
}
