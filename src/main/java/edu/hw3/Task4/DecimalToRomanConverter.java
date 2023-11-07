package edu.hw3.Task4;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public final class DecimalToRomanConverter {
    static Map<Integer, String> decToRomanDict = new TreeMap<>(Comparator.reverseOrder());

    private DecimalToRomanConverter() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    public static String convertToRoman(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Value must be non-negative");
        }

        if (decToRomanDict.isEmpty()) {
            decToRomanDict.put(1000, "M");
            decToRomanDict.put(900, "CM");
            decToRomanDict.put(500, "D");
            decToRomanDict.put(400, "CD");
            decToRomanDict.put(100, "C");
            decToRomanDict.put(90, "XC");
            decToRomanDict.put(50, "L");
            decToRomanDict.put(40, "XL");
            decToRomanDict.put(10, "X");
            decToRomanDict.put(9, "IX");
            decToRomanDict.put(5, "V");
            decToRomanDict.put(4, "IV");
            decToRomanDict.put(1, "I");
        }

        int mutableValue = value;
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Integer, String> entry : decToRomanDict.entrySet()) {
            int divided = mutableValue / entry.getKey();
            result.append(entry.getValue().repeat(divided));
            mutableValue = mutableValue % entry.getKey();
        }

        return result.toString();
    }
}
