package edu.hw5.Task3;

import java.time.LocalDate;

public interface DateFormatHandler {
    boolean canParse(String string);

    int getPriority();

    LocalDate parse(String string);
}
