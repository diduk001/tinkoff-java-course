package edu.hw2.Task2;

public class Square extends Rectangle {
    @Override
    public boolean setWidth(int size) {
        super.setHeight(size);
        super.setWidth(size);
        return true;
    }

    @Override
    public boolean setHeight(int size) {
        super.setHeight(size);
        super.setWidth(size);
        return true;
    }
}
