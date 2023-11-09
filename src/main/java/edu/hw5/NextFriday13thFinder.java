package edu.hw5;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class NextFriday13thFinder implements TemporalAdjuster {
    private static Temporal findNext13th(Temporal temporal) {
        final int THIRTEEN = 13;

        int curDate = temporal.get(ChronoField.DAY_OF_MONTH);
        if (curDate < THIRTEEN) {
            return temporal.plus(THIRTEEN - curDate, ChronoUnit.DAYS);
        }
        return temporal
            .with(TemporalAdjusters.firstDayOfNextMonth())
            .plus(THIRTEEN - 1, ChronoUnit.DAYS);
    }

    @Override
    public Temporal adjustInto(final Temporal temporal) {
        Temporal result = findNext13th(temporal);
        while (result.get(ChronoField.DAY_OF_WEEK) != DayOfWeek.FRIDAY.getValue()) {
            result = findNext13th(result);
        }
        return result;
    }
}
