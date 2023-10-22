package edu.hw3;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

final public class Task4 {
    static TreeMap<Integer, String> intToRomanDict = new TreeMap<>(Comparator.reverseOrder());

    private Task4() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    static public String convertToRoman(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Value must be non-negative");
        }

        if (intToRomanDict.isEmpty()) {
            intToRomanDict.put(1000, "M");
            intToRomanDict.put(900, "CM");
            intToRomanDict.put(500, "D");
            intToRomanDict.put(400, "CD");
            intToRomanDict.put(100, "C");
            intToRomanDict.put(90, "XC");
            intToRomanDict.put(50, "L");
            intToRomanDict.put(40, "XL");
            intToRomanDict.put(10, "X");
            intToRomanDict.put(9, "IX");
            intToRomanDict.put(5, "V");
            intToRomanDict.put(4, "IV");
            intToRomanDict.put(1, "I");
        }

        int mutableValue = value;
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Integer, String> entry : intToRomanDict.entrySet()) {
            int divided = mutableValue / entry.getKey();
            result.append(entry.getValue().repeat(divided));
            mutableValue = mutableValue % entry.getKey();
        }

        return result.toString();
    }
}
