package edu.hw1;

public class Task6 {
    private static final int K = 6174;

    private static int[] intToDigits(Integer number) {
        int[] result = new int[4];
        for (int i = 3; i > -1; i--) {
            result[i] = number % 10;
            number /= 10;
        }
        return result;
    }

    private static int digitsToInt(final int[] digitsArray) {
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result *= 10;
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
        } else if (number <= 0 || number > 9999) {
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

