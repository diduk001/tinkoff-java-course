package edu.hw1;

import static java.lang.Math.pow;

public final class Task6 {
    private static final int K = 6174;
    private static final int RADIX = 10;
    private static final int DIGITS_N = 4;

    private Task6() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    private static int[] intToDigits(final Integer number) {
        Integer mutableNumber = number;
        int[] result = new int[DIGITS_N];
        for (int i = DIGITS_N - 1; i > -1; i--) {
            result[i] = mutableNumber % RADIX;
            mutableNumber /= RADIX;
        }
        return result;
    }

    private static int digitsToInt(final int[] digitsArray) {
        int result = 0;
        for (int i = 0; i < DIGITS_N; i++) {
            result *= RADIX;
            result += digitsArray[i];
        }
        return result;
    }

    private static void sortAscending(int[] array) {
        int arrayLength = array.length;
        for (int i = 0; i < arrayLength - 1; i++) {
            for (int j = 0; i + j < arrayLength - 1; j++) {
                if (array[j] <= array[j + 1]) {
                    continue;
                }

                int temp = array[j];
                array[j] = array[j + 1];
                array[j + 1] = temp;
            }
        }
    }

    private static void reverseArray(int[] array) {
        int arrayLength = array.length;
        for (int i = 0; i < arrayLength - i - 1; i++) {
            int temp = array[i];
            array[i] = array[arrayLength - i - 1];
            array[arrayLength - i - 1] = temp;
        }
    }

    public static int countK(int number) {
        if (number == K) {
            return 0;
        } else if (number <= 0 || number >= pow(RADIX, DIGITS_N)) {
            return -1;
        }

        int[] digits = intToDigits(number);
        sortAscending(digits);
        int ascendingDigitsNum = digitsToInt(digits);
        reverseArray(digits);
        int descendingDigitsNum = digitsToInt(digits);
        int difference = ascendingDigitsNum - descendingDigitsNum;
        if (difference < 0) {
            difference *= -1;
        }
        return 1 + countK(difference);
    }
}

