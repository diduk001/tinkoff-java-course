package edu.hw1;

public class Task1 {
    public static int minutesToSeconds(final String string_representation) {
        String[] splitted_string = string_representation.split(":");
        final int minutes_int = Integer.parseInt(splitted_string[0]);
        final int seconds_int = Integer.parseInt(splitted_string[1]);
        if (seconds_int >= 60) {
            return -1;
        }
        return minutes_int * 60 + seconds_int;
    }
}
