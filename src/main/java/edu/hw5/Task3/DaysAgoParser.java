package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class DaysAgoParser implements DateFormatHandler {
    private static final int PRIORITY = 3;

    @Override
    public boolean canParse(String string) {
        final Pattern pattern = Pattern.compile("^((1 day)|((?!1 )\\d+ days)) ago$");
        return pattern.matcher(string).find();
    }

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public LocalDate parse(String string) {
        String[] words = string.split("\\s+");
        int days = Integer.parseInt(words[0]);
        return LocalDate.now().minusDays(days);
    }
}
