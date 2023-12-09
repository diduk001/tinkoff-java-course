package edu.project3;

public class NonAtomicPixel implements Pixel {
    private final NonAtomicColor c;
    private int hitCount;

    public NonAtomicPixel(MyColor c, int hitCount) {
        this.c = new NonAtomicColor(c);
        this.hitCount = hitCount;
    }

    @Override
    public NonAtomicColor getColor() {
        return c;
    }

    @Override
    public void mixWith(MyColor other) {
        c.mixWith(other);
    }

    @Override
    public void hit() {
        hitCount++;
    }

    @Override
    public double getNormal() {
        return Math.log10(hitCount);
    }

    @Override
    public void normalize(double gamma) {
        c.normalize(gamma, getNormal());
    }
}
