package edu.hw3.Task1;

import java.util.HashMap;

public final class AtbashEncrypt {
    private static final HashMap<Character, Character> ATBASH_TRANSLATE_DICT = new HashMap<>();

    private AtbashEncrypt() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    private static void initializeTranslateDictAndAlphabet() {
        final int ALPHABET_SIZE = 26;
        final char[] LOWERCASE_ALPHABET = new char[ALPHABET_SIZE];
        final char[] UPPERCASE_ALPHABET = new char[ALPHABET_SIZE];

        for (char letter = 'a'; letter <= 'z'; letter++) {
            int idx = letter - 'a';
            LOWERCASE_ALPHABET[idx] = letter;
        }

        for (char letter = 'A'; letter <= 'Z'; letter++) {
            int idx = letter - 'A';
            UPPERCASE_ALPHABET[idx] = letter;
        }

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
            initializeTranslateDictAndAlphabet();
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
