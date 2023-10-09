package edu.hw1;

public final class Task7 {
    private Task7() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    private static int getUsedBitsCount(final int number) {
        int result = 0;
        int powerOf2 = 1;
        do {
            ++result;
            powerOf2 *= 2;
        } while (powerOf2 <= number);
        return result;
    }

    public static int rotateLeft(final int n, final int shift) {
        final int lengthBits = getUsedBitsCount(n);
        final int allOnesBitmask = (1 << lengthBits) - 1;

        final int prettyShift = shift % lengthBits;

        final int numWithoutOverflow = (n << prettyShift) & allOnesBitmask;
        final int overflowedBitsWithoutShift = n & (allOnesBitmask << (lengthBits - prettyShift));
        final int overflowed = overflowedBitsWithoutShift >> (lengthBits - prettyShift);
        return numWithoutOverflow | overflowed;
    }

    public static int rotateRight(final int n, final int shift) {
        final int lengthBits = getUsedBitsCount(n);
        final int allOnesBitmask = (1 << lengthBits) - 1;

        final int prettyShift = shift % lengthBits;

        final int numWithoutOverflow = (n >> prettyShift) & allOnesBitmask;
        final int overflowedBitsWithoutShift = n & (allOnesBitmask >> (lengthBits - prettyShift));
        final int overflowed = overflowedBitsWithoutShift << (lengthBits - prettyShift);
        return numWithoutOverflow | overflowed;
    }
}
