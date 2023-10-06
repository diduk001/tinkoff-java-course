package edu.hw1;

public class Task6 {
    private static final int K = 6174;

    private static int[] intToDigits(Integer num) {
        int[] result = new int[4];
        for (int i = 3; i > -1; i--) {
            result[i] = num % 10;
            num /= 10;
        }
        return result;
    }

    private static int digitsToInt(final int[] digits) {
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result *= 10;
            result += digits[i];
        }
        return result;
    }

    private static void sortAsc(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; i + j < n - 1; j++) {
                if (arr[j] <= arr[j + 1]) {
                    continue;
                }

                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }

    private static void reverse(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - i - 1; i++) {
            int temp = arr[i];
            arr[i] = arr[n - i - 1];
            arr[n - i - 1] = temp;
        }
    }

    public static int countK(int num) {
        if (num == K) {
            return 0;
        } else if (num <= 0 || num > 9999) {
            return -1;
        }

        int[] digits = intToDigits(num);
        sortAsc(digits);
        int num_ascending = digitsToInt(digits);
        reverse(digits);
        int num_descending = digitsToInt(digits);
        int difference = num_ascending - num_descending;
        if (difference < 0) {
            difference *= -1;
        }
        return 1 + countK(difference);
    }
}

