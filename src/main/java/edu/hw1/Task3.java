package edu.hw1;

public class Task3 {
    private static int getMin(final int[] array) {
        int result = array[0];
        for (int value : array) {
            if (value < result) {
                result = value;
            }
        }
        return result;
    }

    private static int getMax(final int[] array) {
        int result = array[0];
        for (int value : array) {
            if (value > result) {
                result = value;
            }
        }
        return result;
    }

    public static boolean isNestable(final int[] innerArray, final int[] outerArray) {
        if (innerArray.length == 0 && outerArray.length != 0) {  // {}, {1, 2, 3}
            return true;
        } else if (innerArray.length == 0) {  // {}, {}
            return false;
        } else if (outerArray.length == 0) {  // {1, 2, 3}, {}
            return false;
        }
        return getMin(innerArray) > getMin(outerArray) && getMax(innerArray) < getMax(outerArray);
    }
}
