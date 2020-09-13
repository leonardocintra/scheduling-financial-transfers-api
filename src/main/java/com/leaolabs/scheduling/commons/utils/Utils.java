package com.leaolabs.scheduling.commons.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Utils {
    public long getDaysDiff(final LocalDate localDate) {
        return ChronoUnit.DAYS.between(LocalDate.now(), localDate);
    }

    public double calculatePercentage(final double percentage, final double amount) {
        return (amount * percentage) / 100;
    }
}
