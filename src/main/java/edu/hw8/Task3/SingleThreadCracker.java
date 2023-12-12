package edu.hw8.Task3;

import java.util.HashMap;
import java.util.Map;

public final class SingleThreadCracker implements PasswordCracker {
    String curPassword;
    int maxLength;

    public SingleThreadCracker(int minLength, int maxLength) {
        if (minLength <= 0 || maxLength <= 0) {
            throw new IllegalArgumentException("minLength and maxLength must be positive values");
        } else if (minLength > maxLength) {
            throw new IllegalArgumentException("minLength must be not greater than maxLength");
        }

        this.curPassword = String.valueOf(ALPHABET.charAt(0)).repeat(minLength);
        this.maxLength = maxLength;
    }

    public static String nextByAlphabet(final String current) {
        if (current.isEmpty()) {
            return current;
        }
        int pivotIdx = current.length() - 1;
        while (pivotIdx >= 0
            && current.charAt(pivotIdx) == ALPHABET.charAt(ALPHABET.length() - 1)) {
            pivotIdx--;
        }
        if (pivotIdx == -1) {
            return String.valueOf(ALPHABET.charAt(0)).repeat(current.length() + 1);
        }
        return getNextStringWithPivot(current, pivotIdx);
    }

    private static String getNextStringWithPivot(final String current, int pivotIdx) {
        final StringBuilder result = new StringBuilder();
        for (int i = 0; i < current.length(); i++) {
            if (i < pivotIdx) {
                result.append(current.charAt(i));
            } else if (i == pivotIdx) {
                char curChar = current.charAt(i);
                int curCharIdx = ALPHABET.indexOf(curChar);
                char nextChar = ALPHABET.charAt(curCharIdx + 1);
                result.append(nextChar);
            } else {
                result.append(ALPHABET.charAt(0));
            }
        }
        return result.toString();
    }

    @Override
    public String crackSingle(String hash) {
        while (this.curPassword.length() <= this.maxLength) {
            final String hashed = PasswordCracker.md5Hash(this.curPassword);
            if (hashed.equals(hash)) {
                return this.curPassword;
            }

            this.curPassword = nextByAlphabet(this.curPassword);
        }
        return null;
    }

    @Override
    public Map<String, String> crackDict(Map<String, String> hashDict) {
        final Map<String, String> result = new HashMap<>();
        while (this.curPassword.length() <= this.maxLength) {
            final String hashed = PasswordCracker.md5Hash(this.curPassword);
            if (hashDict.containsKey(hashed)) {
                final String username = hashDict.get(hashed);
                result.put(username, this.curPassword);
            }

            this.curPassword = nextByAlphabet(this.curPassword);
        }
        return result;
    }
}
