package edu.hw5.DateParser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class DateParser {
    private List<DateFormatHandler> handlersChain;

    public DateParser() {
        initializeChain();
    }

    private void initializeChain() {
        handlersChain = List.of(
            new YearMonDayDashParser(), new DayMonYearSlashParser(),
            new OneWordParser(), new DaysAgoParser()
        );
    }

    public Optional<LocalDate> parseDate(String date) {
        Optional<DateFormatHandler> result;
        try {
            result = handlersChain
                .stream()
                .filter(dateFormatHandler -> dateFormatHandler.canParse(date))
                .min(Comparator.comparingInt(DateFormatHandler::getPriority));
            return result.map(dateFormatHandler -> dateFormatHandler.parse(date));
        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }
}
