package edu.hw1;

public class Task2 {
    public static int countDigits(Integer num) {
        if (num < 0) {
            num *= -1;
        }

        int result = 0;
        do {
            ++result;
            num /= 10;
        } while (num > 0);

        return result;
    }
}
