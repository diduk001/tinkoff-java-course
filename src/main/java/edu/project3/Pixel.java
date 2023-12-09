package edu.project3;

public class Pixel {
    private final int hitCount;
    private final MyColor c;

    public Pixel(MyColor c, int hitCount) {
        this.c = c;
        this.hitCount = hitCount;
    }

    public int getHitCount() {
        return hitCount;
    }

    MyColor getColor() {
        return c;
    }

    double getNormal() {
        return Math.log10(hitCount);
    }

    void normalize(double gamma) {
        c.normalize(gamma, getNormal());
    }
}
