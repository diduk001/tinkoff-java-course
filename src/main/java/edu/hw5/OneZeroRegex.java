package edu.hw5;

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

    // Task 8

    public static boolean oddLength(final String string) {
        final Pattern pattern = Pattern.compile("^[01]([01][01])*$");
        return pattern.matcher(string).find();
    }

    public static boolean oddLengthZeroOrEvenLengthOne(final String string) {
        final Pattern pattern = Pattern.compile("(^0([01][01])*$)|(^1[01]([01][01])*$)");
        return pattern.matcher(string).find();
    }

    public static boolean zeroCountDividesByThree(final String string) {
        final Pattern pattern = Pattern.compile("^1*(01*?01*?01*?)*$");
        return pattern.matcher(string).find();
    }

    public static boolean no11And111(final String string) {
        final Pattern pattern = Pattern.compile("^(?!11$|111$)[01]*$");
        return pattern.matcher(string).find();
    }

    public static boolean oddSymbolsAre1(final String string) {
        final Pattern pattern = Pattern.compile("^1([01]1)*[01]?$");
        return pattern.matcher(string).find();
    }

    public static boolean notLessThanTwoZeroesAndNoMoreThanOneOne(final String string) {
        final Pattern pattern = Pattern.compile("^0*?(000|100|010|001)?0*$");
        return pattern.matcher(string).find();
    }

    public static boolean noSequentialOnes(final String string) {
        final Pattern pattern = Pattern.compile("^((?!11)[01])*$");
        return pattern.matcher(string).find();
    }
}
