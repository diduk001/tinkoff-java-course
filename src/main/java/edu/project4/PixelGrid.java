package edu.project4;

public interface PixelGrid {
    int getWidth();

    int getHeight();

    Pixel[] getPixelsArray();

    MyColor getColor(int row, int col);

    boolean contains(int row, int col);

    int getColFromPoint(double pointX, double minValue, double maxValue);

    int getRowFromPoint(double pointY, double minValue, double maxValue);

    void mixAndHit(int row, int col, MyColor other);

    void applyGammaCorrection(double gamma);
}
