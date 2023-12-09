package edu.project3;

public class NonAtomicPixel implements Pixel {
    private final NonAtomicColor c;
    private int hitCount;

    public NonAtomicPixel(NonAtomicColor c, int hitCount) {
        this.c = c;
        this.hitCount = hitCount;
    }

    public NonAtomicColor getColor() {
        return c;
    }

    public void mixWith(MyColor other) {
        c.mixWith(other);
    }

    public void hit() {
        hitCount++;
    }

    public double getNormal() {
        return Math.log10(hitCount);
    }

    public void normalize(double gamma) {
        c.normalize(gamma, getNormal());
    }
}
