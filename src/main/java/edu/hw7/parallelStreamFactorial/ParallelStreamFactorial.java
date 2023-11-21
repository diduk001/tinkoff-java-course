package edu.hw7.parallelStreamFactorial;

import java.util.stream.LongStream;

public final class ParallelStreamFactorial {
    private ParallelStreamFactorial() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    public static long getFactorial(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("N be positive");
        }

        return LongStream.range(1, n + 1).parallel().reduce(1, (a, b) -> a * b);
    }
}
