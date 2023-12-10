package edu.hw7.Task1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ThreadCounter {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int MAX_THREADS = 30;

    private ThreadCounter() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    public static int countWithThreads(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("N must not be negative");
        }

        AtomicInteger counter = new AtomicInteger(0);

        List<Thread> threads = new ArrayList<>();
        if (n <= MAX_THREADS) {
            for (int i = 0; i < n; i++) {
                threads.add(new Incrementor(counter, 1));
            }
        } else {
            int div = n / (MAX_THREADS - 1);
            int rem = n % (MAX_THREADS - 1);
            for (int i = 0; i < MAX_THREADS - 1; i++) {
                threads.add(new Incrementor(counter, div));
            }
            threads.add(new Incrementor(counter, rem));
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

        return counter.get();
    }
}
