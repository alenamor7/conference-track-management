package com.itemis.constant;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class ConferenceConstant {

    public static final int MORNING_SESSION_MAX_DURATION = 180;

    public static LocalTime MORNING_SESSION_START_TIME = LocalTime.of(9, 0);

    // max duration of an afternoon session detects also max duration of one talk
    public static final int AFTERNOON_SESSION_MAX_DURATION = 240;

    public static LocalTime AFTERNOON_SESSION_START_TIME = LocalTime.of(13, 0);

    public static final int LIGHTNING_DURATION = 5;

    public static final DateTimeFormatter DATE_FORMATTER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("hh:mma")
            .toFormatter(Locale.US);

}
