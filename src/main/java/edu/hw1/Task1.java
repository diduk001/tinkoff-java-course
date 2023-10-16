package edu.hw1;

public final class Task1 {
    static final int SECONDS_IN_MINUTE = 60;

    private Task1() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    public static int minutesToSeconds(final String stringRepresentation) {
        String[] splittedString = stringRepresentation.split(":");
        if (splittedString.length != 2
            || splittedString[0].isEmpty()
            || splittedString[1].isEmpty()) {
            return -1; // invalid string
        }
        try {
            final int minutes = Integer.parseInt(splittedString[0]);
            final int seconds = Integer.parseInt(splittedString[1]);
            if (0 <= minutes && 0 <= seconds && seconds < SECONDS_IN_MINUTE) {
                return minutes * SECONDS_IN_MINUTE + seconds;
            }
        } catch (NumberFormatException | NullPointerException e) {
            return -1;
        }
        return -1;
    }
}
