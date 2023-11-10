package edu.hw5.DateParser;

import java.time.LocalDate;

public interface DateFormatHandler {
    boolean canParse(String string);

    int getPriority();

    LocalDate parse(String string);
}
