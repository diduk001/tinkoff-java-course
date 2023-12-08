package edu.hw5.Task7;

import java.util.regex.Pattern;

public final class OneZeroRegex {
    private OneZeroRegex() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    // Task 7

    public static boolean lengthIsNoLessThan3AndThirdSymbolIs0(final String string) {
        final Pattern pattern = Pattern.compile("^[01]{2}0[01]*$");
        return pattern.matcher(string).find();
    }

    public static boolean startsAndEndsWithOneSymbol(final String string) {
        final Pattern pattern = Pattern.compile("(^[01]$)|(^([01])[01]*\\3$)");
        return pattern.matcher(string).find();
    }

    public static boolean lengthBetween1and3(final String string) {
        final Pattern pattern = Pattern.compile("^[01]{1,3}$");
        return pattern.matcher(string).find();
    }
}
