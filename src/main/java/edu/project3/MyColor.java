package edu.project3;

import java.awt.Color;

public final class MyColor {
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 255;

    int r;
    int g;
    int b;

    public MyColor(int r, int g, int b) {
        if (!(MIN_VALUE <= r && r <= MAX_VALUE)) {
            throw new IllegalArgumentException(String.format("R value is not between %d and %d", MIN_VALUE, MAX_VALUE));
        } else if (!(MIN_VALUE <= g && g <= MAX_VALUE)) {
            throw new IllegalArgumentException(String.format("G value is not between %d and %d", MIN_VALUE, MAX_VALUE));
        } else if (!(MIN_VALUE <= b && b <= MAX_VALUE)) {
            throw new IllegalArgumentException(String.format("B value is not between %d and %d", MIN_VALUE, MAX_VALUE));
        }

        this.r = r;
        this.g = g;
        this.b = b;
    }

    public MyColor mixWith(MyColor other) {
        return new MyColor(
            (this.r + other.r) / 2,
            (this.g + other.g) / 2,
            (this.b + other.b) / 2
        );
    }

    public void normalize(double gamma, double curNormal) {
        this.r = Math.min(Math.max(MIN_VALUE, (int) (r * Math.pow(curNormal, 1.0 / gamma))), MAX_VALUE);
        this.g = Math.min(Math.max(MIN_VALUE, (int) (g * Math.pow(curNormal, 1.0 / gamma))), MAX_VALUE);
        this.b = Math.min(Math.max(MIN_VALUE, (int) (b * Math.pow(curNormal, 1.0 / gamma))), MAX_VALUE);
    }

    public Color toAWTColor() {
        final int MIN_AWT = 0;
        final int MAX_AWT = 255;

        if (MIN_VALUE == MIN_AWT && MAX_VALUE == MAX_AWT) {
            return new Color(r, g, b);
        } else {
            return new Color(
                ((r - MIN_VALUE) / (MAX_VALUE - MIN_VALUE)) * (MAX_AWT - MIN_AWT) + MIN_AWT,
                ((g - MIN_VALUE) / (MAX_VALUE - MIN_VALUE)) * (MAX_AWT - MIN_AWT) + MIN_AWT,
                ((b - MIN_VALUE) / (MAX_VALUE - MIN_VALUE)) * (MAX_AWT - MIN_AWT) + MIN_AWT
            );
        }
    }
}
