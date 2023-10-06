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
        final int all_1_mask = (1 << bits_cnt) - 1;

        shift %= bits_cnt;

        final int without_overflow = (n << shift) & all_1_mask;
        final int overflowed_without_shift = n & (all_1_mask << (bits_cnt - shift));
        final int overflowed = overflowed_without_shift >> (bits_cnt - shift);
        return without_overflow | overflowed;
    }

    public static int rotateRight(final int n, int shift) {
        final int bits_cnt = getUsedBitsCount(n);
        final int all_1_mask = (1 << bits_cnt) - 1;

        shift %= bits_cnt;

        final int without_overflow = (n >> shift) & all_1_mask;
        final int overflowed_without_shift = n & (all_1_mask >> (bits_cnt - shift));
        final int overflowed = overflowed_without_shift << (bits_cnt - shift);
        return without_overflow | overflowed;
    }
}
