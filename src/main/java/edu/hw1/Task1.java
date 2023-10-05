package edu.hw1;

public class Task1 {
    public static int minutesToSeconds(final String string_representation) {
        String[] splitted_string = string_representation.split(":");
        if (splitted_string.length != 2 ||
            splitted_string[0].isEmpty() ||
            splitted_string[1].isEmpty()) {
            return -1;
        }
        final int minutes_int = Integer.parseInt(splitted_string[0]);
        final int seconds_int = Integer.parseInt(splitted_string[1]);
        if (0 <= minutes_int && 0 <= seconds_int && seconds_int < 60) {
            return minutes_int * 60 + seconds_int;
        }
        return -1;
    }
}
