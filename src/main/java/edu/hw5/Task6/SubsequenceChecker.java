package edu.hw5.Task6;

import java.util.regex.Pattern;

public final class SubsequenceChecker {
    private SubsequenceChecker() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    public static boolean isSubstring(final String s, final String t) {
        StringBuilder expression = new StringBuilder();
        expression.append("^.*");
        for (int i = 0; i < s.length(); i++) {
            expression.append(Pattern.quote(String.valueOf(s.charAt(i))));
            expression.append(".*");
        }
        expression.append("$");
        Pattern patternMatcher = Pattern.compile(expression.toString());
        return patternMatcher.matcher(t).find();
    }
}
