package edu.hw2;

public class Task2 {
    public static class Rectangle {
        private int width;
        private int height;

        boolean setWidth(int width) {
            if (width > 0) {
                this.width = width;
            }
            return false;
        }

        boolean setHeight(int height) {
            if (height > 0) {
                this.height = height;
            }
            return false;
        }

        double area() {
            return width * height;
        }
    }

    public static class Square extends Rectangle {
        @Override
        boolean setWidth(int size) {
            super.setHeight(size);
            super.setWidth(size);
            return true;
        }

        @Override
        boolean setHeight(int size) {
            super.setHeight(size);
            super.setWidth(size);
            return true;
        }
    }
}
