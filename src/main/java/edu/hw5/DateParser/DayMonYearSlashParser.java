package edu.hw5.DateParser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class DayMonYearSlashParser implements DateFormatHandler {
    private static final int PRIORITY = 1;

    @Override
    public boolean canParse(String string) {
        final Pattern pattern = Pattern.compile("^\\d{1,2}/\\d{1,2}/(\\d{2}|\\d{4})$");
        return pattern.matcher(string).find();
    }

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public LocalDate parse(String string) {
        // For 1/3/20-like cases
        final int ONE_HUNDRED = 100;
        final int TWO_THOUSAND = 2000;

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/y");
        LocalDate result = LocalDate.parse(string, formatter);
        if (result.getYear() < ONE_HUNDRED) {
            result = result.plusYears(TWO_THOUSAND);
        }
        return result;
    }
}
