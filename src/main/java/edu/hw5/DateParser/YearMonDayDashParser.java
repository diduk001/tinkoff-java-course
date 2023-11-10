package edu.hw5.DateParser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class YearMonDayDashParser implements DateFormatHandler {
    private static final int PRIORITY = 0;

    @Override
    public boolean canParse(String string) {
        final Pattern pattern = Pattern.compile("^\\d{4}-\\d{1,2}-\\d{1,2}$");
        return pattern.matcher(string).find();
    }

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public LocalDate parse(String string) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("y-M-d");
        return LocalDate.parse(string, formatter);
    }
}
