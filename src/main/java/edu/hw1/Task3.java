package edu.hw1;

public class Task3 {
    private static int min(final int[] array) throws IndexOutOfBoundsException {
        int result = array[0];
        for (int value : array) {
            if (value < result) {
                result = value;
            }
        }
        return result;
    }

    private static int max(final int[] array) throws IndexOutOfBoundsException {
        int result = array[0];
        for (int value : array) {
            if (value > result) {
                result = value;
            }
        }
        return result;
    }

    public static boolean isNestable(final int[] array_1, final int[] array_2) {
        if (array_1.length == 0 && array_2.length != 0) {
            return true; // {}, {1, 2, 3}
        } else if (array_1.length == 0) {
            return false; // {}, {}
        } else if (array_2.length == 0) {
            return false; // {1, 2, 3}, {}
        }
        return min(array_1) > min(array_2) && max(array_1) < max(array_2);
    }
}
