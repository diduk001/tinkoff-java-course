package edu.project3;

import java.util.concurrent.atomic.AtomicReferenceArray;

public class AtomicPixelGrid implements PixelGrid {
    AtomicReferenceArray<Pixel> pixels;
    int width;
    int height;

    public AtomicPixelGrid(Pixel[] pixels, int width, int height) {
        if (width * height != pixels.length) {
            throw new IllegalArgumentException("width * height must be equal to pixels.length");
        }
        this.pixels = new AtomicReferenceArray<>(pixels);
        this.width = width;
        this.height = height;
    }

    public AtomicPixelGrid(int width, int height, MyColor c) {
        this.width = width;
        this.height = height;
        this.pixels = new AtomicReferenceArray<>(width * height);
        for (int i = 0; i < width * height; i++) {
            this.pixels.lazySet(i, new AtomicPixel(new AtomicColor(c), 0));
        }
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public Pixel[] getPixelsArray() {
        Pixel[] result = new Pixel[width * height];
        for (int i = 0; i < width * height; i++) {
            result[i] = pixels.get(i);
        }
        return result;
    }

    @Override
    public MyColor getColor(int row, int col) {
        return pixels.get(row * width + col).getColor();
    }

    @Override
    public boolean contains(int row, int col) {
        return 0 <= row && row < height
            && 0 <= col && col < width;
    }

    @Override
    public int getColFromPoint(double pointX, double minValue, double maxValue) {
        return (int) (width * (pointX - minValue) / (maxValue - minValue));
    }

    @Override
    public int getRowFromPoint(double pointY, double minValue, double maxValue) {
        return (int) (height * (pointY - minValue) / (maxValue - minValue));
    }

    @Override
    public void mixAndHit(int row, int col, MyColor other) {
        this.pixels.get(width * row + col).mixWith(other);
        this.pixels.get(width * row + col).hit();
    }

    @Override
    public void applyGammaCorrection(double gamma) {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                this.pixels.get(row * width + col).normalize(gamma);
            }
        }
    }
}
