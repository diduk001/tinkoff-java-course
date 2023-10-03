package edu.hw1;

public class Task5 {
    private static String reverseString(final String s) {
        String result = "";
        for (int i = s.length() - 1; i > -1; i--) {
            result += s.charAt(i);
        }

        return result;
    }

    private static String getDescendant(final String s) {
        String result = "";
        for (int i = 0; i < s.length(); i += 2) {
            int first_digit = s.charAt(i) - '0';
            int second_digit = (i + 1 < s.length()) ? s.charAt(i + 1) - '0' : 0;
            result += String.valueOf(first_digit + second_digit);
        }
        return result;
    }

    private static boolean isPalindromeDescendant_string(final String num_as_string) {
        if (num_as_string.length() < 2) {
            return false;
        }

        String reversed = reverseString(num_as_string);
        if (num_as_string.equals(reversed)) {
            return true;
        }

        String descendant = getDescendant(num_as_string);
        return isPalindromeDescendant_string(descendant);
    }

    public static boolean isPalindromeDescendant(final Integer num) {
        return isPalindromeDescendant_string(num.toString());
    }
}
