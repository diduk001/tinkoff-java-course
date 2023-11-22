package edu.hw7.Task4;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ParallelMonteCarlo {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final double FOUR = 4.0;
    private final int threadsNumber;

    public ParallelMonteCarlo(int threadsNumber) {
        this.threadsNumber = threadsNumber;
    }

    public double getPi(long totalCount) {
        if (totalCount <= 0) {
            throw new IllegalArgumentException("N must be positive");
        }

        final AtomicInteger circleCount = new AtomicInteger(0);

        ArrayList<Thread> threads = new ArrayList<>();
        if (totalCount <= threadsNumber) {
            for (long i = 0; i < totalCount; i++) {
                threads.add(new PointGenerator(circleCount, 1));
            }
        } else {
            long div = totalCount / (threadsNumber - 1);
            long rem = totalCount % (threadsNumber - 1);
            for (int i = 0; i < threadsNumber - 1; i++) {
                threads.add(new PointGenerator(circleCount, div));
            }
            threads.add(new PointGenerator(circleCount, rem));
        }

        for (Thread t : threads) {
            t.start();
        }

        try {
            for (Thread t : threads) {
                t.join();
            }
        } catch (InterruptedException e) {
            LOGGER.error("Error while joining threads: " + e);
        }

        return FOUR * circleCount.get() / totalCount;
    }
}
