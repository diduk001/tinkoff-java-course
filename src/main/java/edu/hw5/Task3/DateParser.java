package edu.hw5.Task3;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class DateParser {
    private List<DateFormatHandler> handlersChain;

    public DateParser() {
        initializeChain(List.of(
            new YearMonDayDashParser(), new DayMonYearSlashParser(),
            new OneWordParser(), new DaysAgoParser()
        ));
    }

    private void initializeChain(List<DateFormatHandler> handlersChain) {
        this.handlersChain = handlersChain;
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
