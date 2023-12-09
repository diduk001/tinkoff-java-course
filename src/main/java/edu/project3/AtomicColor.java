package edu.project3;

import java.awt.Color;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;

public class AtomicColor implements MyColor {
    private final AtomicInteger r;
    private final AtomicInteger g;
    private final AtomicInteger b;

    public AtomicColor(int r, int g, int b) {
        if (!(MIN_VALUE <= r && r <= MAX_VALUE)) {
            throw new IllegalArgumentException(String.format("R value is not between %d and %d", MIN_VALUE, MAX_VALUE));
        } else if (!(MIN_VALUE <= g && g <= MAX_VALUE)) {
            throw new IllegalArgumentException(String.format("G value is not between %d and %d", MIN_VALUE, MAX_VALUE));
        } else if (!(MIN_VALUE <= b && b <= MAX_VALUE)) {
            throw new IllegalArgumentException(String.format("B value is not between %d and %d", MIN_VALUE, MAX_VALUE));
        }

        this.r = new AtomicInteger(r);
        this.g = new AtomicInteger(g);
        this.b = new AtomicInteger(b);
    }

    public AtomicColor(MyColor other) {
        this.r = new AtomicInteger(other.getR());
        this.g = new AtomicInteger(other.getG());
        this.b = new AtomicInteger(other.getB());
    }

    @Override
    public int getR() {
        return r.get();
    }

    @Override
    public int getG() {
        return g.get();
    }

    @Override
    public int getB() {
        return b.get();
    }

    @Override
    public void mixWith(MyColor other) {
        this.r.updateAndGet((r) -> (r + other.getR()) / 2);
        this.g.updateAndGet((g) -> (g + other.getG()) / 2);
        this.b.updateAndGet((b) -> (b + other.getB()) / 2);
    }

    @Override
    public void normalize(double gamma, double curNormal) {
        final IntUnaryOperator gammaNormalize =
            (x) -> Math.min(Math.max(MIN_VALUE, (int) (x * Math.pow(curNormal, 1.0 / gamma))), MAX_VALUE);
        this.r.updateAndGet(gammaNormalize);
        this.g.updateAndGet(gammaNormalize);
        this.b.updateAndGet(gammaNormalize);
    }

    @Override
    public Color toAWTColor() {
        final int MIN_AWT = 0;
        final int MAX_AWT = 255;

        if (MIN_VALUE == MIN_AWT && MAX_VALUE == MAX_AWT) {
            return new Color(r.get(), g.get(), b.get());
        } else {
            return new Color(
                ((r.get() - MIN_VALUE) / (MAX_VALUE - MIN_VALUE)) * (MAX_AWT - MIN_AWT) + MIN_AWT,
                ((g.get() - MIN_VALUE) / (MAX_VALUE - MIN_VALUE)) * (MAX_AWT - MIN_AWT) + MIN_AWT,
                ((b.get() - MIN_VALUE) / (MAX_VALUE - MIN_VALUE)) * (MAX_AWT - MIN_AWT) + MIN_AWT
            );
        }
    }
}
