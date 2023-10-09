package edu.hw1;

public class Task2 {
    public static int countDigits(Integer number) {
        if (number < 0) {
            number *= -1;
        }

        int digitsCount = 0;
        do {
            ++digitsCount;
            number /= 10;
        } while (number > 0);

        return digitsCount;
    }
}
