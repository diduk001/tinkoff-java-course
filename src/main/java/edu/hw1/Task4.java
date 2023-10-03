package edu.hw1;

public class Task4 {
    public static String fixString(final String broken_string) {
        String result = "";
        for (int i = 0; i < broken_string.length(); i += 2) {
            if (i + 1 < broken_string.length()) {
                result += broken_string.charAt(i + 1);
            }
            result += broken_string.charAt(i);
        }

        return result;
    }
}
