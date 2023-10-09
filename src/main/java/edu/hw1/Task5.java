package edu.hw1;

public class Task5 {
    private static String reverseString(final String plainString) {
        String reversedString = "";
        for (int i = plainString.length() - 1; i > -1; i--) {
            reversedString += plainString.charAt(i);
        }

        return reversedString;
    }

    private static String getDescendant(final String plainString) {
        String descendantString = "";
        for (int i = 0; i < plainString.length(); i += 2) {
            int firstDigit = plainString.charAt(i) - '0';
            int secondDigit = (i + 1 < plainString.length()) ? plainString.charAt(i + 1) - '0' : 0;
            descendantString += String.valueOf(firstDigit + secondDigit);
        }
        return descendantString;
    }

    private static boolean isPalindromeDescendantString(final String numberAsString) {
        if (numberAsString.length() < 2) {
            return true;
        }

        String reversedString = reverseString(numberAsString);
        if (numberAsString.equals(reversedString)) {
            return true;
        }

        String descendantString = getDescendant(numberAsString);
        return isPalindromeDescendantString(descendantString);
    }

    public static boolean isPalindromeDescendant(final Integer number) {
        if (number < 0) {
            return false;
        }
        return isPalindromeDescendantString(number.toString());
    }
}
