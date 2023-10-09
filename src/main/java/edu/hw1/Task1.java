package edu.hw1;

public class Task1 {
    public static int minutesToSeconds(final String stringRepresentation) {
        String[] splittedString = stringRepresentation.split(":");
        if (splittedString.length != 2 ||
            splittedString[0].isEmpty() ||
            splittedString[1].isEmpty()) {
            return -1; // invalid string
        }
        final int minutes = Integer.parseInt(splittedString[0]);
        final int seconds = Integer.parseInt(splittedString[1]);
        if (0 <= minutes && 0 <= seconds && seconds < 60) {
            return minutes * 60 + seconds;
        }
        return -1;
    }
}
