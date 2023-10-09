package edu.hw1;

public final class Task2 {
    private Task2() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    @SuppressWarnings("checkstyle:MagicNumber") public static int countDigits(final Integer number) {
        Integer mutableNumber = number;
        if (number < 0) {
            mutableNumber *= -1;
        }

        int digitsCount = 0;
        do {
            ++digitsCount;
            mutableNumber /= 10;
        } while (mutableNumber > 0);

        return digitsCount;
    }
}
