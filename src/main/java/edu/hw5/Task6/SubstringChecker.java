package edu.hw5.Task6;

import java.util.regex.Pattern;

public final class SubstringChecker {
    private SubstringChecker() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    public static boolean isSubstring(final String s, final String t) {
        Pattern patternMatcher = Pattern.compile("^.*" + Pattern.quote(s) + ".*$");
        return patternMatcher.matcher(t).find();
    }
}
