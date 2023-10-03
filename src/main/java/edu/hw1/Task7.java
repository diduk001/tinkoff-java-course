package edu.hw1;

public class Task7 {
    private static int getUsedBitsCount(final int num) {
        int result = 0;
        int power_of_2 = 1;
        do {
            ++result;
            power_of_2 *= 2;
        } while (power_of_2 <= num);
        return result;
    }

    public static int rotateLeft(final int n, int shift) {
        final int bits_cnt = getUsedBitsCount(n);
        shift %= bits_cnt;
        final int all_1_mask = (1 << bits_cnt) - 1;
        final int will_be_cropped = n & (all_1_mask << (bits_cnt - shift));
        return (n << shift) | (will_be_cropped >> (bits_cnt - shift));
    }

    public static int rotateRight(final int n, int shift) {
        final int bits_cnt = getUsedBitsCount(n);
        shift %= bits_cnt;
        final int will_be_cropped_mask = (1 << shift) - 1;
        final int will_be_cropped = n & will_be_cropped_mask;
        return (n >> shift) | (will_be_cropped << (bits_cnt - shift));
    }
}
