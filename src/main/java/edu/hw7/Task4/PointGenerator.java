package edu.hw7.Task4;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class PointGenerator extends Thread {
    private final AtomicInteger circleCount;
    private final long pointsNumber;

    public PointGenerator(AtomicInteger circleCount, long pointsNumber) {
        this.circleCount = circleCount;
        this.pointsNumber = pointsNumber;
    }

    @Override
    public void run() {
        int delta = 0;
        for (int i = 0; i < this.pointsNumber; i++) {
            double x = ThreadLocalRandom.current().nextDouble(2.0) - 1.0;
            double y = ThreadLocalRandom.current().nextDouble(2.0) - 1.0;

            if (x * x + y * y <= 1) {
                delta++;
            }
        }
        circleCount.addAndGet(delta);
    }
}
