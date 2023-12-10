package edu.hw5.Task2;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.util.ArrayList;
import java.util.List;

public final class AllFriday13thFinder {
    private AllFriday13thFinder() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    public static List<LocalDate> findAllFriday13thInYear(int year) {
        final TemporalAdjuster adjuster = new NextFriday13thFinder();
        ArrayList<LocalDate> result = new ArrayList<>();
        LocalDate date = LocalDate.of(year, 1, 1).with(adjuster);
        while (date.getYear() == year) {
            result.add(date);
            date = date.with(adjuster);
        }

        return result;
    }
}
