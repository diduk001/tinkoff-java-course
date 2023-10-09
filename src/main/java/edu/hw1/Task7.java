package edu.hw1;

public class Task7 {
    private static int getUsedBitsCount(final int number) {
        int result = 0;
        int powerOf2 = 1;
        do {
            ++result;
            powerOf2 *= 2;
        } while (powerOf2 <= number);
        return result;
    }

    public static int rotateLeft(final int n, int shift) {
        final int lengthBits = getUsedBitsCount(n);
        final int allOnesBitmask = (1 << lengthBits) - 1;

        shift %= lengthBits;

        final int numWithoutOverflow = (n << shift) & allOnesBitmask;
        final int overflowedBitsWithoutShift = n & (allOnesBitmask << (lengthBits - shift));
        final int overflowed = overflowedBitsWithoutShift >> (lengthBits - shift);
        return numWithoutOverflow | overflowed;
    }

    public static int rotateRight(final int n, int shift) {
        final int lengthBits = getUsedBitsCount(n);
        final int allOnesBitmask = (1 << lengthBits) - 1;

        shift %= lengthBits;

        final int numWithoutOverflow = (n >> shift) & allOnesBitmask;
        final int overflowedBitsWithoutShift = n & (allOnesBitmask >> (lengthBits - shift));
        final int overflowed = overflowedBitsWithoutShift << (lengthBits - shift);
        return numWithoutOverflow | overflowed;
    }
}
