package com.arathy.transactionmanagement.utils;

import java.time.ZonedDateTime;

import static java.time.ZoneOffset.UTC;

public class DateTimeUtils {
    public static long getEpochMillisForUTCForLast60s() {
        return ZonedDateTime
                .now(UTC)
                .minusSeconds(60)
                .toInstant()
                .toEpochMilli();
    }
}
