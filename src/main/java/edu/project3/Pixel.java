package edu.project3;

public class Pixel {
    private int hitCount;
    private final MyColor c;

    public Pixel(MyColor c, int hitCount) {
        this.c = c;
        this.hitCount = hitCount;
    }

    public MyColor getColor() {
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
