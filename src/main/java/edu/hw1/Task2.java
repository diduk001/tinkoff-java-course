package edu.hw1;

public final class Task2 {
    static final int RADIX = 10;

    private Task2() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    public static int countDigits(final Integer number) {
        Integer mutableNumber = number;
        if (number < 0) {
            mutableNumber *= -1;
        }

        int digitsCount = 0;
        do {
            ++digitsCount;
            mutableNumber /= RADIX;
        } while (mutableNumber > 0);

        return digitsCount;
    }
}
