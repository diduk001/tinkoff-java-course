package edu.hw7.Task4;

import java.util.concurrent.TimeUnit;

public final class Main {
    private static final long[] POINTS_NUMBER_ARRAY = {100, 10_000, 1_000_000, 10_000_000};
    private static final int[] THREADS_NUMBER_ARRAY = {2, 5, 10, 20, 30, 50};

    private static final String INT_AND_FLOAT_FORMAT = "%d : %f%n";
    private static final int MAX_THREADS = THREADS_NUMBER_ARRAY[THREADS_NUMBER_ARRAY.length - 1];
    private static final long MAX_POINTS = POINTS_NUMBER_ARRAY[POINTS_NUMBER_ARRAY.length - 1];

    private Main() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    @SuppressWarnings("checkstyle:RegexpSinglelineJava")
    public static void main(String[] args) {

        final ParallelMonteCarlo maxPerformanceMonteCarlo = new ParallelMonteCarlo(MAX_THREADS);

        System.out.println("Accuracy test (parallel version with " + MAX_THREADS + " threads):");
        System.out.println("Points number : Accuracy");
        for (long points : POINTS_NUMBER_ARRAY) {
            final double parallelVersionValue = maxPerformanceMonteCarlo.getPi(points);
            final double accuracy = StrictMath.abs(StrictMath.PI - parallelVersionValue);

            System.out.printf(INT_AND_FLOAT_FORMAT, points, accuracy);
        }
        System.out.println();

        System.out.println("Performance test (for " + MAX_POINTS + " points):");
        System.out.println("Threads number : Boost");
        for (int threads : THREADS_NUMBER_ARRAY) {
            final ParallelMonteCarlo curParallel = new ParallelMonteCarlo(threads);

            final long parallelStart = System.nanoTime();
            curParallel.getPi(MAX_POINTS);
            final long parallelEnd = System.nanoTime();
            final long parallelMillis = TimeUnit.NANOSECONDS.toMillis(parallelEnd - parallelStart);

            final long sequentialStart = System.nanoTime();
            SequentialMonteCarloMethod.getPi(MAX_POINTS);
            final long sequentialEnd = System.nanoTime();
            final long sequentialMillis = TimeUnit.NANOSECONDS.toMillis(sequentialEnd - sequentialStart);

            final double boost = (double) sequentialMillis / parallelMillis;

            System.out.printf(INT_AND_FLOAT_FORMAT, threads, boost);
        }
    }
}
