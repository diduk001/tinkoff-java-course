package edu.hw7.Task4;

public final class SequentialMonteCarloMethod {
    private static final double FOUR = 4.0;

    private SequentialMonteCarloMethod() {
        throw new UnsupportedOperationException("This is a utility class.");
    }

    public static double getPi(long totalCount) {
        if (totalCount <= 0) {
            throw new IllegalArgumentException("N must be positive");
        }

        long circleCount = 0;
        for (long i = 0; i < totalCount; i++) {
            double x = Math.random();
            double y = Math.random();

            if (x * x + y * y <= 1) {
                circleCount++;
            }
        }

        return FOUR * circleCount / totalCount;
    }
}
