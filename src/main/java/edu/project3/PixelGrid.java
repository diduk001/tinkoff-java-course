package edu.project3;

import java.util.Arrays;

public class PixelGrid {
    Pixel[] pixels;
    int width;
    int height;

    public PixelGrid(Pixel[] pixels, int width, int height) {
        if (width * height != pixels.length) {
            throw new IllegalArgumentException("width * height must be equal to pixels.length");
        }
        this.pixels = pixels;
        this.width = width;
        this.height = height;
    }

    public PixelGrid(int width, int height, MyColor c) {
        this.width = width;
        this.height = height;
        this.pixels = new Pixel[width * height];
        Arrays.setAll(this.pixels, (int ignored) -> new Pixel(new MyColor(c), 0));
    }

    public Pixel getPixel(int row, int col) {
        return pixels[row * width + col];
    }

    public boolean contains(int row, int col) {
        return 0 <= row && row < height
            && 0 <= col && col < width;
    }

    public int getColFromPoint(double pointX, double minValue, double maxValue) {
        return (int) (width * (pointX - minValue) / (maxValue - minValue));
    }

    public int getRowFromPoint(double pointY, double minValue, double maxValue) {
        return (int) (height * (pointY - minValue) / (maxValue - minValue));
    }

    public void mixAndHit(int row, int col, MyColor other) {
        this.pixels[width * row + col].mixWith(other);
        this.pixels[width * row + col].hit();
    }

    public void applyGammaCorrection(double gamma) {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                this.pixels[row * width + col].normalize(gamma);
            }
        }
    }
}
