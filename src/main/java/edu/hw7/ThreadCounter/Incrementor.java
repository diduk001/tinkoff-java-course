package edu.hw7.ThreadCounter;

import java.util.concurrent.atomic.AtomicInteger;

public class Incrementor extends Thread {
    private final AtomicInteger counter;
    private final int delta;

    public Incrementor(AtomicInteger counter, int delta) {
        this.counter = counter;
        this.delta = delta;
    }

    @Override
    public void run() {
        counter.addAndGet(delta);
    }
}
