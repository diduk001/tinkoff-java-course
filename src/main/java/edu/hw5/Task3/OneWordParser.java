package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class OneWordParser implements DateFormatHandler {
    private static final int PRIORITY = 2;

    @Override
    public boolean canParse(String string) {
        final Pattern pattern = Pattern.compile("^yesterday|today|tomorrow$");
        return pattern.matcher(string).find();
    }

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public LocalDate parse(String string) {
        return switch (string) {
            case "yesterday" -> LocalDate.now().minusDays(1);
            case "today" -> LocalDate.now();
            case "tomorrow" -> LocalDate.now().plusDays(1);
            default -> throw new IllegalArgumentException(string + " is not \"yesterday\", \"today\", or \"tomorrow\"");
        };
    }
}
