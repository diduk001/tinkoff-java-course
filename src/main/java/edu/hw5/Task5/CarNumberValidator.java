package edu.hw5.Task5;

import java.util.regex.Pattern;

public final class CarNumberValidator {
    private CarNumberValidator() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    public static boolean validateCarNumber(final String number) {
        final Pattern numberPattern = Pattern.compile(
            "^[АВЕКМНОРСТУХ]\\d\\d\\d[АВЕКМНОРСТУХ][АВЕКМНОРСТУХ]\\d{2,3}$"
        );
        return numberPattern.matcher(number).find();
    }
}
