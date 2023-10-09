package edu.hw1;

public final class Task1 {
    private Task1() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    @SuppressWarnings("checkstyle:MagicNumber") public static int minutesToSeconds(final String stringRepresentation) {
        String[] splittedString = stringRepresentation.split(":");
        if (splittedString.length != 2
            || splittedString[0].isEmpty()
            || splittedString[1].isEmpty()) {
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
