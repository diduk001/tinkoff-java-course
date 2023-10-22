package edu.hw3;

import java.util.HashMap;

final public class Task1 {
    private final static char[] LOWERCASE_ALPHABET =
        {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z'};
    private final static char[] UPPERCASE_ALPHABET =
        {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
            'W', 'X', 'Y', 'Z'};
    private static final HashMap<Character, Character> ATBASH_TRANSLATE_DICT = new HashMap<>();

    private Task1() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    private static void initializeTranslateDict() {
        for (int i = 0; i < LOWERCASE_ALPHABET.length; i++) {
            Character letterFrom = LOWERCASE_ALPHABET[i];
            Character letterTo = LOWERCASE_ALPHABET[LOWERCASE_ALPHABET.length - i - 1];
            ATBASH_TRANSLATE_DICT.put(letterFrom, letterTo);
        }

        for (int i = 0; i < UPPERCASE_ALPHABET.length; i++) {
            Character letterFrom = UPPERCASE_ALPHABET[i];
            Character letterTo = UPPERCASE_ALPHABET[UPPERCASE_ALPHABET.length - i - 1];
            ATBASH_TRANSLATE_DICT.put(letterFrom, letterTo);
        }
    }

    public static String atbash(String plaintext) {
        if (ATBASH_TRANSLATE_DICT.isEmpty()) {
            initializeTranslateDict();
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            Character curChar = plaintext.charAt(i);
            Character newChar = ATBASH_TRANSLATE_DICT.getOrDefault(curChar, curChar);
            result.append(newChar);
        }

        return result.toString();
    }
}
