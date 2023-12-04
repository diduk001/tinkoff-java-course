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
        Arrays.setAll(this.pixels, (int ignored) -> new Pixel(c, 0));
    }

    public Pixel getPixel(int row, int col) {
        return pixels[row * width + col];
    }

    public void setPixel(int row, int col, Pixel newPixel) {
        pixels[row * width + col] = newPixel;
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

    public PixelGrid applyGammaCorrection(double gamma) {
        PixelGrid result = new PixelGrid(this.pixels, this.width, this.height);
        double maxNormal = 0;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                final Pixel curPixel = result.getPixel(row, col);
                if (curPixel.hitCount() == 0) {
                    continue;
                }
                maxNormal = Math.max(maxNormal, curPixel.getNormal());
            }
        }

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                result.setPixel(row, col, getPixel(row, col).normalized(gamma));
            }
        }

        return result;
    }
}