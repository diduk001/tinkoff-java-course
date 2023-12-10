package edu.project4;

public final class LinearTransformation {
    private final double a;
    private final double b;
    private final double c;
    private final double d;
    private final double e;
    private final double f;

    public LinearTransformation(
        double a, double b, double c,
        double d, double e, double f
    ) {
        if (coefficientDoesNotSatisfy(a, b, d, e)) {
            throw new IllegalArgumentException("Argument should satisfy relations");
        }

        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }

    public LinearTransformation() {
        double aRandom;
        double bRandom;
        double cRandom;
        double dRandom;
        double eRandom;
        double fRandom;

        do {
            aRandom = -1 + Math.random() * 2;
            bRandom = -1 + Math.random() * 2;
            cRandom = -1 + Math.random() * 2;
            dRandom = -1 + Math.random() * 2;
            eRandom = -1 + Math.random() * 2;
            fRandom = -1 + Math.random() * 2;
        } while (coefficientDoesNotSatisfy(aRandom, bRandom, dRandom, eRandom));

        this.a = aRandom;
        this.b = bRandom;
        this.c = cRandom;
        this.d = dRandom;
        this.e = eRandom;
        this.f = fRandom;
    }

    private static boolean coefficientDoesNotSatisfy(
        double a, double b,
        double d, double e
    ) {
        return a * a + d * d >= 1 && b * b + e * e >= 1
            && a * a + b * b + d * d + e * e >= 1 + (a * e - b * d) * (a * e - b * d);
    }

    public double a() {
        return a;
    }

    public double b() {
        return b;
    }

    public double c() {
        return c;
    }

    public double d() {
        return d;
    }

    public double e() {
        return e;
    }

    public double f() {
        return f;
    }
}
