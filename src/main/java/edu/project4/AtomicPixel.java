package edu.project4;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicPixel implements Pixel {
    private final AtomicColor c;
    private final AtomicInteger hitCount;

    public AtomicPixel(MyColor c, int hitCount) {
        this.c = new AtomicColor(c);
        this.hitCount = new AtomicInteger(hitCount);
    }

    @Override
    public MyColor getColor() {
        return c;
    }

    @Override
    public void mixWith(MyColor other) {
        c.mixWith(other);
    }

    @Override
    public void hit() {
        hitCount.getAndIncrement();
    }

    @Override
    public double getNormal() {
        return Math.log10(hitCount.get());
    }

    @Override
    public void normalize(double gamma) {
        c.normalize(gamma, getNormal());
    }
}
