package edu.hw2.Task2;

public class Rectangle {
    private int width;
    private int height;

    public boolean setWidth(int width) {
        if (width > 0) {
            this.width = width;
        }
        return false;
    }

    public boolean setHeight(int height) {
        if (height > 0) {
            this.height = height;
        }
        return false;
    }

    public double area() {
        return width * height;
    }
}
