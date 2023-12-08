package edu.hw5.Task4;

import java.util.regex.Pattern;

public final class PasswordChecker {
    private PasswordChecker() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    public static boolean checkPassword(final String password) {
        final Pattern passwordPattern = Pattern.compile("^.*[~!@#$%^&*|].*$");
        return passwordPattern.matcher(password).find();
    }
}
