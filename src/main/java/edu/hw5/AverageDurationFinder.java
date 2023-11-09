package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;

public final class AverageDurationFinder {
    private static final Pattern DURATION_PATTERN =
        Pattern.compile("^\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2} - \\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2}$");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");

    private AverageDurationFinder() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    private static Duration parseDuration(String period) {
        if (!DURATION_PATTERN.matcher(period).find()) {
            throw new IllegalArgumentException(period + " doesn't match regular expression");
        }
        final String[] parts = period.split(" - ");
        final LocalDateTime from = LocalDateTime.parse(parts[0], DATE_TIME_FORMATTER);
        final LocalDateTime to = LocalDateTime.parse(parts[1], DATE_TIME_FORMATTER);
        return Duration.between(from, to);
    }

    public static Duration findAverageDuration(List<String> dates) {
        Duration sum = Duration.ZERO;
        if (dates.isEmpty()) {
            return sum;
        }
        for (String fromToString : dates) {
            sum = sum.plus(parseDuration(fromToString));
        }
        return sum.dividedBy(dates.size());
    }
}
