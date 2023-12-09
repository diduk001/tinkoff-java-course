package edu.project3;

import java.util.Arrays;

public class ArrayPixelGrid implements PixelGrid {
    Pixel[] pixels;
    int width;
    int height;

    public ArrayPixelGrid(Pixel[] pixels, int width, int height) {
        if (width * height != pixels.length) {
            throw new IllegalArgumentException("width * height must be equal to pixels.length");
        }
        this.pixels = pixels;
        this.width = width;
        this.height = height;
    }

    public ArrayPixelGrid(int width, int height, MyColor c) {
        this.width = width;
        this.height = height;
        this.pixels = new Pixel[width * height];
        Arrays.setAll(this.pixels, (int ignored) -> new Pixel(new MyColor(c), 0));
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
        return Arrays.copyOf(pixels, pixels.length);
    }

    @Override
    public MyColor getColor(int row, int col) {
        return pixels[row * width + col].getColor();
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
        this.pixels[width * row + col].mixWith(other);
        this.pixels[width * row + col].hit();
    }

    @Override
    public void applyGammaCorrection(double gamma) {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                this.pixels[row * width + col].normalize(gamma);
            }
        }
    }
}
