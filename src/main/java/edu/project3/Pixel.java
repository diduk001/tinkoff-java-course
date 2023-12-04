package edu.project3;

public record Pixel(MyColor c, int hitCount) {
    double getNormal() {
        return Math.log10(hitCount);
    }

    Pixel normalized(double gamma) {
        return new Pixel(c.normalized(gamma, getNormal()), hitCount);
    }
}
