package edu.hw1;

public class Task4 {
    public static String fixString(final String brokenString) {
        String fixedString = "";
        for (int i = 0; i < brokenString.length(); i += 2) {
            if (i + 1 < brokenString.length()) {
                fixedString += brokenString.charAt(i + 1);
            }
            fixedString += brokenString.charAt(i);
        }

        return fixedString;
    }
}
