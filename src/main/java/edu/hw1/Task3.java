package edu.hw1;

public class Task3 {
    private static int min(final int[] array) throws IndexOutOfBoundsException {
        if (array.length == 0) {
            throw new IndexOutOfBoundsException();
        }
        int result = array[0];
        for (int value : array) {
            if (value < result) {
                result = value;
            }
        }
        return result;
    }

    private static int max(final int[] array) throws IndexOutOfBoundsException {
        if (array.length == 0) {
            throw new IndexOutOfBoundsException();
        }
        int result = array[0];
        for (int value : array) {
            if (value > result) {
                result = value;
            }
        }
        return result;
    }

    public static boolean isNestable(final int[] array_1, final int[] array_2) {
        return min(array_1) > min(array_2) || max(array_1) < max(array_2);
    }
}
